package equationSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;



public class ConstantUnitTest {
	
	
	
	@Test
	public void GivenTwoConstants_CheckIsEqual() {
		ConstantTestBuilder constantBuilder = new ConstantTestBuilder();
		Constant c1 = constantBuilder.constantValue(equalValue(1)).build();
		Constant c2 = constantBuilder.constantValue(equalValue(1)).build();
		assertTrue(c1.equal(c2));
	}
	
	@Test
	public void GivenTwoConstants_CheckAreDifferent() {
		ConstantTestBuilder constantBuilder = new ConstantTestBuilder();
		Constant c1 = constantBuilder.constantValue(differentValue(1)).build();
		Constant c2 = constantBuilder.constantValue(differentValue(2)).build();
		assertFalse(c1.equal(c2));
	}

	@Test
	public void GivenConstant_CreateAClon() {
		ConstantTestBuilder constantBuilder = new ConstantTestBuilder();
		Constant c1 = constantBuilder.constantValue(3).build();
//		Constant c2 = c1.clon();
		Term c2 = c1.clon();
		assertTrue(c1.equal(c2));	
	}
	

	@Test
	public void GivenConstant_WhenGetName_ThenIsNull() {
		ConstantTestBuilder constantBuilder = new ConstantTestBuilder();
		Constant c1 = new ConstantTestBuilder().constantValue(3).build();	
		assertThat(c1.getName(), is(nullValue()));	
	}

	private double equalValue (double value) {
		return value;
	}
	private double differentValue (double value) {
		return value;
	}
}

