package equations;

public interface TermVisitor {
	abstract public void visit(Constant constant);
	abstract public void visit(Variable variable);
}
