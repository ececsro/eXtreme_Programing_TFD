package equations;

public abstract class TermTestBuilder {

	protected float value;
	
	public TermTestBuilder() {
		this.value=0;
	}
	
	public TermTestBuilder constantValue(float value) {
		this.value = value;
		return this;
	}
	
	public VariableTestBuilder nameValue(String string) {
		return null;
	}

	abstract Term build();
}
