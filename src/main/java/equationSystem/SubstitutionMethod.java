package equationSystem;

import java.util.HashMap;
import java.util.Map;

public class SubstitutionMethod extends SolutionMethod {

	@Override
	public void resolve() {
		this.getFirstVariable();
		this.replaceFirstName();
		this.getSecondNameSolution();
		this.getFirstNameSolution();
	}

	public void getFirstVariable() {
		Fraction valueFirstName = this.getLast(2).getValue(this.firstName);
		Fraction valueSecondVariable = this.getLast(2).getValue(this.secondName);
					
		Equation equationTemp = new Equation();
		equationTemp.add(Side.LEFT, new Variable(valueFirstName.multiply(valueFirstName.invert()),this.firstName));
		equationTemp.add(Side.RIGHT, new Constant(this.getLast(2).getValue(Side.RIGHT).multiply(valueFirstName.invert())));		
		equationTemp.add(Side.RIGHT, new Variable(valueSecondVariable.multiply(-1).multiply(valueFirstName.invert()),this.secondName));
		this.add(equationTemp);	
	}

	public void replaceFirstName() {
		Fraction valueFirstName = this.equationList.get(1).getValue(this.firstName);
		
		this.copyBefore();
		this.getLast().multiply(valueFirstName);

		Expression expression1 = this.getLast().members.get(Side.RIGHT);

		Equation equationTemp = new Equation();
		equationTemp.add(Side.LEFT, new Variable(this.equationList.get(1).getValue(secondName),this.secondName));
		equationTemp.add(Side.RIGHT, new Constant(this.equationList.get(1).getValue(Side.RIGHT)));
		
		equationTemp.add(Side.LEFT, expression1);
		this.add(equationTemp);			
	}

	public void getSecondNameSolution() {
		this.copyBefore();
		Fraction value = this.getLast().getValue(Side.LEFT);
		value = value.multiply(-1);
		Constant constant = new Constant(value);
		this.getLast().add(constant);
		this.getLast().simplify(Side.LEFT, this.secondName);
		this.getLast().simplify(Side.LEFT);		
		this.getLast().simplify(Side.RIGHT);
		this.copyBefore();
		this.getLast().multiply(this.getLast().getValue(this.secondName).invert());
		this.setSolution(secondName, this.getLast());
	}

	public void getFirstNameSolution() {
		this.copyBefore(5);
		this.getLast().apply(this.secondName, this.getLast(2).getValue(Side.RIGHT));
		this.copyBefore();
		this.getLast().simplify(Side.RIGHT);
		this.setSolution(this.firstName, this.getLast());		
	}
}
