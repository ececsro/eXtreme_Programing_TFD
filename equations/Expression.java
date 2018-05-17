package equations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Expression {

	List<Term> expression = new ArrayList<Term>();
	
	public List<Term> getExpression() {
		return expression;
	}

	public String toString() {
		String expressionString = "";
		for (Term term : expression) {
			expressionString = expressionString + term.toString();
		}
		return expressionString;
	}

	public boolean empty() {
		if (expression.size() == 0)
			return true;
		else
			expression.clear();
			return false;
	}

	public void add(Term term) {
		expression.add(term);
	}

	public boolean equal(Expression expressionToCompare) {
		boolean comparationResult = false;
	    int numMiddleComparationMatch = 1;			

	    int numberOfMatches = 0;
	    
	    if (this.expression.size() != expressionToCompare.getExpression().size()) {
	    	return false;
	    }
	    
	    int i = 0;
	    while (i < this.expression.size() &&
	    		this.expression.get(i).equal(
	    				expressionToCompare.getExpression().get(i)))  {
	    	i++;
	    	numberOfMatches = i;
	    }
	    
	    if (numberOfMatches == this.expression.size()) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	    
	    
	    
/*		for (int i = 0; i < this.expression.size() ; i++) {
			numMiddleComparationMatch = 0;
			for (Term termToCompare : expressionToCompare.getExpression()) {
				if (expression.get(i).equal(termToCompare)) {
					numMiddleComparationMatch ++;
				}
			}
		}		

	    for (int i = 0; 
				i < this.expression.size() && numMiddleComparationMatch == 1 ; 
				i++) {
			numMiddleComparationMatch = 0;
			for (Term termToCompare : expressionToCompare.getExpression()) {
				if (expression.get(i).equal(termToCompare)) {
					numMiddleComparationMatch ++;
				}
			}
		}		

		if (this.expression.size()>0 && numMiddleComparationMatch == 1 ) {
			comparationResult = true;
		}

	    if (this.expression.size() == 0 && expressionToCompare.getExpression().size() == 0) {
			comparationResult = true;			
		}
		return comparationResult;
*/
	}

	public float getValue() {
		float value = 0;
	
		for (Term term : expression ) {
			if (term.getName() == null) {
				value = value + term.getValue();
			}
		}
		return value;
	}

	public float getValue(String name) {
		float value = 0;

		for (Term term : expression ) {
			if (term.getName() == name) {
				value = value + term.getValue();
			}
		}
		return value;
	}

	public void multiply(float value) {
		for (Term term : expression ) {
				term.multiply(value);
		}
	}

	public void simplify() {
		float totalConstantValue = this.getValue();
		Constant simplifyConstant = new Constant(totalConstantValue);
		
		boolean constantFound = false;
		
		for (Iterator<Term> itTerm = expression.iterator(); itTerm.hasNext();) {
			Term term = itTerm.next();
			if (term.getName() == null) {
				constantFound = true;
				itTerm.remove();
			}
		}

		if (constantFound) {
			this.add(simplifyConstant);			
		}
	}

	public void simplify(String nameValue) {
		float totalVariableValue = this.getValue(nameValue);
		Variable simplifyVariable = new Variable(totalVariableValue,nameValue);

		boolean variableFound = false;

		for (Iterator<Term> itTerm = expression.iterator(); itTerm.hasNext();) {
			Term term = itTerm.next();
			if (term.getName() == nameValue) {
				variableFound = true;	
				itTerm.remove();
			}				
		}

		if (variableFound) {
			this.add(simplifyVariable);			
		}
	}

	public Set<String> getNameSet() { 

		return (new NamesExpressionAnalyzer(this.expression).getNameSet());
		
/*	
		Set<String> nameSet = new HashSet<String>();
		
		for (Term term : expression ) {
			if (term.getName() != null) {
				nameSet.add(term.getName());
			}
		}		
		return nameSet;
*/
		
	}

	public boolean hasName(String nameValue) {

		boolean foundName = false; 
		Set<String> nameSet = new HashSet<String>();
		nameSet = this.getNameSet();
		for (String name : nameSet) {
			if (name == nameValue) {
				foundName = true;
			}
		}
		return foundName;
	}

	public void add(Expression expressionAdded) {
		for (Term termAdded : expressionAdded.getExpression()) {
			this.add(termAdded);
		}		
	}

	public Expression clone() {
		Expression clonedExpression = new Expression();
		for (Term term : expression) {
			clonedExpression.add(term.clone());
		}
		
		return clonedExpression;
	}
}
