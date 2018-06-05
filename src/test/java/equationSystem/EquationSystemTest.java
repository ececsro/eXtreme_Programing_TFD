package equationSystem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class EquationSystemTest {

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
	public void reductionMethod3ImposibleSolutionTest() {
		String name1 = "x";
		String name2 = "y";
		
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(1,1,name1).term(1,1,name2).equals().term(2,1)
			.equation().term(2,1,name1).term(2,1,name2).equals().term(4,1)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);
		equationSystem.resolve();
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

	@Test
	public void SubstitutionMethod4Test() {
		String name1 = "x";
		String name2 = "y";
		Fraction expectedName1 = new Fraction(-14,1);
		Fraction expectedName2 = new Fraction(8,1);
		
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,1,name1).term(4,1,name2).equals().term(4,1)
			.equation().term(5,1,name1).term(9,1,name2).equals().term(2,1)
			.build();
		SubstitutionMethod substitutionMethod = new SubstitutionMethod();
		
		equationSystem.set(substitutionMethod);
		equationSystem.resolve();
		expectedName1.simplify();
		assertThat(expectedName1.toString(), equalTo(substitutionMethod.getSolution(name1).toString()));
		expectedName2.simplify();
		assertThat(expectedName2.toString(), equalTo(substitutionMethod.getSolution(name2).toString()));		
	}

}
