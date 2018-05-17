package equations;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
import org.junit.Test;

//@RunWith(Parameterized.class)
public class ExpressionUnitTest {

/*		@Parameters()
		public static Collection<Object[]> data(){
			
		    Object[][] data = {
		    	{ 5, -5, 7.2 , -7.2 , "x", "y", "z", 0.0001,null, "X", true,  0, 1, 2 },
		    };
		
		    return Arrays.asList(data);
		}
				
		@Parameter(0) public double intPositive;
		@Parameter(1) public double intNegative;
		@Parameter(2) public double decimalPositive;
		@Parameter(3) public double decimalNegative;
		@Parameter(4) public String nameX;
		@Parameter(5) public String nameY;
		@Parameter(6) public String nameZ;
		@Parameter(7) public double closeGap;
		@Parameter(8) public String cellVal8;
		@Parameter(9) public String playerLabel;
		@Parameter(10) public boolean winResult;
		@Parameter(11) public int winResult1;
		@Parameter(12) public int winResult2;
		@Parameter(13) public int winResult3;
*/		

		private double intPositive = 5;
		private double intNegative = -5 ;
		private double decimalPositive = 7.2;
		private double decimalNegative = -7.2;
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
	public void GivenNotEmpty_executeEmpty_checkIsEmpty_checkIsFalse() {
		Expression exprSUT = new Expression();
		Term termAux=new ConstantTestBuilder().build();
		exprSUT.add(termAux);
		
		assertFalse(exprSUT.empty());
		assertTrue(exprSUT.equal(new Expression()));
	}	

	@Test
	public void GivenEmpty_addTermConstant() {
		Expression exprSUT = new Expression();
		Term termAux=new ConstantTestBuilder().
				constantValue(intPositive).build();
		exprSUT.add(termAux);
		
		assertThat((double) exprSUT.getValue(), closeTo(intPositive,closeGap));		
	}	

	@Test
	public void GivenEmpty_addTermVariable() {
		Expression exprSUT = new Expression();
		Term termAux=new VariableTestBuilder().
				constantValue(intNegative).nameValue(nameX).build();
		exprSUT.add(termAux);
		
		assertThat((double) exprSUT.getValue(nameX), closeTo(intNegative,closeGap));
	}		

	@Test
	public void GivenEmpty_addExpression() {

		Expression exprSUT = new Expression();

		List<TermForTest> expressionAdded = new ArrayList<TermForTest>();

		expressionAdded.add(new TermForTest(decimalPositive, nameX));
		expressionAdded.add(new TermForTest(decimalNegative));
		expressionAdded.add(new TermForTest(decimalPositive, nameZ));

		Expression exprAdded = new ExpressionTestBuilder().
				addExpression(expressionAdded).build();

		String expectedToString = "";
		for (TermForTest term : expressionAdded) {
			expectedToString = expectedToString + term.toString();
		}

		exprSUT.add(exprAdded);
		
		assertThat (exprSUT.toString(), equalTo(expectedToString));
		

	}		

	@Test
	public void GivenExpression_addExpression() {

		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(decimalPositive));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(intPositive, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		
		List<TermForTest> expressionAdded = new ArrayList<TermForTest>();

		expressionAdded.add(new TermForTest(decimalPositive, nameX));
		expressionAdded.add(new TermForTest(decimalNegative));
		expressionAdded.add(new TermForTest(decimalPositive, nameZ));

		Expression exprAdded = new ExpressionTestBuilder().
				addExpression(expressionAdded).build();

		String expectedToString = "";

		for (TermForTest term : expressionTest) {
			expectedToString = expectedToString + term.toString();
		}

		for (TermForTest term : expressionAdded) {
			expectedToString = expectedToString + term.toString();
		}

		exprSUT.add(exprAdded);
		
		assertThat (exprSUT.toString(), equalTo(expectedToString));
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
				constantValue(decimalPositive).build();
		exprSUT.add(termConstant);
		exprSUT.multiply((float)decimalNegative);
		float expectedConstantValue = (float)(decimalPositive * decimalNegative);
		
		assertThat((double) exprSUT.getValue(), closeTo(expectedConstantValue,closeGap));
	}		

