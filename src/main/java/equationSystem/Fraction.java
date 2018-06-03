package equationSystem;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Fraction {

	int num;
	int den;
	
	public Fraction(int num, int den) {
		this.num = num;
		this.den = den;
	}

	public Fraction multiply(Fraction fraction) {
		Fraction result = new Fraction (fraction.num * this.num, fraction.den * this.den);
		result.simplify();
		return result;
	}
	public Fraction multiply(int value) {
		Fraction result = new Fraction(value * num, den);
		result.simplify();
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + den;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Fraction otherFraction = (Fraction) obj;
		Fraction thisFraction = this;
		
		otherFraction.simplify();
		thisFraction.simplify();
		
		if (thisFraction.den != otherFraction.den)
			return false;
		if (thisFraction.num != otherFraction.num)
			return false;
		return true;
	}

	public boolean equal(Fraction otherFraction) {
		if (this == otherFraction)
			return true;
		if (otherFraction == null)
			return false;

		Fraction thisFraction = this;
		
		otherFraction.simplify();
		thisFraction.simplify();
		
		if (thisFraction.den != otherFraction.den)
			return false;
		if (thisFraction.num != otherFraction.num)
			return false;
		return true;
	}

	public Fraction add(Fraction fraction) {
		final int numResult = (this.num * fraction.den) + (fraction.num * this.den);
		final int denResult = this.den * fraction.den;
		Fraction result = new Fraction(numResult, denResult);
		result.simplify();
		return result;
	}

	void simplify() {
		// ecluides's alg
		int a = Math.abs(this.num);
		int b = Math.abs(this.den);
		
		int t;
	    while(b != 0) {
	         t = b;
	         b = a % b;
	         a = t;
	    }
	    
		this.num = this.num / a;
		this.den = this.den / a;
	}

	@Override
	public String toString() {

		if (this.den == 0) {
			return "Infinite";
		}	

		if (this.num == 0) {
			return "+0";
		}
	
		String sign = "+";
		if (Math.signum(this.num)/ Math.signum(this.den) == (-1f)) {
			sign = "-";
		}	
	
		if (Math.abs(this.den) == 1) {
			return sign + Math.abs(this.num);
		}
		
		return sign + "(" + Math.abs(num) + "/" + Math.abs(den) + ")";
	}

	protected DecimalFormat setDecimalFormat() {
		DecimalFormat df = new DecimalFormat("+#;-#");
		return df;
	}

	public float getValue () {
		float numf = this.num;
		float denf = this.den;
		return (numf / denf);
	}

	public Fraction invert() {
		int auxMember;
		auxMember = num;
		num = den;
		den = auxMember;
		return this;
	}
}
