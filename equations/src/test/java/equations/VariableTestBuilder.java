package equations;

public class VariableTestBuilder extends TermTestBuilder {

	private String name;

	public VariableTestBuilder () {	
		this.name="";
	}
	
	public VariableTestBuilder nameValue(String name) {
		this.name = name;
		return this;
	}

	public Variable build() {
		return (new Variable(this.value,this.name));
	}
}
