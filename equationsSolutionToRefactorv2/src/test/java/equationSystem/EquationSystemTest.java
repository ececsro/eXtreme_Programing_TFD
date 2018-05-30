package equationSystem;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EquationSystemTest {

	@Test
	public void reductionMethodTest() {
		double precission = 0.00001;
		String name1 = "x";
		String name2 = "y";
		EquationSystem equationSystem = new EquationSystemBuilder()
			.equation().term(2,name1).term(4,name2).equals().term(4)
			.equation().term(5,name1).term(-9,name2).equals().term(-2)
			.build();
		ReductionMethod reductionMethod = new ReductionMethod();
		
		equationSystem.set(reductionMethod);
		equationSystem.resolve();
		assertEquals(0.73684216, reductionMethod.getSolution(name1), precission);
		assertEquals(0.6315789, reductionMethod.getSolution(name2), precission);		
	}

}
