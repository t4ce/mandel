package src;

import java.nio.ByteBuffer;
import static src.Mandel.*;

/**
 *
 * @author jonas
 */
public class Worker extends Thread{
    
    // finds out about convergenz/divergenz
    int grayValue(ComplexNumber c) {
        ComplexNumber z = new ComplexNumber(0, 0);
        // 0..255
        for(int i = 0; i < 10; i++){
            //z = z * z + c
            z.mul(z);
            z.add(c);
            // outside 
            if (z.abs() > 2){  
                 //int r = Math.abs((int)Math.round(255 * Math.sin(180 / Math.PI * i)));
                 //int g = Math.abs((int)Math.round(255 * Math.cos(180 / Math.PI * i)));
                 //return new Color(r, g, (r+g)/2).getRGB();
                 return (i * 25) - 128;
            }
        }
        // inside
        return 255 - 128;
    }
    
    // stores the gray-values in a ByteBuffer
    ByteBuffer values = ByteBuffer.allocateDirect(IMG_SIZE);
    
    // indices a column within the image
    int range;
   
    boolean started = false;
    boolean done = false;
    
    // constructor for worker
    public Worker(int range){
        super();
        this.range = range;
    }
    
    @Override
    public void run(){
        done = false;
        // for every row_pixel: i in column: range
        for (int i = 0; i < IMG_SIZE; i++) {  
            ComplexNumber c = scaleToComplex(range, i, rect_x, rect_y, rect_width);
            values.put((byte)grayValue(c));
        }
        
        done = true;
        decreaseThread(range);
    }
    
    
    // -2 -2 width 4 = alles   
    // transform pixelvalues (img_size) to -2..+2 
    // scales the given double to a rect at (x, y) width*width
    ComplexNumber scaleToComplex(int ax, int ay, double rx, double ry, double width){
        // scale on to rect
        double resX = ((double)ax / IMG_SIZE) * width;
        double resY = ((double)ay / IMG_SIZE) * width;
        // translate rect ->
        resX += rx;
        resY += ry;    
        return new ComplexNumber(resX, resY);
    }
}
