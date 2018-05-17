package equations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NamesExpressionAnalyzer implements TermVisitor {

	List <Term> expression = new ArrayList<Term>();
	Set<String> nameSet = new HashSet<String>();
	
	public NamesExpressionAnalyzer(List<Term> expressionToAnalyze) {
		for (Term term : expressionToAnalyze) {
			this.expression.add(term);
		}
	}
	
	public Set<String> getNameSet() {
		for (Term term : expression) {
			term.dispatch(this);
		}
		
		return this.nameSet;
	};
	
	public void visit(Constant constant) {
	}

	public void visit(Variable variable) {
		nameSet.add(variable.getName());
	}

}
