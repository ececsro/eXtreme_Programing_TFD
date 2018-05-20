package equationSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExpressionTest {

	@Test
	public void addVariableToEmptyExpressionTest() {
		Variable variable = new Variable(3,"a");
		Expression expresion = new ExpressionBuilder().build();
		expresion.add(variable);
		assertEquals(variable.getValue(), expresion.getValue(variable.getName()), 0);
	}
	
	@Test
	public void addVariableToNotEmptyExpressionTest() {
		Variable variable = new Variable(-5,"z");
		Expression expresion= new ExpressionBuilder()
					.term(2,"x").term(-4,"x").term(100).term(-5,"y").build();
		expresion.add(variable);
		assertEquals(variable.getValue(), expresion.getValue(variable.getName()), 0);
	}
	
	@Test
	public void addRepeatedVariableToExpressionTest() {
		Variable variable = new Variable(19,"x");
		float valueRepeated = 7;
		Expression expression= new ExpressionBuilder()
					.term(valueRepeated,variable.getName()).build();
		expression.add(variable);
		float expectedValue = 19 + 7;
		assertEquals(expectedValue, expression.getValue(variable.getName()), 0);
	}
	
	@Test
	public void addConstantToEmptyExpressionTest() {
		Constant constant = new Constant(3);
		Expression expresion = new ExpressionBuilder().build();
		expresion.add(constant);
		assertEquals(constant.getValue(), expresion.getValue(), 0);
	}
	
	@Test
	public void addConstantToNotEmptyExpressionTest() {
		Constant constant = new Constant(-5);
		Expression expresion= new ExpressionBuilder()
					.term(2,"x").term(-4,"x").term(-5,"y").build();
		expresion.add(constant);
		assertEquals(constant.getValue(), expresion.getValue(), 0);
	}
	
	@Test
	public void addRepeatedConstantToExpressionTest() {
		Constant constant = new Constant(19);
		float valueRepeated = 7;
		Expression expression= new ExpressionBuilder().term(valueRepeated).build();
		expression.add(constant);
		float expectedValue = 19 + 7;
		assertEquals(expectedValue, expression.getValue(), 0);
	}
	
	@Test 
	public void addEmptyExpressionToEmptyExpressionTest(){
		Expression expression = new ExpressionBuilder().build();
		expression.add(new ExpressionBuilder().build());
		assertTrue(new ExpressionBuilder().build().equal(expression));
	}
	
	@Test 
	public void addEmptyExpressionToNotEmptyExpressionTest(){
		Expression empty = new ExpressionBuilder().build();
		Expression notEmpty = new ExpressionBuilder()
								.term(3,"x").term(4).term(6,"y").build();
		Expression result = notEmpty.clon();
		notEmpty.add(empty);
		assertTrue(result.equal(notEmpty));
	}
	
	@Test 
	public void addNotEmptyExpressionToEmptyExpressionTest(){
		Expression empty = new ExpressionBuilder().build();
		Expression notEmpty = new ExpressionBuilder()
								.term(3,"x").term(4).term(6,"y").build();
		Expression result = notEmpty.clon();
		empty.add(notEmpty);
		assertTrue(result.equal(empty));
	}
	
	@Test 
	public void addNotEmptyExpressionToNotEmptyExpressionTest(){
		Expression expression1 = new ExpressionBuilder()
								.term(31).term(4,"z").term(8,"x").build();
		Expression expression2 = new ExpressionBuilder()
								.term(3,"x").term(4).term(6,"y").build();
		expression1.add(expression2);
		Expression result = new ExpressionBuilder()
								.term(31).term(4,"z").term(8,"x")
								.term(3,"x").term(4).term(6,"y")
								.build();
		assertTrue(result.equal(expression1));
	}
	
	@Test 
	public void multiplyNotEmptyExpressionTest(){
		Expression expression = new ExpressionBuilder()
								.term(31).term(-4,"z").term(9,"x").build();
		float value = 3;
		expression.multiply(value);
		Expression result = new ExpressionBuilder()
								.term(93).term(-12,"z").term(27,"x").build();
		assertTrue(result.equal(expression));
	}
	
	@Test 
	public void simplifyNotRepeatedVariableTest(){
		Expression expression = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"z").term(27,"x").build();
		expression.simplify("z");
		Expression result = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"x").term(27,"z").build();
		assertTrue(result.equal(expression));
	}
	
	@Test 
	public void simplifyRepeatedVariableTest(){
		Expression expression = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"z").term(27,"x").build();
		expression.simplify("x");
		Expression result = new ExpressionBuilder()
								.term(93).term(27,"z").term(15,"x").build();
		assertTrue(result.equal(expression));
	}
	
	@Test 
	public void simplifyNotRepeatedConstantTest(){
		Expression expression = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"x").build();
		expression.simplify();
		Expression result = new ExpressionBuilder()
								.term(-12,"x").term(27,"x").term(93).build();
