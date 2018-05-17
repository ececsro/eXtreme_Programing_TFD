package equations;

import java.util.ArrayList;
import java.util.List;

public class NamesExpressionTestBuilder {
	
	protected List<Term> listOfTerms = new ArrayList<Term>();

	public NamesExpressionTestBuilder(List<TermForTest> expressionTest) {

		for (TermForTest inputTerm : expressionTest) {
			if (inputTerm.getNameValue()==null) {
				Term termConstant=new ConstantTestBuilder().
						constantValue(inputTerm.getConstantValue()).build();
				listOfTerms.add(termConstant);
			}
			else {
				Term termVariable=new VariableTestBuilder().
						constantValue(inputTerm.getConstantValue()).
						nameValue(inputTerm.getNameValue()).
						build();
				listOfTerms.add(termVariable);
			}
		}
	}
		
	public NamesExpressionAnalyzer build() {
		return new NamesExpressionAnalyzer(listOfTerms);
	}
}

