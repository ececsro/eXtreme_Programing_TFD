package equationSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ConstantUnitTest {
	
	@Test
	public void GivenTwoConstantsFractions_CheckIsEqual() {
		ConstantTestBuilder constantBuilder = new ConstantTestBuilder();
		Constant c1 = constantBuilder.constantValue(3,2).build();
		Constant c2 = constantBuilder.constantValue(3,2).build();
		assertTrue(c1.equal(c2));
	}

	@Test
	public void GivenTwoConstants_CheckAreDifferent() {
		ConstantTestBuilder constantBuilder = new ConstantTestBuilder();
		Constant c1 = constantBuilder.constantValue(0,5).build();
		Constant c2 = constantBuilder.constantValue(3,2).build();
		assertFalse(c1.equal(c2));
	}

	@Test
	public void GivenConstant_CreateAClon() {
		ConstantTestBuilder constantBuilder = new ConstantTestBuilder();
		Constant c1 = constantBuilder.constantValue(3,2).build();
		Term c2 = c1.clon();
		assertTrue(c1.equal(c2));	
	}
	
	@Test
	public void GivenConstant_WhenGetName_ThenIsNull() {
		Constant c1 = new ConstantTestBuilder().constantValue(3,2).build();	
		assertThat(c1.getName(), is(nullValue()));	
	}
}

