package equations;

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
		Constant c1 = new Constant ((float) 3.2);
		Constant c2 = new Constant ((float) 3.2);
		assertTrue(c1.equal(c2));
		
	}
	
	@Test
	public void GivenTwoConstants_CheckAreDifferent() {
		Constant c1 = new Constant ((float) 1.8);
		Constant c2 = new Constant ((float) 3.2);
		assertFalse(c1.equal(c2));
	}

	@Test
	public void GivenConstant_CreateAClon() {
	
		Constant c1 = new Constant ((float) 1.8);
		Constant c2 = c1.clon();
		assertTrue(c1.equal(c2));	
	}
}