	@Test
	public void Given0cons1var_multiply() {
		Expression exprSUT = new Expression();
		Term termVariable=new VariableTestBuilder().
				constantValue(intPositive).nameValue(nameX).build();
		exprSUT.add(termVariable);
		exprSUT.multiply((float)decimalPositive);
		float expectedConstantValue = (float)(intPositive * decimalPositive);
		
		assertThat((double) exprSUT.getValue(nameX), closeTo(expectedConstantValue,closeGap));
	}		

	@Test
	public void Given2cons3var_multiply() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(decimalPositive));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(intPositive, nameX));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.multiply((float)decimalPositive);
		
		float expectedConstantValue = (float)((decimalPositive + decimalNegative) * decimalPositive);
		float expectedNameValue1 = (float)((intPositive + decimalPositive) * decimalPositive);
		float expectedNameValue2 = (float)(decimalNegative * decimalPositive);
		
		assertThat((double) exprSUT.getValue(), closeTo(expectedConstantValue,closeGap));
		assertThat((double) exprSUT.getValue(nameX), closeTo(expectedNameValue1,closeGap));
		assertThat((double) exprSUT.getValue(nameY), closeTo(expectedNameValue2,closeGap));
	}		

	@Test
	public void Given0cons1var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();		
		expressionTest.add(new TermForTest(intPositive, nameX));

		String expectedToString = expressionTest.get(0).toString();

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();
				
		assertThat((double) exprSUT.getValue(), closeTo(0 , closeGap));
		assertThat((double) exprSUT.getValue(nameX), closeTo(intPositive, closeGap));

		assertThat(exprSUT.toString(), equalTo(expectedToString));
	}		

	@Test
	public void Given1cons0var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();		
		expressionTest.add(new TermForTest(decimalPositive));

		String expectedToString = expressionTest.get(0).toString();
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();
		
		assertThat((double) exprSUT.getValue(), closeTo(decimalPositive,closeGap));
		assertThat(exprSUT.toString(), equalTo(expectedToString));
	}
	
	@Test
	public void Given4cons3var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(decimalPositive));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(intPositive, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));

		float expectedConstantValue = (float)
				(decimalPositive + decimalNegative + intNegative + decimalNegative);		
		List<TermForTest> expectedExpression = new ArrayList<TermForTest>();

		TermForTest expectedTerm = new TermForTest (expectedConstantValue);
		
		expectedExpression.add(expressionTest.get(2));
		expectedExpression.add(expressionTest.get(4));
		expectedExpression.add(expressionTest.get(6));
		expectedExpression.add(expectedTerm);

		String expectedToString = "";
		for (TermForTest term : expectedExpression) {
			expectedToString = expectedToString + term.toString();
		}

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();

		assertThat(exprSUT.toString(), equalTo(expectedToString));
	}		

	@Test
	public void Given2cons3varX2varY1varZ_simplifyVariableX() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameZ));

		float expectedNameX = (float)
				(intNegative + decimalNegative + decimalPositive);
		
		List<TermForTest> expectedExpression = new ArrayList<TermForTest>();

		TermForTest expectedTerm = new TermForTest (expectedNameX,nameX);
		
		expectedExpression.add(expressionTest.get(2));
		expectedExpression.add(expressionTest.get(4));
		expectedExpression.add(expressionTest.get(5));
		expectedExpression.add(expressionTest.get(6));
		expectedExpression.add(expectedTerm);

		String expectedToString = "";
		for (TermForTest term : expectedExpression) {
			expectedToString = expectedToString + term.toString();
		}

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify(nameX);

		assertThat(exprSUT.toString(), equalTo(expectedToString));
	}		

	@Test
	public void GivenFullExpression_2constant_getValueConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameZ));

		float expectedConstant = (float)
				(intNegative + decimalNegative);
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat((double) exprSUT.getValue(), closeTo(expectedConstant,closeGap));
	}

	@Test
	public void GivenFullExpression_3varX_getValueX() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameZ));

		float expectedConstant = (float)
				(intNegative + decimalNegative + decimalPositive);
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat((double) exprSUT.getValue(nameX), closeTo(expectedConstant,closeGap));
	}

	@Test
	public void GivenFullExpression_1varZ_getValueZ() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameZ));

		float expectedConstant = (float) (intNegative);
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		assertThat((double) exprSUT.getValue(nameZ), closeTo(expectedConstant,closeGap));
	}

	@Test
	public void Given2cons0var_getNameSet() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(intNegative));
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		
		assertThat( exprSUT.getNameSet(), is(empty()));
	}

	@Test
	public void Given1var_getNameSet() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.getNameSet(), containsInAnyOrder(nameX));
	}

	@Test
	public void Given3var_getNameSet() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameZ));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.getNameSet(), containsInAnyOrder(nameX,nameY,nameZ));
	}

	@Test
	public void Given2cons0var_hasNameX_false() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(intNegative));
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.hasName(nameX), is(false));
	}

	@Test
	public void Given2var_hasNameZ_false() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.hasName(nameZ), is(false));
	}

	@Test
	public void Given3var_hasNameZ_true() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameZ));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		assertThat( exprSUT.hasName(nameZ), is(true));
	}

	@Test
	public void Given0Term_clone() {

		Expression exprSUT = new Expression();

		Expression exprExpected = exprSUT.clone();
		
		assertThat( exprSUT.equal(exprExpected), is(true));
	}

	@Test
	public void Given1Term_clone() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		Expression exprExpected = exprSUT.clone();
		assertThat( exprSUT.equal(exprExpected), is(true));
	}

	@Test
	public void Given7Terms_clone() {

		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intNegative, nameX));
		expressionTest.add(new TermForTest(decimalNegative, nameX));
		expressionTest.add(new TermForTest(intNegative));
		expressionTest.add(new TermForTest(decimalPositive, nameX));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(decimalNegative, nameY));
		expressionTest.add(new TermForTest(intNegative, nameY));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();

		Expression exprExpected = exprSUT.clone();
		
		assertThat((double) exprExpected.getExpression().get(0).getValue(), closeTo(intNegative,closeGap));
		assertThat(exprExpected.getExpression().get(0).getName(), is(nameX));
		assertThat((double) exprExpected.getExpression().get(1).getValue(), closeTo(decimalNegative,closeGap));
		assertThat(exprExpected.getExpression().get(1).getName(), is(nameX));
		assertThat((double) exprExpected.getExpression().get(2).getValue(), closeTo(intNegative,closeGap));
		assertThat(exprExpected.getExpression().get(2).getName(), is(nullValue()));
		assertThat((double) exprExpected.getExpression().get(3).getValue(), closeTo(decimalPositive,closeGap));
		assertThat(exprExpected.getExpression().get(3).getName(), is(nameX));
		assertThat((double) exprExpected.getExpression().get(4).getValue(), closeTo(decimalNegative,closeGap));
		assertThat(exprExpected.getExpression().get(4).getName(), is(nullValue()));
		assertThat((double) exprExpected.getExpression().get(5).getValue(), closeTo(decimalNegative,closeGap));
		assertThat(exprExpected.getExpression().get(5).getName(), is(nameY));
		assertThat((double) exprExpected.getExpression().get(6).getValue(), closeTo(intNegative,closeGap));
		assertThat(exprExpected.getExpression().get(6).getName(), is(nameY));
		
		assertThat( exprSUT.equal(exprExpected), is(true));
	}
}
