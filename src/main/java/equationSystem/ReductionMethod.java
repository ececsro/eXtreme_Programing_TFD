package equationSystem;

import java.util.HashMap;
import java.util.Map;

public class ReductionMethod extends SolutionMethod {
	
	private Map<String, Equation> solutions = new HashMap<String, Equation>();

	@Override
	public void resolve() {
		this.multiplyByCrossedFactors(firstName);
		this.simplifyFirstName(firstName, secondName);
		this.getSecondNameSolution(secondName);
		this.simplifySecondName(secondName);
		this.getFirstNameSolution(firstName);
	}

	void multiplyByCrossedFactors (String firstName) {
		Fraction value1 = this.getLast(2).getValue(firstName);
		Fraction value2 = this.getLast().getValue(firstName);

		this.copyBefore(2);
		this.getLast().multiply(value2);
		this.copyBefore(2);
		this.getLast().multiply(value1.multiply(-1));
		this.copyBefore();
		this.getLast().add(this.getLast(3));
	}
	
	void simplifyFirstName(String firstName, String secondName) {
		this.copyBefore();
		this.getLast().simplify(Side.LEFT, firstName);
		this.copyBefore();
		this.getLast().simplify(Side.LEFT, secondName);
		this.copyBefore();
		this.getLast().simplify(Side.RIGHT);
	}
	
	void getSecondNameSolution(String secondName) {
		this.copyBefore();
		this.getLast().multiply(this.getLast(2).getValue(secondName).invert());
		this.setSolution(secondName, this.getLast());
	}
	
	void simplifySecondName (String secondName) {
		this.copyBefore(9);
		this.getLast().apply(secondName, this.getLast(2).getValue(Side.RIGHT));
		this.copyBefore();
		Fraction tmpFraction2 = this.getLast(2).getValue(Side.LEFT);
		tmpFraction2 = tmpFraction2.multiply(-1);
		this.getLast().add(new Constant(tmpFraction2));
		this.copyBefore();
		this.getLast().simplify(Side.LEFT);
		this.copyBefore();
		this.getLast().simplify(Side.RIGHT);
	}
	
	void getFirstNameSolution(String firstName) {
		this.copyBefore();
		this.getLast().multiply(this.getLast(2).getValue(firstName).invert());
		this.setSolution(firstName, this.getLast());
	}
	
	void copyBefore(int before){
		int index = this.equationList.size() - before;
		this.add(this.get(index).clon());
	}
	
	void copyBefore(){
		this.copyBefore(1);
	}
	
	private Equation get(int index){
		return this.equationList.get(index);
	}

	public void add(Equation equation) {
		this.equationList.add(equation);
	}
	
	Equation getLast(int before){
		int index = this.equationList.size() - before;
		return this.equationList.get(index);
	}
	
	Equation getLast(){
		return this.getLast(1);
	}

	void setSolution(String firstName, Equation equation) {
		this.solutions.put(firstName, equation);
	}
	
	public Fraction getSolution(String name){
		return this.solutions.get(name).getValue(Side.RIGHT);
	}
}
