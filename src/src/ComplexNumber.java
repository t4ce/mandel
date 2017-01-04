package src;

/**
 * Implements a complex number a + b*i;
 * 
 * @author jonas
 *
 */
public class ComplexNumber extends Number {
	private double real = 1;
	private double imag = 0;
	
	/**
	 * Default Constructor with real and imaginary part.
	 * 
	 * @param real
	 * @param imag
	 */
	public ComplexNumber(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}
	
	/**
	 * Copy Constructor.
	 * 
	 * @param other
	 */
	public ComplexNumber(ComplexNumber other) {
		this.real = other.real;
		this.imag = other.imag;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ComplexNumber other = (ComplexNumber) obj;
		if (imag!=other.imag)
			return false;
		if (real!=other.real)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "("+this.real+" + "+this.imag+"i)";
	}
	
	/**
	 * Sets the value of this number instance to the value of other.
	 * @param other
	 */
	public void set(ComplexNumber other) {
		this.real = other.real;
		this.imag = other.imag;
	}
	
	/**
	 * Adds the ComplexNumber given as parameter to this one.
	 * 
	 * @param other
	 */
	public void add(ComplexNumber other) {
		this.real += other.real;
		this.imag += other.imag;
	}

	/**
	 * Subtracts the ComplexNumber given as parameter from this one.
	 * 
	 * @param other
	 */
	public void sub(ComplexNumber other) {
		this.real -= other.real;
		this.imag -= other.imag;
	}
	
	/**
	 * Multiplies this ComplexNumber by the given one.
	 * 
	 * @param other
	 */
	public void mul(ComplexNumber other) {
		double new_real = (this.real * other.real - this.imag * other.imag);
		this.imag = (this.real * other.imag + this.imag * other.real);
		this.real = new_real;
	}

	/**
	 * Divides this ComplexNumber by the given one.
	 * 
	 * @param other
	 */
	public void div(ComplexNumber other) {
		double nenner = other.real * other.real + other.imag * other.imag;
		this.real = (this.real * other.real + this.imag * other.imag) / nenner;
		this.imag = (this.imag * other.real - this.real * other.imag) / nenner; 
	}
	
	/**
	 * Conjugates this complex number
	 */
	public void conjugate() {
		this.imag = -this.imag;
	}
        
        public double abs(){
            return Math.sqrt(real * real + imag * imag);
        }
	
	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImag() {
		return imag;
	}

	public void setImag(double imag) {
		this.imag = imag;
	}

	@Override
	public void set(Number other) {
		if (other instanceof ComplexNumber) {
			this.set((ComplexNumber)other);
		} else {
			throw new IllegalArgumentException("setting of "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void add(Number other) {
		if (other instanceof ComplexNumber) {
			this.add((ComplexNumber)other);
		} else {
			throw new IllegalArgumentException("addition of "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void sub(Number other) {
		if (other instanceof ComplexNumber) {
			this.sub((ComplexNumber)other);
		} else {
			throw new IllegalArgumentException("subtraction of "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void mul(Number other) {
		if (other instanceof ComplexNumber) {
			this.mul((ComplexNumber)other);
		} else {
			throw new IllegalArgumentException("multiplication by "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void div(Number other) {
		if (other instanceof ComplexNumber) {
			this.div((ComplexNumber)other);
		} else {
			throw new IllegalArgumentException("division by "+other.getClass()+" not supported yet");
		}
	}
}
