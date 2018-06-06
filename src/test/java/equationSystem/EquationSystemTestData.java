package equationSystem;

public class EquationSystemTestData {
	
	SolutionMethod solutionMethod = new ReductionMethod();

	String name1 = "Very long variable including espaces";
	String name2 = "y";
	Fraction expectedValueName1 = new Fraction(-14,1);
	Fraction expectedValueName2 = new Fraction(8,1);
	EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,1,name1).term(4,1,name2).equals().term(4,1)
			.equation().term(5,1,name1).term(9,1,name2).equals().term(2,1)
			.build();
}
