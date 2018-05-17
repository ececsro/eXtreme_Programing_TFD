package equations;

public class Constant extends Term {

	public Constant(float value) {	
		this.value=value;		
	}

	public Constant clon() {	
		return new Constant(this.value);
	}

	public boolean equal(Constant valueToCompare) {
		return super.equal((Term) valueToCompare);
//	if (Float.floatToIntBits(this.value) != Float.floatToIntBits(valueToCompare.value))
//			return false;
//		return true;
	}

	public String getName() {
		return null;
	}

	public void dispatch (TermVisitor termVisitor) {
		termVisitor.visit(this);
	}
}
