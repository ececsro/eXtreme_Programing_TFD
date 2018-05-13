package equations;

import java.util.ArrayList;
import java.util.List;

public class Expression {

	List<Term> expression = new ArrayList<Term>();
	
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

	public boolean equal(Expression expression) {
		return false;
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
		
		for (Term term : expression ) {
		}
	}
}
