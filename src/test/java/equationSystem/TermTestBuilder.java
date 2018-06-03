package equationSystem;

public abstract class TermTestBuilder {

	protected Fraction fractionValue;
	protected ConvertToFraction convAux = new ConvertToFraction();
	
	public TermTestBuilder() {
		fractionValue = new Fraction(0,1);
	}
	
	protected TermTestBuilder constantValue(double doubleValue) {
        fractionValue = convAux.convertToFraction(doubleValue);
		return this;
	}

	public TermTestBuilder constantValue(int num, int den) {
		this.fractionValue = new Fraction (num,den);
		return this;
	}

	protected VariableTestBuilder nameValue(String string) {
		return null;
	}

	abstract Term build();

}
