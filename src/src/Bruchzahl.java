package src;

/**
 * Implements a Bruchzahl (Zaehler / Nenner).
 * 
 * @author kif
 *
 */
public class Bruchzahl extends Number {
	private int zaehler = 1;
	private int nenner = 1;
	
	/**
	 * Default Constructor with zaehler and nenner. May throw an
	 * exception if set to 0.
	 * 
	 * @param zaehler
	 * @param nenner
	 */
	public Bruchzahl(int zaehler, int nenner) {
		if (nenner==0) {
			throw new IllegalArgumentException(Integer.toString(nenner));			
		}
		
		this.zaehler = zaehler;
		this.nenner = nenner;
	}
	
	/**
	 * Copy Constructor.
	 * 
	 * @param other
	 */
	public Bruchzahl(Bruchzahl other) {
		this.zaehler = other.zaehler;
		this.nenner = other.nenner;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Bruchzahl other = (Bruchzahl) obj;
		if (nenner!=other.nenner)
			return false;
		if (zaehler!=other.zaehler)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "("+this.zaehler+" / "+this.nenner+")";
	}
	
	/**
	 * Sets the value of this number instance to the value of other.
	 * @param other
	 */
	public void set(Bruchzahl other) {
		this.zaehler = other.zaehler;
		this.nenner = other.nenner;
	}
	
	/**
	 * Adds the BruchZahl given as parameter to this one. Does not automatically cancel() the BruchZahl.
	 * 
	 * @param other
	 */
	public void add(Bruchzahl other) {
		this.zaehler = this.zaehler*other.nenner + other.zaehler*this.nenner;
		this.nenner *= other.nenner;
	}

	/**
	 * Subtracts the BruchZahl given as parameter from this one. Does not automatically cancel() the BruchZahl.
	 * 
	 * @param other
	 */
	public void sub(Bruchzahl other) {
		this.zaehler = this.zaehler*other.nenner - other.zaehler*this.nenner;
		this.nenner *= other.nenner;
	}
	
	/**
	 * Multiplies this BruchZahl by the given one. Does not automatically cancel() the BruchZahl.
	 * 
	 * @param other
	 */
	public void mul(Bruchzahl other) {
		this.zaehler *= other.zaehler;
		this.nenner *= other.nenner;
	}

	/**
	 * Divides this BruchZahl by the given one. Does not automatically cancel() the BruchZahl.
	 * 
	 * @param other
	 */
	public void div(Bruchzahl other) {
		this.zaehler *= other.nenner;
		this.nenner *= other.zaehler;
	}
	
	/**
	 * Classic cancel operations. Also returns the result.
	 * 
	 * @return the canceled BruchZahl
	 */
	public Bruchzahl cancel() {
		boolean once_more;
		do {
			int min = Math.min(this.zaehler, this.nenner);
			once_more = false;
			for (int i=2; i<=min; i++) {
				if (this.zaehler%i==0 && this.nenner%i==0) {
					this.zaehler /= i;
					this.nenner /= i;
					once_more = true;
					break;
				}
			}
		} while (once_more);
		
		return this;
	}

	public int getZaehler() {
		return zaehler;
	}

	public void setZaehler(int zaehler) {
		this.zaehler = zaehler;
	}

	public int getNenner() {
		return nenner;
	}

	/**
	 * Sets the nenner. Throws an exception if set to 0.
	 * 
	 * @param nenner
	 */
	public void setNenner(int nenner) {
		if (nenner==0) {
			throw new IllegalArgumentException(Integer.toString(nenner));			
		}

		this.nenner = nenner;
	}

	@Override
	public void set(Number other) {
		if (other instanceof Bruchzahl) {
			this.set((Bruchzahl)other);
		} else {
			throw new IllegalArgumentException("setting of "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void add(Number other) {
		if (other instanceof Bruchzahl) {
			this.add((Bruchzahl)other);
		} else {
			throw new IllegalArgumentException("addition of "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void sub(Number other) {
		if (other instanceof Bruchzahl) {
			this.sub((Bruchzahl)other);
		} else {
			throw new IllegalArgumentException("subtraction of "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void mul(Number other) {
		if (other instanceof Bruchzahl) {
			this.mul((Bruchzahl)other);
		} else {
			throw new IllegalArgumentException("multiplication by "+other.getClass()+" not supported yet");
		}
	}

	@Override
	public void div(Number other) {
		if (other instanceof Bruchzahl) {
			this.div((Bruchzahl)other);
		} else {
			throw new IllegalArgumentException("division by "+other.getClass()+" not supported yet");
		}
	}
}
