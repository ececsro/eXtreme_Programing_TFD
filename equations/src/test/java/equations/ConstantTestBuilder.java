package equations;

public class ConstantTestBuilder extends TermTestBuilder {


	public ConstantTestBuilder () {	
		this.value=0;		
	}
	
    public ConstantTestBuilder constantValue(double doubleValue) {
		return (ConstantTestBuilder) super.constantValue(doubleValue);
	}
	
	public Constant build() {
		return (new Constant(this.value));
	}
}