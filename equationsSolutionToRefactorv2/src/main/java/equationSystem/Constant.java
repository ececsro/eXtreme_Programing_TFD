package equationSystem;

public class Constant extends Term {

	public Constant(float value){
		super(value);
	}
	
	@Override
	public boolean equal(Term term) {
		return super.equal(term) && term instanceof Constant;
	}

	@Override
	public Term clon() {
		return new Constant(this.getValue());
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void dispatch(TermVisitor termVisitor) {
		termVisitor.visit(this);
	}
	
}
