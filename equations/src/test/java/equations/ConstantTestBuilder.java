package equations;

public class ConstantTestBuilder extends TermTestBuilder {


	public ConstantTestBuilder () {	
		this.value=0;		
	}
	
	public ConstantTestBuilder constantValue(float value) {
		this.value = value;
		return this;
	}
	
	public Constant build() {
		return (new Constant (this.value));
	}
}