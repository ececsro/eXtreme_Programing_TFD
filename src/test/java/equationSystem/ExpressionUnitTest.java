package equationSystem;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class ExpressionUnitTest {

		private String nameX = "x";
		private String nameY = "y";
		private String nameZ = "z";
		private double closeGap = 0.0001;	
	
	@Test
	public void GivenEmpty_executeEmpty_checkIsTrue() {
		Expression exprSUT = new Expression();
		assertTrue(exprSUT.empty());
	}	

	@Test
	public void GivenNotEmpty_executeEmpty_checkIsFalse() {
		Expression exprSUT = new Expression();
		Term termAux=new ConstantTestBuilder().build();
		exprSUT.add(termAux);
		
		assertFalse(exprSUT.empty());
		assertTrue(exprSUT.equal(exprSUT));
	}	

	@Test
	public void GivenEmpty_addTermConstant() {
		Expression exprSUT = new Expression();
		Term termAux=new ConstantTestBuilder().
				constantValue(-5,-1).build();
		exprSUT.add(termAux);

		Fraction expectedFracConstant = new Fraction(5,1);
		assertEquals(exprSUT.getValue().toString(), expectedFracConstant.toString());
	}	

	@Test
	public void GivenEmpty_addTermVariable() {
		Expression exprSUT = new Expression();
		Term termAux=new VariableTestBuilder().
				constantValue(-5,1).nameValue(nameX).build();
		exprSUT.add(termAux);

		Variable expectedValue = new Variable(-5,1,nameX);
		assertThat((expectedValue.getValue().toString()), is(equalTo(exprSUT.getValue(nameX).toString())));
	}		

	@Test
	public void GivenEmpty_addExpression() {

		Expression exprSUT = new Expression();

		List<TermForTest> expressionAdded = new ArrayList<TermForTest>();

		expressionAdded.add(new TermForTest(72,10, nameX));
		expressionAdded.add(new TermForTest(72,-10));
		expressionAdded.add(new TermForTest(72,10, nameZ));

		Expression exprAdded = new ExpressionTestBuilder().
				addExpression(expressionAdded).build();

		String expectedToString = "";
		for (TermForTest term : expressionAdded) {
			expectedToString = expectedToString + " " + term.toString();
		}

		exprSUT.add(exprAdded);
		
		assertThat (exprSUT.toString(), is(expectedToString.trim()));
	}		

	@Test
	public void GivenExpression_addExpression() {

		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(72,10));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-5,-1, nameX));
		expressionTest.add(new TermForTest(5,1));
		expressionTest.add(new TermForTest(72,10, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		
		List<TermForTest> expressionAdded = new ArrayList<TermForTest>();

		expressionAdded.add(new TermForTest(72,10, nameX));
		expressionAdded.add(new TermForTest(-72,10));
		expressionAdded.add(new TermForTest(-72,-10, nameZ));

		Expression exprAdded = new ExpressionTestBuilder().
				addExpression(expressionAdded).build();

		String expectedToString = "";

		for (TermForTest term : expressionTest) {
			expectedToString = expectedToString + " " + term.toString();
		}

		for (TermForTest term : expressionAdded) {
			expectedToString = expectedToString + " " + term.toString();
		}

		exprSUT.add(exprAdded);
		
		assertThat (exprSUT.toString(), equalTo(expectedToString.trim()));
	}		


	/* One expression can have 1 or more term constants and/or 1 or more term variables */
	/* Equivalence class:
	 * 		constant   variables   ¿test?  
	 * 		1			0			yes
	 * 		0			1			yes
	 * 		+1			0			no
	 * 		0			+1			no
	 * 		+1			+1			yes
	 */
	/* Entry value is float: Equivalence class is the limit of float */
	@Test
	public void Given1cons0var_multiply() {
		Expression exprSUT = new Expression();
		Term termConstant=new ConstantTestBuilder().
				constantValue(7,10).build();
		exprSUT.add(termConstant);
		exprSUT.multiply((float)-7);
		float expectedConstantValue = (float)(7 * -7);

		Fraction expectedFracConstant = new Fraction(-49,10);
		assertEquals(exprSUT.getValue(), expectedFracConstant);
	}		

	@Test
	public void Given0cons1var_multiply() {
		Expression exprSUT = new Expression();
		Term termVariable=new VariableTestBuilder().
				constantValue(5,1).nameValue(nameX).build();
		exprSUT.add(termVariable);
		exprSUT.multiply(7);
		float expectedConstantValue = (5 * 7);
		
	    Variable expectedValue = new Variable(5*7, 1,nameX);
		assertThat(expectedValue.getValue().toString(), is(exprSUT.getValue(nameX).toString()));
	}		

	@Test
	public void Given2cons3var_multiply() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(72,10));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(5,1, nameX));
		expressionTest.add(new TermForTest(70,10, nameX));
		expressionTest.add(new TermForTest(-7,1, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.multiply(7);
		
		float expectedConstantValue = (float)((7.2 + -7.2) * 7);
		float expectedNameValue1 = (float)((5 + 7) * 2);
		float expectedNameValue2 = (float)(-7 * 7);
		
		Fraction expectedFracConstant = new Fraction(0,1);
		assertEquals(exprSUT.getValue(), expectedFracConstant);
		Fraction expectedFracX = new Fraction(84,1);
		assertEquals(exprSUT.getValue(nameX), expectedFracX);
		Fraction expectedFracY = new Fraction(-49,1);
		assertEquals(exprSUT.getValue(nameY), expectedFracY);
	}		

	@Test
	public void Given0cons1var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();		
		expressionTest.add(new TermForTest(5,1, nameX));

		String expectedToString = expressionTest.get(0).toString();

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();

		Fraction expectedFracConstant = new Fraction(0,10);
		assertEquals(exprSUT.getValue(), expectedFracConstant);
		Fraction expectedFracX = new Fraction(5,1);
		assertEquals(exprSUT.getValue(nameX), expectedFracX);

		assertThat(exprSUT.toString(), equalTo(expectedToString));
	}		

	@Test
	public void Given1cons0var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();		
		expressionTest.add(new TermForTest(72,10).simplify());

		String expectedToString = expressionTest.get(0).toString();
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();
		
		Fraction expectedFracConstant = new Fraction(72,10);
		assertEquals(exprSUT.getValue(), expectedFracConstant);
		assertThat(exprSUT.toString(), equalTo(expectedToString));
	}
	
	@Test
	public void Given4cons3var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(72,10));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(5,1, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));

		float expectedConstantValue = (float)
				(7.2 + -7.2 + -5 + -7.2);		
		List<TermForTest> expectedExpression = new ArrayList<TermForTest>();

		TermForTest expectedTerm = new TermForTest (expectedConstantValue);
		expectedTerm.simplify();
		
		expectedExpression.add(expressionTest.get(2));
		expectedExpression.add(expressionTest.get(4));
		expectedExpression.add(expressionTest.get(6));
		expectedExpression.add(expectedTerm);

		String expectedToString = "";
		for (TermForTest term : expectedExpression) {
			expectedToString = expectedToString + " " + term.toString();
		}

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();

		assertThat(exprSUT.toString(), equalTo(expectedToString.trim()));
	}		

	@Test
	public void Given2cons3varX2varY1varZ_simplifyVariableX() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameZ));

		float expectedNameX = (float)
				(-5 + -7.2 + 7.2);
		
		List<TermForTest> expectedExpression = new ArrayList<TermForTest>();

		TermForTest expectedTerm = new TermForTest (expectedNameX,nameX);
		
		expectedExpression.add(expressionTest.get(2));
		expectedExpression.add(expressionTest.get(4));
		expectedExpression.add(expressionTest.get(5));
		expectedExpression.add(expressionTest.get(6));
		expectedExpression.add(expectedTerm);

		String expectedToString = "";
		for (TermForTest term : expectedExpression) {
			expectedToString = expectedToString + " " + term.toString();
		}
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify(nameX);

		assertThat(exprSUT.toString(), equalTo(expectedToString.trim()));
	}		

	@Test
	public void GivenFullExpression_2constant_getValueConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameZ));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		Fraction expectedFracConstant = new Fraction(-122,10);
		assertEquals(exprSUT.getValue(), expectedFracConstant);
	}

	@Test
	public void GivenFullExpression_3varX_getValueX() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameZ));

		Variable expectedFraction = new Variable(-5, 1, nameX);
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat(exprSUT.getValue(nameX), is(expectedFraction.getValue()));
	}

	@Test
	public void GivenFullExpression_1varZ_getValueZ() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameZ));

		Variable expectedFraction = new Variable(-5, 1, nameZ);

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		assertThat(exprSUT.getValue(nameZ), is(expectedFraction.getValue()));
	}

	@Test
	public void Given2cons0var_getNameSet() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(5,-1));
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.getNameSet().size(), is(0));
	}

	@Test
	public void Given1var_getNameSet() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.getNameSet(), containsInAnyOrder(nameX));
	}

	@Test
	public void Given3var_getNameSet() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameZ));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.getNameSet(), containsInAnyOrder(nameX,nameY,nameZ));
	}

	@Test
	public void Given2cons0var_hasNameX_false() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(-5,1));
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.hasName(nameX), is(false));
	}

	@Test
	public void Given2var_hasNameZ_false() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.hasName(nameZ), is(false));
	}

	@Test
	public void Given3var_hasNameZ_true() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameZ));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.hasName(nameZ), is(true));
	}

	@Ignore
	@Test
	public void Given0Term_clon() {

		Expression exprSUT = new Expression();

		Expression exprExpected = exprSUT.clon();
		
		assertThat( exprSUT.equal(exprExpected), is(true));
	}

	@Test
	public void Given1Term_clon() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		Expression exprExpected = exprSUT.clon();
		assertThat( exprSUT.equal(exprExpected), is(true));
	}

	@Test
	public void Given7Terms_clon() {

		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(-5,1, nameX));
		expressionTest.add(new TermForTest(-72,10, nameX));
		expressionTest.add(new TermForTest(-5,1));
		expressionTest.add(new TermForTest(72,10, nameX));
		expressionTest.add(new TermForTest(-72,10));
		expressionTest.add(new TermForTest(-72,10, nameY));
		expressionTest.add(new TermForTest(-5,1, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		Expression exprExpected = exprSUT.clon();

		Variable expectedTerm0 = new Variable(-5,1,nameX);
		assertThat(exprExpected.termList.get(0).getValue(), is(expectedTerm0.getValue()));
		assertThat(exprExpected.termList.get(0).getName(), is(expectedTerm0.getName()));

		Variable expectedTerm1 = new Variable(-72,10,nameX);
		assertThat(exprExpected.termList.get(1).getValue(), is(expectedTerm1.getValue()));
		assertThat(exprExpected.termList.get(1).getName(), is(expectedTerm1.getName()));

		Constant expectedTerm2 = new Constant(-5,1);
		assertThat(exprExpected.termList.get(2).getValue(), is(expectedTerm2.getValue()));
		assertThat(exprExpected.termList.get(2).getName(), is(expectedTerm2.getName()));

		Variable expectedTerm3 = new Variable(72,10,nameX);
		assertThat(exprExpected.termList.get(3).getValue(), is(expectedTerm3.getValue()));
		assertThat(exprExpected.termList.get(3).getName(), is(expectedTerm3.getName()));

		Constant expectedTerm4 = new Constant(-72,10);
		assertThat(exprExpected.termList.get(4).getValue(), is(expectedTerm4.getValue()));
		assertThat(exprExpected.termList.get(4).getName(), is(expectedTerm4.getName()));

		Variable expectedTerm5 = new Variable(-72,10,nameY);
		assertThat(exprExpected.termList.get(5).getValue(), is(expectedTerm5.getValue()));
		assertThat(exprExpected.termList.get(5).getName(), is(expectedTerm5.getName()));

		Variable expectedTerm6 = new Variable(-5,1,nameY);
		assertThat(exprExpected.termList.get(6).getValue(), is(expectedTerm6.getValue()));
		assertThat(exprExpected.termList.get(6).getName(), is(expectedTerm6.getName()));
	
		assertThat( exprSUT.equal(exprExpected), is(true));
	}

	@Test
	public void notEqualExpr1WithConstant_Expr2WithVariablesTest() {
 
		Fraction fracValue = new Fraction(5,2);
		
		Constant value1 = new Constant(fracValue);
		Variable value2 = new Variable(fracValue,"x");

		Expression exprSUT1 = new Expression();
		exprSUT1.add(value1);
		
		Expression exprSUT2 = new Expression();
		exprSUT2.add(value2);
		
		assertThat( exprSUT1.equal(exprSUT2), is(false));
	}

	@Test
	public void equalExpr1WithConstant_Expr2WithConstantTest() {
 
		Fraction fracValue = new Fraction(5,2);
		
		Constant value = new Constant(fracValue);

		Expression exprSUT1 = new Expression();
		exprSUT1.add(value);
		
		Expression exprSUT2 = new Expression();
		exprSUT1.add(value);
		
		assertThat( exprSUT1.equal(exprSUT2), is(true));
	}
}
