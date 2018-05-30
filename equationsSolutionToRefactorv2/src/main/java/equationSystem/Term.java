package equationSystem;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Set;

public abstract class Term {

	private float value;
	
	protected Term(float value){
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	public void multiply(float value) {
		this.value *= value;
	}

	public boolean hasName(String name) {
		return false;
	}
	
	public boolean hasName(Set<String> nameSet) {
		return false;
	}
	
	public boolean equal(Term term){
		return this.getValue()== term.getValue(); 
	}
	
	public abstract Term clon();
	
	@Override
	public String toString() {
		return setDecimalFormat().format(this.value);
	}

	protected DecimalFormat setDecimalFormat() {
		DecimalFormat df = new DecimalFormat("+#.##;-#");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df;
	}

	public abstract void dispatch(TermVisitor termVisitor);

	public abstract String getName();

}
