package equations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;



public class VariableUnitTest {
	
	@Test
	public void GivenOneVariable_CheckHasTheRightName() {
		Variable varSUT = new VariableTestBuilder().constantValue(1).nameValue("x").build();
		assertTrue (varSUT.hasName("x"));
	}	
	
	@Test
	public void GivenNameSet_CheckIsOneOfThem() {
		Variable varSUT = new VariableTestBuilder().constantValue(1).nameValue("x").build();
		Set<String> nameSet = new HashSet<String>();
		nameSet.add("x");
		nameSet.add("y");
		nameSet.add("z");
	
		assertTrue (varSUT.hasName(nameSet));
	}
	
	
	@Test
	public void GivenTwoVariables_CheckAreEqual() {
		Variable varSUT = new VariableTestBuilder().constantValue(1).nameValue("x").build();
		Variable varAux = new VariableTestBuilder().constantValue(1).nameValue("x").build();
		assertTrue(varSUT.equal(varAux));		
	}
	
	@Test
	public void GivenTwoVariables_constantsAreDifferent_CheckAreDifferent() {
		Variable varSUT = new VariableTestBuilder().constantValue(1).nameValue("x").build();
		Variable varAux = new VariableTestBuilder().constantValue(2).nameValue("x").build();
		assertFalse(varSUT.equal(varAux));
	}

	@Test
	public void GivenTwoVariables_namesAreDifferent_CheckAreDifferent() {
		Variable varSUT = new VariableTestBuilder().constantValue(1).nameValue("x").build();
		Variable varAux = new VariableTestBuilder().constantValue(1).nameValue("y").build();
		assertFalse(varSUT.equal(varAux));
	}
	
	@Test
	public void GivenVariable_CreateAClon() {
		
		Variable varSUT = new VariableTestBuilder().constantValue(1).nameValue("x").build();
		Variable varAux = varSUT.clon();
		assertTrue(varSUT.equal(varAux));	
	}
	
	@Test
	public void GivenVariable_constantIsInteger_convertToString() {
		Variable varSUT = new VariableTestBuilder().constantValue(2).nameValue("x").build();		
		assertEquals(varSUT.toString(), "+2x");	
    }

	@Test
	public void GivenVariable_constantWithDecimalPart_convertToString() {
		Variable varSUT = new VariableTestBuilder().constantValue((float)1.8).nameValue("x").build();		
		assertEquals(varSUT.toString(), new String("+1.8x"));	
    }
	
	@Test
	public void GivenVariable_constantWithNegativeAndDecimalPart_convertToString() {
		Variable varSUT = new VariableTestBuilder().constantValue((float)-1.8).nameValue("x").build();		
		assertEquals(varSUT.toString(), new String("-1.8x"));	
    }
}

