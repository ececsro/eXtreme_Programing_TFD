package equations;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EquationUnitTest {

	String messageForExpected = "TEST IS NOT DONE";
	String messageForWas = "Remove procedure toDoTest once is done";
	
	private void toDoTest(String message) {
		assertThat(messageForWas, is(message));
	}
	@Test
	public void add_GivenEmptyEquation_addTermToSide() {
		
		toDoTest(messageForExpected);
	}

	@Test
	public void add_addTermToSide() {
		toDoTest(messageForExpected);
	}

	@Test
	public void add_addTermToBothSides() {
		toDoTest(messageForExpected);
	}

	@Test
	public void add_GivenFullEquation_addEmptyEquation() {
		toDoTest(messageForExpected);
	}

	@Test
	public void add_GivenFullEquation_addFullEquation() {
		toDoTest(messageForExpected);
	}

	@Test
	public void multiply_GivenFullEquation_multiplyByInteger() {
		toDoTest(messageForExpected);
	}

	@Test
	public void multiply_GivenFullEquation_multiplyByDecimal() {
		toDoTest(messageForExpected);
	}

	@Test
	public void multiply_GivenFullEquation_multiplyBy0() {
		toDoTest(messageForExpected);
	}

	@Test
	public void getValueName_GivenFullEquation_getValueFromTheSideWithoutThatName() {
		toDoTest(messageForExpected);
	}

	@Test
	public void getValueName_GivenFullEquation_getValueFromOneSide() {
		toDoTest(messageForExpected);
	}
	
	@Test
	public void getValue_GivenFullEquation_getValueFromOneSideWithoutConstant() {
		toDoTest(messageForExpected);
	}

	@Test
	public void getValue_GivenFullEquation_getValueFromOneSide() {
		toDoTest(messageForExpected);
	}

	@Test
	public void getValue_GivenFullEquation_getValueFromOneSide_resutlIs0() {
		toDoTest(messageForExpected);
	}

	@Test
	public void simplify_GivenFullEquation_simplifyByName_resutlIs0() {
		toDoTest(messageForExpected);
	}

	@Test
	public void simplify_GivenFullEquation_simplifyByName() {
		toDoTest(messageForExpected);
	}

	@Test
	public void simplify_GivenFullEquation_simplifyConstant_resultIs0() {
		toDoTest(messageForExpected);
	}

	@Test
	public void simplify_GivenFullEquation_simplifyConstant() {
		toDoTest(messageForExpected);
	}

	@Test
	public void getNameSet_GivenEquationOnlyConstants_getNameSet() {
		toDoTest(messageForExpected);
	}

	@Test
	public void getNameSet_GivenEquation1NameSet_getNameSet() {
		toDoTest(messageForExpected);
	}

	@Test
	public void getNameSet_GivenEquationMoreThan1NameSet_getNameSet() {
		toDoTest(messageForExpected);
	}

	@Test
	public void equal_Given2EmptyEquation_equalIsTrue() {
		toDoTest(messageForExpected);
	}

	@Test
	public void equal_Given2ExactlyEqualEquation_equalIsTrue() {
		toDoTest(messageForExpected);
	}

	@Test
	public void equal_Given2EqualEquationButInDifferentSides_equalIsTrue() {
		toDoTest(messageForExpected);
	}

	@Test
	public void equal_Given2EqualEquationButWithoutSimplifly_equalIsTrue() {
		toDoTest(messageForExpected);
	}

	@Test
	public void equal_Given2DifferentEquations_equalIsFalse() {
		toDoTest(messageForExpected);
	}

	@Test
	public void clone_GivenEmptyEquation_clone() {
		toDoTest(messageForExpected);
	}

	@Test
	public void clone_GivenEquation_clone() {
		toDoTest(messageForExpected);
	}

	@Test
	public void toString_GivenEmptyEquation_toString_result0eq0() {
		Equation equationSUT = new Equation();
		
		String expectedString = "0 = 0";
		
		assertThat(equationSUT.toString(), is(expectedString));
//		toDoTest(messageForExpected);
	}

	@Test
	public void toString_GivenEquationOneSideIs0_toString_result0eqExpr() {
		toDoTest(messageForExpected);
	}

	@Test
	public void toString_GivenEquationOneSideStartPositive_toString_resultExprEqExpr() {
		toDoTest(messageForExpected);
	}

	@Test
	public void toString_GivenEquationOneSideStartNegative_toString_resultExprEqNegExpr() {
		toDoTest(messageForExpected);
	}

	@Test
	public void invert_GivenEmptyEquation_invert() {
		toDoTest(messageForExpected);
	}

	@Test
	public void invert_GivenFullEquation_invert() {
		toDoTest(messageForExpected);
	}
}
