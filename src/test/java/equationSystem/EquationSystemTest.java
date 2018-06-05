package equationSystem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class EquationSystemTest {

	public void solutionMethod (EquationSystemTestData testData) {
		testData.equationSystem.set(testData.solutionMethod);
		
		testData.equationSystem.resolve();
		testData.expectedValueName1.simplify();
		assertThat(testData.expectedValueName1, equalTo(testData.solutionMethod.getSolution(testData.name1)));
		testData.expectedValueName2.simplify();
		assertThat(testData.expectedValueName2, equalTo(testData.solutionMethod.getSolution(testData.name2)));		
	}

	@Test
	public void substitutionMethodTest() {
		EquationSystemTestData testData = new EquationSystemTestData();

		testData.solutionMethod = new SubstitutionMethod();

		testData.name1 = "x";
		testData.name2 = "y";
		testData.expectedValueName1 = new Fraction(14,19);
		testData.expectedValueName2 = new Fraction(12,19);
		
		testData.equationSystem = new EquationSystemBuilder()
			.equation().term(2,1,testData.name1).term(4,1,testData.name2).equals().term(4,1)
			.equation().term(5,1,testData.name1).term(-9,1,testData.name2).equals().term(-2,1)
			.build();
		
		this.solutionMethod(testData);
	}

	@Test
	public void reductionMethodTest() {
		EquationSystemTestData testData = new EquationSystemTestData();

		testData.solutionMethod = new ReductionMethod();

		testData.name1 = "x";
		testData.name2 = "y";
		testData.expectedValueName1 = new Fraction(14,19);
		testData.expectedValueName2 = new Fraction(12,19);
		
		testData.equationSystem = new EquationSystemBuilder()
			.equation().term(2,1,testData.name1).term(4,1,testData.name2).equals().term(4,1)
			.equation().term(5,1,testData.name1).term(-9,1,testData.name2).equals().term(-2,1)
			.build();
		
		this.solutionMethod(testData);
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
	public void reductionMethod2Test() {
		EquationSystemTestData testData = new EquationSystemTestData();
		testData.solutionMethod = new ReductionMethod();
		this.solutionMethod(testData);
	}

	@Test
	public void SubstitutionMethod2Test() {
		EquationSystemTestData testData = new EquationSystemTestData();
		testData.solutionMethod  = new SubstitutionMethod();
		this.solutionMethod(testData);
	}

}
