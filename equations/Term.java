package equations;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Set;

abstract class Term {
	
	float value;
	public float getValue() {
		return value;
	}

	public String getName() {
		return null;
	}

	public boolean hasName(String name) {
		return false;
	}

	public boolean hasName(Set<String> nameSet) {
		return false;
	}

	public Term multiply(float valueToMultiply) {
		this.value = this.value * valueToMultiply;
		return this;
	}
	
	public boolean equal(Term valueToCompare) {

		if (Float.floatToIntBits(this.value) != 
				Float.floatToIntBits(valueToCompare.value)) {
			return false;
		}

		if (this.getName() == null) {
			return true;
		}
		else {
			if (this.getName() == valueToCompare.getName()) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public String toString() {
		return setDecimalFormat().format(this.value);
	}

	public void dispatch (TermVisitor termVisitor) {
	}

	protected DecimalFormat setDecimalFormat() {
		DecimalFormat df = new DecimalFormat("+#.##;-#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df;
	}

	public Term clone() {
		Term clonedTerm;
		
		if (this.getName() == null) {
			clonedTerm = new Constant(this.value);
		}
		else {
			clonedTerm = new Variable(this.value,this.getName());			
		}
		return clonedTerm;
	}
}
