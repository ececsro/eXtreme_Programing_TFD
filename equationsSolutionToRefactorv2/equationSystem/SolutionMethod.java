package equationSystem;

public abstract class SolutionMethod {

	protected EquationSystem equationSystem;
	
	public void set(EquationSystem equationSystem){
		this.equationSystem = equationSystem;
	}

	public abstract void resolve();
}
