package equationSystem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NamesExpresionAnalyzer implements TermVisitor {

	private Set<String> nameSet;
	
	public NamesExpresionAnalyzer(List<Term> termList) {
		this.nameSet = new HashSet<String>();
		for(Term term : termList){
			term.dispatch(this);
		}
	}
	
	@Override
	public void visit(Variable variable) {
		this.nameSet.add(variable.getName());		
	}

	@Override
	public void visit(Constant constant) {		
	}

	public Set<String> getNameSet() {
		return nameSet;
	}

}
