package equations;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.junit.Test;

@RunWith(Parameterized.class)
public class ExpressionUnitTest {

		@Parameters()
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
		@Parameter(4) public String nameValue1;
		@Parameter(5) public String nameValue2;
		@Parameter(6) public String nameValue3;
		@Parameter(7) public double closeGap;
		@Parameter(8) public String cellVal8;
		@Parameter(9) public String playerLabel;
		@Parameter(10) public boolean winResult;
		@Parameter(11) public int winResult1;
		@Parameter(12) public int winResult2;
		@Parameter(13) public int winResult3;

	
	
	@Test
	public void GivenExpresionEmpty_executeEmpty_checkIsTrue() {
		Expression exprSUT = new Expression();
		assertTrue(exprSUT.empty());
	}	

	@Ignore
	@Test
	public void GivenExpressionNotEmpty_executeEmpty_checkIsEmpty_checkIsFalse() {
		Expression exprSUT = new Expression();
		Term termAux=new ConstantTestBuilder().build();
		exprSUT.add(termAux);
		
		assertFalse(exprSUT.empty());
		assertTrue(exprSUT.equal(new Expression()));
	}	

	@Test
	public void GivenExpressionEmpty_addTermConstant() {
		Expression exprSUT = new Expression();
		Term termAux=new ConstantTestBuilder().
				constantValue(intPositive).build();
		exprSUT.add(termAux);
		
		assertThat(exprSUT.getValue(), equalTo((float) intPositive));		
	}	

	@Test
	public void GivenExpressionEmpty_addTermVariable() {
		Expression exprSUT = new Expression();
		Term termAux=new VariableTestBuilder().
				constantValue(intNegative).nameValue(nameValue1).build();
		exprSUT.add(termAux);
		
		assertThat((double) exprSUT.getValue(nameValue1), closeTo(intNegative,closeGap));
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
	public void GivenExpression1cons0var_multiply() {
		Expression exprSUT = new Expression();
		Term termConstant=new ConstantTestBuilder().
				constantValue(decimalPositive).build();
		exprSUT.add(termConstant);
		exprSUT.multiply((float)decimalNegative);
		float expectedConstantValue = (float)(decimalPositive * decimalNegative);
		
		assertThat((double) exprSUT.getValue(), closeTo(expectedConstantValue,closeGap));
	}		

	@Test
	public void GivenExpression0cons1var_multiply() {
		Expression exprSUT = new Expression();
		Term termVariable=new VariableTestBuilder().
				constantValue(intPositive).nameValue(nameValue1).build();
		exprSUT.add(termVariable);
		exprSUT.multiply((float)decimalPositive);
		float expectedConstantValue = (float)(intPositive * decimalPositive);
		
		assertThat((double) exprSUT.getValue(nameValue1), closeTo(expectedConstantValue,closeGap));
	}		

	@Test
	public void GivenExpression2cons3var_multiply() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(decimalPositive));
		expressionTest.add(new TermForTest(decimalNegative));
		expressionTest.add(new TermForTest(intPositive, nameValue1));
		expressionTest.add(new TermForTest(decimalPositive, nameValue1));
		expressionTest.add(new TermForTest(decimalNegative, nameValue2));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.multiply((float)decimalPositive);
		
		float expectedConstantValue = (float)((decimalPositive + decimalNegative) * decimalPositive);
		float expectedNameValue1 = (float)((intPositive + decimalPositive) * decimalPositive);
		float expectedNameValue2 = (float)(decimalNegative * decimalPositive);
		
		assertThat((double) exprSUT.getValue(), closeTo(expectedConstantValue,closeGap));
		assertThat((double) exprSUT.getValue(nameValue1), closeTo(expectedNameValue1,closeGap));
		assertThat((double) exprSUT.getValue(nameValue2), closeTo(expectedNameValue2,closeGap));
	}		

	@Test
	public void GivenExpression0cons1var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(intPositive, nameValue1));

		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();
				
		assertThat((double) exprSUT.getValue(), closeTo(0 , closeGap));
		assertThat((double) exprSUT.getValue(nameValue1), closeTo(intPositive, closeGap));
	}		

	@Test
	public void GivenExpression1cons0var_simplifyConstant() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(decimalPositive));
		
		Expression exprSUT = new ExpressionTestBuilder().
				addExpression(expressionTest).build();
				
		exprSUT.simplify();
		
		assertThat((double) exprSUT.getValue(), closeTo(decimalPositive,closeGap));
	}		
}
