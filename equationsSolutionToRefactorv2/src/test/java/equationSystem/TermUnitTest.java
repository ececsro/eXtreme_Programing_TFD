package equationSystem;

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



public class TermUnitTest {
	
	@Test
	public void GivenOneTermVariable_MultiplyByValue() {
		Term termSUT = new VariableTestBuilder().constantValue((float) 2.3).build();
		
		termSUT.multiply(2);
		
		assertEquals(termSUT.getValue(), 4.6f, 0000.1);
	}	
	
	@Test
	public void GivenOneConstantTerm_HasNameIsFalse() {
		Term termSUT = new ConstantTestBuilder().build();
		String name="x";
		assertFalse(termSUT.hasName(name));
	}	
	
	@Test
	public void GivenOneConstantTerm_HasNameSetIsFalse() {
		Term termSUT = new ConstantTestBuilder().build();

		Set<String> nameSet = new HashSet<String>();
		nameSet.add("x");
		nameSet.add("y");
		nameSet.add("z");
		
		assertFalse(termSUT.hasName(nameSet));
	}	
	
	@Test
	public void GivenVariableTerm_GivenNameSet_CheckNameIsOneOfThem() {
		Term termSUT = new VariableTestBuilder().nameValue("x").build();
		Set<String> nameSet = new HashSet<String>();
		nameSet.add("x");
		nameSet.add("y");
		nameSet.add("z");
	
		assertTrue(termSUT.hasName(nameSet));
		
	}
	
	@Test
	public void GivenVariableTerm_GivenNameSet_CheckNameIsNoneOfThem() {
		Term termSUT = new VariableTestBuilder().nameValue("w").build();
		Set<String> nameSet = new HashSet<String>();
		nameSet.add("x");
		nameSet.add("y");
		nameSet.add("z");
	
		assertFalse(termSUT.hasName(nameSet));
	}

	@Test
	public void GivenVariableTerm_GivenEmptyNameSet_CheckNameIsNoneOfThem() {
		Term termSUT = new VariableTestBuilder().nameValue("w").build();
		Set<String> nameSet = new HashSet<String>();
	
		assertFalse(termSUT.hasName(nameSet));
	}

	@Test
	public void GivenConstantTerm_PositiveInteger_ConvertToString() {
		Term termSUT = new ConstantTestBuilder().constantValue(2).build();	
		assertEquals(termSUT.toString(), "+2");	
	}

	@Test
	public void GivenConstantTerm_NegativeInteger_ConvertToString() {
		Term termSUT = new ConstantTestBuilder().constantValue(-2).build();		
		assertEquals(termSUT.toString(), "-2");	
	}
	
	@Test
	public void GivenConstantTerm_NegativeDecimalPart_ConvertToString() {
		Term termSUT = new ConstantTestBuilder().constantValue(-1.8f).build();		
		assertEquals(termSUT.toString(), "-1.8");	
	}
}

