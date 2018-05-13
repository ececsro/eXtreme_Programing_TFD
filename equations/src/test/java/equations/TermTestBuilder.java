package equations;

public abstract class TermTestBuilder {

	protected float value;
	
	public TermTestBuilder() {
		this.value=0;
	}
	
	protected TermTestBuilder constantValue(double doubleValue) {
		this.value = (float) doubleValue;
		return this;
	}
	
	protected VariableTestBuilder nameValue(String string) {
		return null;
	}

	abstract Term build();
}
