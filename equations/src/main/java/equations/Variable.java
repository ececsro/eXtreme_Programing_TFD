package equations;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Set;

public class Variable extends Term {

	String name;

	public Variable(float value, String name) {
		this.value = value;
		this.name = name;
	}

	public boolean hasName(String name) {
		if ( this.name == name )
				return true;	
		return false;
	}

	public boolean hasName(Set<String> nameSet) {
		return nameSet.contains(this.name);
	}

	public boolean equal(Variable valueToCompare) {
		if (Float.floatToIntBits(this.getValue()) == Float.floatToIntBits(valueToCompare.getValue()) &&
		    this.getName() == valueToCompare.getName() 
		   ) 
		    	return true;
		    return false; 
	}

	private String getName() {
		return this.name;
	}

	public String toString() {
		return setDecimalFormat().format(this.value)+this.name;
	}
	

	public Variable clon() {
		return new Variable(this.value, this.name);
	}

}