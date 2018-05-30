package equationSystem;

public class ExpressionBuilder {

	private Expression expression;
	
	public ExpressionBuilder() {
		this.expression = new Expression();
	}
	
	public ExpressionBuilder term(float value, String name){
		expression.add(new Variable(value, name));
		return this;
	}
	
	public ExpressionBuilder term(float value){
		expression.add(new Constant(value));
		return this;
	}
	
	public Expression build(){
		return expression;
	}
}
