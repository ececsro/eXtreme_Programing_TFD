package equationSystem;

public class ConstantTestBuilder extends TermTestBuilder {


	public ConstantTestBuilder () {	
		this.fractionValue=new Fraction(0,1);
	}
	
    public ConstantTestBuilder constantValue(double doubleValue) {
		return (ConstantTestBuilder) super.constantValue(doubleValue);
	}
	
	public Constant build() {
		return (new Constant(this.fractionValue));
	}

	public ConstantTestBuilder constantValue(int num, int den) {
		return (ConstantTestBuilder) super.constantValue(num,den);
	}
}