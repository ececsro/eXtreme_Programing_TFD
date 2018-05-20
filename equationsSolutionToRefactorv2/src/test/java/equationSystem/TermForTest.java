package equationSystem;

public class TermForTest {

	float constantValue;
	String nameValue;
	
	public TermForTest(double value) {
		this.constantValue = (float)value;
	}
	public TermForTest(double value, String name) {
		this.constantValue = (float)value;
		this.nameValue=name;
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
			realTerm = new Constant (this.constantValue);
		}
		else {
			realTerm = new Variable(this.constantValue,this.nameValue);			
		}
		return realTerm.toString(); 
	}
}
