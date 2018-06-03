package equationSystem;

public class Constant extends Term {

	public Constant(int num, int den){
		super(num,den);
	}

	public Constant(Fraction fraction){
		super(fraction);
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
