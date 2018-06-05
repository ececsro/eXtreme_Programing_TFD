package equationSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class SubstitutionMethodUnitTest {

	@Test
	public void getFirstVariable_valueEqual1Test() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(1,name1).term(3,4,name2).equals().term(5,6)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		SubstitutionMethod substitutionMethod = new SubstitutionMethod();
		
		equationSystem.set(substitutionMethod);
		
		substitutionMethod.getFirstVariable();

		int listSize = substitutionMethod.equationList.size();

		Equation lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+1x = +(5/6) -(3/4)y"));
	}

	@Test
	public void getFirstVariable_valueFractionNegativeTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(7,-8,name1).term(3,4,name2).equals().term(5,6)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		SubstitutionMethod substitutionMethod = new SubstitutionMethod();
		
		equationSystem.set(substitutionMethod);
		
		substitutionMethod.getFirstVariable();

		int listSize = substitutionMethod.equationList.size();

		Equation lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+1x = -(20/21) +(6/7)y"));
	}

	@Test
	public void replaceFirstNameTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(1,name1).term(4,name2).equals().term(3)
			.equation().term(2,name1).term(-9,name2).equals().term(-2)
			.build();
		SubstitutionMethod substitutionMethod = new SubstitutionMethod();
		
		equationSystem.set(substitutionMethod);
		
		substitutionMethod.getFirstVariable();
		int listSize = substitutionMethod.equationList.size();
		Equation lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+1x = +3 -4y"));

		substitutionMethod.replaceFirstName();
		
		listSize = substitutionMethod.equationList.size();
	    Equation equation2 = substitutionMethod.equationList.get(1);
		assertThat(equation2.toString(), is("+2x -9y = -2"));

		Equation last2Equation = substitutionMethod.equationList.get(listSize-2);
		assertThat(last2Equation.toString(), is("+2x = +6 -8y"));

		lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("-9y +6 -8y = -2"));

/*		assertThat(lastEquation.toString(), is("-17y = -8"));
		assertThat(lastEquation.toString(), is("+1y = +(8/17)"));	
        assertThat(lastEquation.toString(), is("+1x = +(51/17) -(32/17)"));	
        assertThat(lastEquation.toString(), is("+1x = +(19/17)"));	
*/        
	}

	@Test
	public void getSecondNameSolutionTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(1,name1).term(4,name2).equals().term(3)
			.equation().term(2,name1).term(-9,name2).equals().term(-2)
			.build();
		SubstitutionMethod substitutionMethod = new SubstitutionMethod();
		
		equationSystem.set(substitutionMethod);
		
		substitutionMethod.getFirstVariable();
		substitutionMethod.replaceFirstName();
		int listSize = substitutionMethod.equationList.size();
		Equation lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("-9y +6 -8y = -2"));

		substitutionMethod.getSecondNameSolution();

		listSize = substitutionMethod.equationList.size();

		Equation last2Equation = substitutionMethod.equationList.get(listSize-2);
		assertThat(last2Equation.toString(), is("-17y = -8"));
		
		lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+1y = +(8/17)"));	
	}

	@Test
	public void getFirstNameSolutionTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(1,name1).term(4,name2).equals().term(3)
			.equation().term(2,name1).term(-9,name2).equals().term(-2)
			.build();
		SubstitutionMethod substitutionMethod = new SubstitutionMethod();
		
		equationSystem.set(substitutionMethod);
		
		substitutionMethod.getFirstVariable();
		substitutionMethod.replaceFirstName();
		substitutionMethod.getSecondNameSolution();

		int listSize = substitutionMethod.equationList.size();		
		Equation lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+1y = +(8/17)"));	

		substitutionMethod.getFirstNameSolution();

		listSize = substitutionMethod.equationList.size();		
		lastEquation = substitutionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+1x = +(19/17)"));	
		
//		assertThat(lastEquation.toString(), is("+1x = +(51/17) -(32/17)"));	
//      assertThat(lastEquation.toString(), is("+1x = +(19/17)"));	        
	}
	
	@Ignore
	@Test
	public void multiplyByCrossedFactorsTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,name1).term(4,name2).equals().term(4)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);

		reductionMethod.multiplyByCrossedFactors(name1);

		int listSize = reductionMethod.equationList.size();

		Equation lastEquation3 = reductionMethod.equationList.get(listSize-3);
		assertThat(lastEquation3.toString(), is("+10x +20y = +20"));

		Equation lastEquation2 = reductionMethod.equationList.get(listSize-2);
		assertThat(lastEquation2.toString(), is("-10x +18y = +4"));

		Equation lastEquation = reductionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("-10x +18y +10x +20y = +4 +20"));
	}


}
