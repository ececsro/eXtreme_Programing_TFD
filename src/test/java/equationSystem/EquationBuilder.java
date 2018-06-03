package equationSystem;

public class EquationBuilder {

	private Equation equation;
	private Fraction fraction;
	
	private Side side;
	
	public EquationBuilder() {
		this.equation = new Equation();
		this.side = Side.LEFT;
	}
	
	public EquationBuilder term(float value, String name){
		fraction = new ConvertToFraction().convertToFraction(value);
		this.equation.add(side, new Variable(fraction.num, fraction.den, name));
		return this;
	}
	
	public EquationBuilder term(float value){
		fraction = new ConvertToFraction().convertToFraction(value);
		this.equation.add(side, new Constant(fraction.num, fraction.den));
		return this;
	}
	
	public EquationBuilder term(int num, int den, String name){
		this.equation.add(side, new Variable(num, den, name));
		return this;
	}
	
	public EquationBuilder term(int num, int den){
		this.equation.add(side, new Constant(num, den));
		return this;
	}

	public EquationBuilder equals(){
		this.side = Side.RIGHT;
		return this;
	}
	
	public Equation build(){
		assert this.side == Side.RIGHT;
		return equation;
	}
}
