package equationSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class EquationTest {

	@Test
	public void equalTest() {
		Equation equation1 = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		Equation equation2 = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		assertTrue(equation1.equal(equation2));
	}

	@Test
	public void notEqualTest() {
		Equation equation1 = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		Equation equation2 = new EquationBuilder().term(3, "y").term(-2, "x")
				.equals().term(6).build();
		assertFalse(equation1.equal(equation2));
	}

	@Test
	public void multiplyTest() {
		Equation equation = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		float value = 5;
		equation.multiply(value);
		Equation result = new EquationBuilder().term(-10, "x").term(15, "y")
				.equals().term(30).build();
		assertTrue(result.equal(equation));
	}

	@Test
	public void addVariableTest() {
		Equation equation = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		Variable variable = new Variable(-7, "z");
		equation.add(Side.LEFT, variable);
		Equation result = new EquationBuilder().term(-2, "x").term(3, "y")
				.term(-7, "z").equals().term(6).build();
		assertTrue(result.equal(equation));
	}

	@Test
	public void addEquationTest() {
		Equation equation1 = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		Equation equation2 = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		equation1.add(equation2);
		Equation result = new EquationBuilder().term(-2, "x").term(3, "y")
				.term(-2, "x").term(3, "y").equals().term(6).term(6).build();
		assertTrue(result.equal(equation1));
	}

	@Test
	public void getNameSetTest() {
		Equation equation1 = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		Set<String> result = new HashSet<String>();
		result.add("x");
		result.add("y");
		assertEquals(result, equation1.getNameSet());
	}

	@Test
	public void negativeGetNameSetTest() {
		Equation equation1 = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		Set<String> result = new HashSet<String>();
		result.add("z");
		result.add("y");
		assertNotEquals(result, equation1.getNameSet());
	}

	@Test
	public void clonTest() {
		Equation equation = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		Equation result = equation.clon();
		assertTrue(result.equal(equation));
	}

	@Test
	public void invertTest() {
		Equation equation = new EquationBuilder().term(-2, "x").term(3, "y")
				.equals().term(6).build();
		equation.invert();
		Equation result = new EquationBuilder().term(6).equals().term(-2, "x")
				.term(3, "y").build();
		assertTrue(result.equal(equation));
	}

	@Test
	public void applyTest() {
		Equation equation = new EquationBuilder()
								.term(-2, "x").term(3, "y").equals().term(6).build();
		equation.apply("x",10);
		Equation result = new EquationBuilder()
								.term(3, "y").term(-20).equals().term(6).build();
		assertTrue(result.equal(equation));
	}

}
