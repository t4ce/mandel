package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jonas
 */
public class Mandel {
    
    // Represents the number of activeThreads at runtime
    private static int activeThreads = 0;
    
    // Size of the produced Image where width*heigth is IMG_SIZE*IMG_SIZE
    public static final int IMG_SIZE = 1000;
    
    // The Rect that represents the section of the mandelbrot to render
    public static double rect_x = -2;
    public static double rect_y = -2;
    public static double rect_width = 4;
    
     // x AND y must be between -2..+2 AND with: width + x < 2 AND width + y < 2
    static void setRect(double x, double y, double width) throws Exception{
        if(x > 2 || x < -2 || y > 2 || y < -2 || (width + x) > 2 || (width + y) > 2){
            throw new Exception("RectBoundary not valid! x and y must be between -2..+2, width + x MUSS < 2 und width + y MUSS < 2");
        }
        rect_width = width;
        rect_x = x;
        rect_y = y;
    }
    
    // converts the rect to a readable string
    static String rectToString(){
        return "xy_" + rect_x + "_" + rect_y + "_width_" + rect_width;
    }
    
    // called by the workers whenever they finished their task completely
    public static void decreaseThread(int range) {
        activeThreads--;
    }
        
    public static void main(String[] args) throws IOException, InterruptedException, Exception {
        // 0,0 und -2,0 und -2,-2 und 0,-2 alle mit width = 2
        // setRect(0,0,2);
        // setRect(-2,0,2);
        // setRect(0,-2,2);
        // setRect(-2,-2,2);
        // setRect(-.65, -.65, 0.1);
        
        // create  and start threads
        ArrayList<Worker> workers = new ArrayList(IMG_SIZE);
        for (int x = 0; x < IMG_SIZE; x++) {
            Worker w = new Worker(x);
            workers.add(w);
        }
        
        // wait for threads to finish calculating
        boolean done;
        do {       
            // check all threads -> maybe we have to start a thread
            for (Worker worker : workers) {
                // start a thread when he is not started and when there are not more then 24 threads running
                if (!worker.started && !(activeThreads > 24)){
                    worker.started = true;
                    activeThreads++;
                    worker.start();
                }
            }
            // check if all threads are done
            boolean allReady = true;
            for (Worker worker : workers) {
                allReady = allReady && worker.done;
            }
            done = allReady;
        } while (!done);
        
        // generate the image in ram
        BufferedImage off_Image = new BufferedImage(IMG_SIZE, IMG_SIZE, BufferedImage.TYPE_INT_ARGB); 
        off_Image.setAccelerationPriority(1);
        for (int x = 0; x < IMG_SIZE; x++) {
            for (int y = 0; y < IMG_SIZE; y++) {
                int value = 255 - (workers.get(x).values.get(y) + 128);
                int color = new Color(value, value, value).getRGB(); 
                off_Image.setRGB(x, y, color);
            } 
        }    

        ImageIO.write(off_Image, "png", new File("C:\\Users\\jonas\\Downloads\\mandel_" + rectToString() + ".png"));
       
        // draw the image
        JPanel panel = new JPanel() {         
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(off_Image, 0, 0, this);
            }
        };

        JFrame frame = new JFrame("Mandel_" + rectToString());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(IMG_SIZE, IMG_SIZE);
        frame.add(panel);
        frame.setVisible(true);
    }
}
