package equationSystem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class SolutionMethod {

	protected List<Equation> equationList;
	
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
}
