package equations;

import java.util.ArrayList;
import java.util.List;

public class Equation {

	List<Expression> equation = new ArrayList<Expression>();

	public String toString() {
		String returnedString = null;
		String tmpString = null;
		List<String> expressionToString = new ArrayList<String>();

		expressionToString.add(0, "0");
		expressionToString.add(1, "0");
		
		for (int i=0; i < equation.size(); i++) {
			tmpString = equation.get(i).toString();
			if (tmpString != null) {
				expressionToString.set(i , tmpString);
			}	
		}
		
		returnedString = expressionToString.get(0) + " = " + expressionToString.get(1);
		
		return returnedString;
	}

}
