package equationSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Equation {

	Map<Side, Expression> members;

	public Equation() {
		this(new Expression[]{new Expression(), new Expression()});
	}

	private Equation(Expression[] expresions){
		this.members = new HashMap<Side, Expression>();
		int i=0;
		for(Side side : Side.values()) {
			this.members.put(side, expresions[i++]);
		}
	}
	
	public void add(Side side, Expression expression) {
		this.members.get(side).add(expression);		
	}

	public void add(Side side, Term term) {
		this.members.get(side).add(term.clon());		
	}
	
	public void add(Term term){
		for(Expression expresion : this.members.values()){
			expresion.add(term);
		}
	}
	
	public void add(Equation equation){
		for(Side side : Side.values()){
			this.members.get(side).add(equation.members.get(side));
		}
	}
	
	public void multiply(float value){
		for(Expression expresion : members.values()){
			expresion.multiply(value);
		}
	}	
	
	public void multiply(Fraction value){
		for(Expression expresion : members.values()){
			expresion.multiply(value);
		}
	}	

	public Fraction getValue(String name) {
		Fraction value = new Fraction (0,1);
		
		for(Expression expresion : members.values()){
			if (expresion.hasName(name)){
				value = expresion.getValue(name);
			}
		}
		return value;
	}
	
	public Fraction getValue(Side side) {
		return members.get(side).getValue();
	}

	public void simplify(Side side, String name){
		members.get(side).simplify(name);
	}
	
	public void simplify(Side side) {
		members.get(side).simplify();		
	}
	
	public Set<String> getNameSet() {
		Set<String> nameSet = new HashSet<String>();
		for(Expression expresion : members.values()){
			for(String name : expresion.getNameSet()){
				nameSet.add(name);
			}
		}
		return nameSet;
	}	
	
	public boolean equal(Equation equation) {
		if (this == equation)
			return true;
		if (equation == null)
			return false;
		for(Side side : Side.values()){
			if (!members.get(side).equal(equation.members.get(side)))
				return false;
		}
		return true;
	}
	
	public Equation clon() {
		return new Equation(new Expression[]{
				this.members.get(Side.LEFT).clon(),
				this.members.get(Side.RIGHT).clon()				
		});
	}
	
	@Override
	public String toString(){
		return members.get(Side.LEFT).toString() + " = " + members.get(Side.RIGHT).toString();
	}
	
	public void apply(String name, Fraction value) {
		for(Side side : Side.values()){
			if (members.get(side).hasName(name)){
				members.get(side).apply(name, value);
			}
		}
	}

	public void invert() {
		Equation equation = new Equation(new Expression[]{
								this.members.get(Side.RIGHT),
								this.members.get(Side.LEFT)
							});
		this.members = equation.members;
	}
}
