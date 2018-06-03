package equationSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class TermUnitTest {
	
	@Test
	public void GivenOneTermVariable_MultiplyByValue() {
		Term termSUT = new VariableTestBuilder().constantValue(23,10).build();
		
		termSUT.multiply(2);
		
		Fraction expectedFraction = new Fraction (46,10);
		assertThat(termSUT.getValue(), is(expectedFraction));
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
		Term termSUT = new ConstantTestBuilder().constantValue(4,2).build();	
		assertEquals(termSUT.toString(), "+(4/2)");	
	}

	@Test
	public void GivenConstantTerm_NegativeInteger_ConvertToString() {
		Term termSUT = new ConstantTestBuilder().constantValue(-4,2).build();		
		assertEquals(termSUT.toString(), "-(4/2)");	
	}
	
	@Test
	public void GivenConstantTerm_NegativeDecimalPart_ConvertToString() {
		Term termSUT = new ConstantTestBuilder().constantValue(-18,10).build();		
		assertEquals(termSUT.toString(), "-(18/10)");	
	}
}

