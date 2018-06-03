package equationSystem;

public class TermForTest {

	float constantValue;
	Fraction fractionValue = new Fraction(0,1);
	String nameValue;
	
	public TermForTest(double value) {
		this.constantValue = (float)value;
		this.fractionValue = new ConvertToFraction().convertToFraction(value);
	}
	
	public TermForTest(int num, int den) {
		this.fractionValue = new Fraction(num,den);
		this.constantValue = fractionValue.getValue();
	}

	public TermForTest(double value, String name) {
		this.constantValue = (float)value;
		this.fractionValue = new ConvertToFraction().convertToFraction(value);
		this.nameValue=name;
	}

	public TermForTest(int num, int den, String name) {
		fractionValue = new Fraction(num,den);
		this.nameValue = name;
		this.constantValue=fractionValue.getValue();
	}
	
	public Fraction getFractionValue() {
		return fractionValue;
	}
	
	public float getConstantValue() {
		return constantValue;
	}
	public String getNameValue() {
		return nameValue;
	}
	
	public String toString() {
		Term realTerm;
		if (this.nameValue == null) {
			realTerm = new Constant (this.fractionValue);
		}
		else {
			realTerm = new Variable(this.fractionValue,this.nameValue);			
		}
		return realTerm.toString(); 
	}
	public TermForTest simplify() {
		Fraction fraction = this.fractionValue;
		fraction.simplify();
		this.fractionValue = fraction; 
		return this;
	}
}
