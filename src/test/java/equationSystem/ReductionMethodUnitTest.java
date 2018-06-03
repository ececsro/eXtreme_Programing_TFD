package equationSystem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class ReductionMethodUnitTest {

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

	@Test
	public void simplifyFirstNameTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,name1).term(4,name2).equals().term(4)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);

		reductionMethod.multiplyByCrossedFactors(name1);
		reductionMethod.simplifyFirstName(name1, name2);
		
		int listSize = reductionMethod.equationList.size();

		Equation lastEquation3 = reductionMethod.equationList.get(listSize-3);
		assertThat(lastEquation3.toString(), is("+18y +20y = +4 +20"));

		Equation lastEquation2 = reductionMethod.equationList.get(listSize-2);
		assertThat(lastEquation2.toString(), is("+38y = +4 +20"));

		Equation lastEquation = reductionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+38y = +24"));
	}

	@Test
	public void getSecondNameSolutionTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,name1).term(4,name2).equals().term(4)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);

		reductionMethod.multiplyByCrossedFactors(name1);
		reductionMethod.simplifyFirstName(name1, name2);
		reductionMethod.getSecondNameSolution(name2);
		
		int listSize = reductionMethod.equationList.size();

		Equation lastEquation = reductionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+1y = +(12/19)"));		
	}

	@Test
	public void simplifySecondNameTest() {
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,name1).term(4,name2).equals().term(4)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);

		reductionMethod.multiplyByCrossedFactors(name1);
		reductionMethod.simplifyFirstName(name1, name2);
		reductionMethod.getSecondNameSolution(name2);
		reductionMethod.simplifySecondName(name2);
		
		int listSize = reductionMethod.equationList.size();

		Equation lastEquation4 = reductionMethod.equationList.get(listSize-4);
		assertThat(lastEquation4.toString(), is("+2x +(48/19) = +4"));

		Equation lastEquation3 = reductionMethod.equationList.get(listSize-3);
		assertThat(lastEquation3.toString(), is("+2x +(48/19) -(48/19) = +4 -(48/19)"));

		Equation lastEquation2 = reductionMethod.equationList.get(listSize-2);
		assertThat(lastEquation2.toString(), is("+2x = +4 -(48/19)"));

		Equation lastEquation = reductionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("+2x = +(28/19)"));		
	}

	@Test
	public void reductionMethod2Test() {
		String name1 = "x";
		String name2 = "y";
		Fraction expectedName1 = new Fraction(14,19);
		Fraction expectedName2 = new Fraction(12,19);
		
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,1,name1).term(4,1,name2).equals().term(4,1)
			.equation().term(5,1,name1).term(-9,1,name2).equals().term(-2,1)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);
		equationSystem.resolve();
		expectedName1.simplify();
		assertThat(expectedName1, equalTo(reductionMethod.getSolution(name1)));
		expectedName2.simplify();
		assertThat(expectedName2, equalTo(reductionMethod.getSolution(name2)));		
	}

	@Test(expected=AssertionError.class)
	public void reductionMethod3ImposibleResolutionTest() {
		String name1 = "x";
		String name2 = "y";
		Fraction expectedName1 = new Fraction(1,1);
		Fraction expectedName2 = new Fraction(1,1);
		
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(1,1,name1).term(1,1,name2).equals().term(2,1)
			.equation().term(2,1,name1).term(2,1,name2).equals().term(4,1)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);
		reductionMethod.multiplyByCrossedFactors(name1);

		int listSize = reductionMethod.equationList.size();

		Equation lastEquation3 = reductionMethod.equationList.get(listSize-3);
		assertThat(lastEquation3.toString(), is("+2x +2y = +4"));

		Equation lastEquation2 = reductionMethod.equationList.get(listSize-2);
		assertThat(lastEquation2.toString(), is("-2x -2y = -4"));

		Equation lastEquation = reductionMethod.equationList.get(listSize-1);
		assertThat(lastEquation.toString(), is("-2x -2y +2x +2y = -4 +4"));
		
		reductionMethod.simplifyFirstName(name1, name2);
		
		equationSystem.resolve();
		expectedName1.simplify();
		assertThat(expectedName1, equalTo(reductionMethod.getSolution(name1)));
		expectedName2.simplify();
		assertThat(expectedName2, equalTo(reductionMethod.getSolution(name2)));		
	}

	@Test
	public void reductionMethod4Test() {
		String name1 = "x";
		String name2 = "y";
		Fraction expectedName1 = new Fraction(-14,1);
		Fraction expectedName2 = new Fraction(8,1);
		
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,1,name1).term(4,1,name2).equals().term(4,1)
			.equation().term(5,1,name1).term(9,1,name2).equals().term(2,1)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);
		equationSystem.resolve();
		expectedName1.simplify();
		assertThat(expectedName1, equalTo(reductionMethod.getSolution(name1)));
		expectedName2.simplify();
		assertThat(expectedName2, equalTo(reductionMethod.getSolution(name2)));		
	}

}
