package equationSystem;

import java.util.Set;

public abstract class Term {

	protected Fraction fractionValue;
	
	protected Term(int num, int den){
		this.fractionValue = new Fraction (num,den);
	}
	
	protected Term(Fraction fraction){
		this.fractionValue = fraction;
	}
	
	public Fraction getValue() {
		return this.fractionValue;
	}
	
	public void multiply(float value) {
		this.fractionValue = this.fractionValue.multiply(new ConvertToFraction().convertToFraction(value));		
	}

	public void multiply(Fraction value) {
		this.fractionValue = this.fractionValue.multiply(value);		
	}

	public boolean hasName(String name) {
		return false;
	}
	
	public boolean hasName(Set<String> nameSet) {
		return false;
	}
	
	public boolean equal(Term term){
		return this.getValue().equals(term.getValue()); 
	}

	public abstract Term clon();
	
	@Override
	public String toString() {
		return fractionValue.toString();
	}

	public abstract void dispatch(TermVisitor termVisitor);

	public abstract String getName();
}
