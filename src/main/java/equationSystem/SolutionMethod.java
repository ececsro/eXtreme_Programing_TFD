package equationSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SolutionMethod {

	protected List<Equation> equationList;
	private Map<String, Equation> solutions = new HashMap<String, Equation>();
	
	protected String firstName;
	protected String secondName;
	
	public void set(List<Equation> equationList){
		this.equationList = equationList;

		Set<String> nameSet = new HashSet<String>();
		for (Equation equation : equationList) {
			for(String name : equation.getNameSet()){
				nameSet.add(name);
			}
		}
		
		assert nameSet.size() == 2;
		
		Iterator<String> nameIterator = nameSet.iterator();
		firstName = nameIterator.next();	
		secondName = nameIterator.next();
	}
	
	public abstract void resolve();

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
