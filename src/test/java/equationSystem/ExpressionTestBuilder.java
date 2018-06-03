package equationSystem;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTestBuilder {
	
	protected List<Term> expression = new ArrayList<Term>();
	Expression newExpression = new Expression();

	public ExpressionTestBuilder addExpression(List<TermForTest> expressionTest) {

		for (TermForTest inputTerm : expressionTest) {
			if (inputTerm.getNameValue()==null) {
				Term termConstant=new ConstantTestBuilder().
						constantValue(inputTerm.fractionValue.num,inputTerm.fractionValue.den).build();
				newExpression.add(termConstant);
			}
			else {
				Term termVariable=new VariableTestBuilder().
						constantValue(inputTerm.fractionValue.num,inputTerm.fractionValue.den).
						nameValue(inputTerm.getNameValue()).
						build();
				newExpression.add(termVariable);
			}
		}
		return this;
	}
		
	public Expression build() {
		return newExpression;
	}
}

