package equationSystem;

public interface TermVisitor {
	
	void visit(Variable variable);
	
	void visit(Constant constant);
}
