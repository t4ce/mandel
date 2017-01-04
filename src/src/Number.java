package src;

/**
 * Abstract class for Number types.
 * 
 * @author jonas
 *
 */
public abstract class Number {
	/**
	 * Sets the value of this Number instance to the value of other.
	 * @param other
	 */
	public abstract void set(Number other);
		
	/**
	 * Adds the Number given as parameter to this one.
	 * 
	 * @param other
	 */
	public abstract void add(Number other);

	/**
	 * Subtracts the Number given as parameter from this one.
	 * 
	 * @param other
	 */
	public abstract void sub(Number other);

	/**
	 * Multiplies the Number given as parameter to this one.
	 * 
	 * @param other
	 */
	public abstract void mul(Number other);

	/**
	 * Divides this ComplexNumber by the given one.
	 * 
	 * @param other
	 */
	public abstract void div(Number other);
	
	/**
	 * Computes this power n, independent from concrete subclass.
	 */
	public void pow(int n) {
		for (int i=0; i<n; i++) {
			this.mul(this);
		}
	}
}
