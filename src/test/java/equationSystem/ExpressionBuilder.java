package equationSystem;

public class ExpressionBuilder {

	private Expression expression;
	private Fraction fraction;
	
	public ExpressionBuilder() {
		this.expression = new Expression();
	}
	
	public ExpressionBuilder term(float value, String name){
		fraction = new ConvertToFraction().convertToFraction(value);
		expression.add(new Variable(fraction.num, fraction.den, name));
		return this;
	}
	
	public ExpressionBuilder term(float value){
		fraction = new ConvertToFraction().convertToFraction(value);
		expression.add(new Constant(fraction.num, fraction.den));
		return this;
	}
	
	public ExpressionBuilder term(int num, int den){
		expression.add(new Constant(num, den));
		return this;
	}

	public ExpressionBuilder term(int num, int den, String name) {
		expression.add(new Variable(num, den, name));
		return this;
	}

	public ExpressionBuilder term(Fraction value){
		expression.add(new Constant(value));
		return this;
	}

	public ExpressionBuilder term(Fraction value, String name) {
		expression.add(new Variable(value, name));
		return this;
	}

	public Expression build(){
		return expression;
	}
}
