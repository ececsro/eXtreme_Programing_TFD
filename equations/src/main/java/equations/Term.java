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
	public String toString() {
		return setDecimalFormat().format(this.value);
	}

	protected DecimalFormat setDecimalFormat() {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df;
	}
}