//		assertTrue(result.equal(expression));
		assertThat(result.toString(), is(expression.toString()));
	}
	
	@Test 
	public void simplifyRepeatedConstantTest(){
		Expression expression = new ExpressionBuilder()
								.term(93).term(-12,"x").term(-23).term(27,"x").build();
		expression.simplify();
		Expression result = new ExpressionBuilder()
								.term(-12,"x").term(27,"x").term(70).build();
		assertTrue(result.equal(expression));
	}
	
	@Test 
	public void simplifyNullConstantTest(){
		Expression expression = new ExpressionBuilder()
								.term(3).term(-1).term(2,"x").term(-1).term(-1).build();
		expression.simplify();
		Expression result = new ExpressionBuilder()
								.term(2,"x").build();
		assertThat(expression.toString(), is(result.toString()));
		assertTrue(result.equal(expression));
	}
	
	@Test 
	public void simplifyZeroConstantTest(){
		Expression expression = new ExpressionBuilder()
								.term(3).term(-1).term(-1).term(-1).build();
		expression.simplify();
		Expression result = new ExpressionBuilder()
								.term(0).build();
		assertThat(expression.toString(), is(result.toString()));
//		assertTrue(result.equal(expression));
	}
	
	@Test 
	public void getValueRepeatedVaribleTest(){
		Variable variable = new Variable(3,"x");
		Expression expression = new ExpressionBuilder()
									.term(93)
									.term(variable.getValue(), variable.getName())
									.term(-23)
									.term(27,variable.getName())
									.build();
		float expectedValue = 3 + 27;
		assertEquals(expectedValue, expression.getValue(variable.getName()),0);
	}
	
	@Test 
	public void getValueNotRepeatedVaribleTest(){
		Variable variable = new Variable(3,"x");
		Expression expression = new ExpressionBuilder()
									.term(93)
									.term(variable.getValue(), variable.getName())
									.term(-23)
									.term(27,"y")
									.build();
		assertEquals(variable.getValue(), expression.getValue(variable.getName()),0);
	}
	
	@Test 
	public void getValueRepeatedConstantTest(){
		Constant constant = new Constant(3);
		Expression expression = new ExpressionBuilder()
									.term(constant.getValue())
									.term(93)
									.term(27,"y")
									.term(-23)
									.build();
		float expectedValue = 3 + 93 - 23;
		assertEquals(expectedValue, expression.getValue(),0);
	}
	
	@Test 
	public void getValueNotRepeatedConstantTest(){
		Constant constant = new Constant(3);
		Expression expression = new ExpressionBuilder()
									.term(27,"y")
									.term(constant.getValue())
									.term(1,"z")
									.term(2,"y")
									.build();
		assertEquals(constant.getValue(), expression.getValue(),0);
	}
	
	@Test 
	public void getNameSetTest(){
		String[] names = new String[]{"x", "y"};
		Expression expression = new ExpressionBuilder()
									.term(27,names[0])
									.term(1,names[1])
									.term(2,names[0])
									.build();
		Set<String> nameSet = new HashSet<String>();
		for(String name : names){
			nameSet.add(name);
		}
		assertEquals(nameSet, expression.getNameSet());
	}
	
	@Test 
	public void getNameSetEmptyTest(){
		Expression expression = new ExpressionBuilder()
									.term(27).term(2).build();
		Set<String> nameSet = new HashSet<String>();
		assertEquals(nameSet, expression.getNameSet());
	}
	
	@Test
	public void hasNameTest() {
		String name = "x";
		Expression expression = new ExpressionBuilder()
								.term(-8).term(27,name).term(2).build();
		assertTrue(expression.hasName(name));
	}
	
	@Test
	public void notHasNameTest() {
		String name = "x";
		Expression expression = new ExpressionBuilder()
								.term(-8).term(27,"y").term(2).build();
		assertFalse(expression.hasName(name));
	}
	
	@Test
	public void applyTest() {
		float value = 4;
		String name = "y";
		float appliedValue = 3;
		Expression expression = new ExpressionBuilder()
								.term(-8,"x").term(value,name).term(2).build();
		expression.apply(name, appliedValue);
		Expression result = new ExpressionBuilder()
								.term(-8,"x").term(2).term(value*appliedValue).build();
		assertTrue(result.equal(expression));
	}
	
	@Test
	public void apply2Test() {
		Expression expression = new ExpressionBuilder()
								.term(-2, "x").term(3, "y").term(6).build();
		expression.apply("x",10);
		Expression result = new ExpressionBuilder()
								.term(3, "y").term(6).term(-20).build();
		assertTrue(result.equal(expression));
	}
	
	@Test
	public void equalTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"x").build();
		Expression expression2 = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"x").build();
		assertTrue(expression1.equal(expression2));
	}

	@Test
	public void equalOnlyConstantTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-1).build();
		Expression expression2 = new ExpressionBuilder()
								.term(1).term(-2).build();
		assertTrue(expression1.equal(expression2));
	}

	@Test
	public void equalOnlyConstantZeroValueTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-1).term(1).build();
		Expression expression2 = new ExpressionBuilder()
								.term(2).term(-2).build();
		assertTrue(expression1.equal(expression2));
	}

	@Test
	public void equalOnlyVariablesTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-1,"x").term(-2,"y").build();
		Expression expression2 = new ExpressionBuilder()
								.term(-1,"x").term(-2,"y").build();
		assertTrue(expression1.equal(expression2));
	}

	@Test
	public void equalOnlyVariablesZeroValueTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-1,"x").term(1,"x").build();
		Expression expression2 = new ExpressionBuilder()
								.term(2,"x").term(-2,"x").build();
		assertTrue(expression1.equal(expression2));
	}

	@Test
	public void equalTermsInDifferentOrderTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"x").build();
		Expression expression2 = new ExpressionBuilder()
								.term(-12,"x").term(27,"x").term(93).build();
		assertTrue(expression1.equal(expression2));
	}

	@Test
	public void equalTermsInDifferentOrder2VariablesTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"y").build();
		Expression expression2 = new ExpressionBuilder()
								.term(-12,"x").term(27,"y").term(93).build();
		assertTrue(expression1.equal(expression2));
	}

	@Test
	public void equalExpressionsAreEquivalentTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(6).term(1,"x").term(1,"x").term(1,"x").term(20,"y").build();
		Expression expression2 = new ExpressionBuilder()
								.term(3,"x").term(10,"y").term(10,"y").term(2).term(4).build();
		assertTrue(expression1.equal(expression2));
	}
	

	@Test
	public void notEqualConstantTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-12.1234f).term(27).build();
		Expression expression2 = new ExpressionBuilder()
								.term(99).term(99.99999f).build();
		assertFalse(expression1.equal(expression2));
	}

	@Test
	public void notEqualOneVariableTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-12.1234f,"x").term(27,"x").build();
		Expression expression2 = new ExpressionBuilder()
								.term(99,"x").term(99.99999f,"x").build();
		assertFalse(expression1.equal(expression2));
	}

	@Test
	public void notEqualConstantIsDifferentTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-12.1234f).term(27,"x").term(18,"y").build();
		Expression expression2 = new ExpressionBuilder()
								.term(99).term(27,"x").term(18,"y").build();
		assertFalse(expression1.equal(expression2));
	}

	@Test
	public void notEqualVariableIsDifferentTest() {
		Expression expression1 = new ExpressionBuilder()
								.term(-12.1234f).term(27,"x").term(18,"y").build();
		Expression expression2 = new ExpressionBuilder()
								.term(-12.1234f).term(27,"x").term(99,"y").build();
		assertFalse(expression1.equal(expression2));
	}
	@Test
	public void clonTest() {
		Expression expression = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"x").build();
		assertTrue(expression.equal(expression.clon()));
	}
	
	@Test
	public void toStringTest() {
		Expression expression = new ExpressionBuilder()
								.term(93).term(-12,"x").term(27,"x").build();
		assertEquals("+93 -12x +27x", expression.toString());
	}
	
	@Test
	public void emptyToStringTest() {
		Expression expression = new ExpressionBuilder().build();
		assertEquals("", expression.toString());
	}
	
}
