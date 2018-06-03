package equationSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class VariableUnitTest {
	
	@Test
	public void GivenOneVariable_CheckHasTheRightName() {
		Variable varSUT = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
		assertTrue (varSUT.hasName("x"));
	}	
	
	@Test
	public void GivenNameSet_CheckIsOneOfThem() {
		Variable varSUT = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
		Set<String> nameSet = new HashSet<String>();
		nameSet.add("x");
		nameSet.add("y");
		nameSet.add("z");
	
		assertTrue (varSUT.hasName(nameSet));
	}
	
	@Test
	public void GivenTwoVariables_CheckAreEqual() {
		Variable varSUT = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
		Variable varAux = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
		assertTrue(varSUT.equal(varAux));		
	}
	
	@Test
	public void GivenTwoVariables_constantsAreDifferent_CheckAreDifferent() {
		Variable varSUT = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
		Variable varAux = new VariableTestBuilder().constantValue(2,2).nameValue("x").build();
		assertFalse(varSUT.equal(varAux));
	}

	@Test
	public void GivenTwoVariables_namesAreDifferent_CheckAreDifferent() {
		Variable varSUT = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
		Variable varAux = new VariableTestBuilder().constantValue(1,2).nameValue("y").build();
		assertFalse(varSUT.equal(varAux));
	}

	@Test
	public void Given1Constant2Variables_namesAreDifferent_CheckAreDifferent() {
		Variable varSUT = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
		Variable varAux1 = new VariableTestBuilder().constantValue(1,2).nameValue("y").build();
		Constant varAux2 = new ConstantTestBuilder().constantValue(1,2).build();
		assertFalse(varSUT.equal(varAux1));
		assertFalse(varSUT.equal(varAux2));
	}

	@Test
	public void GivenVariable_CreateAClon() {
		
		Variable varSUT = new VariableTestBuilder().constantValue(1,2).nameValue("x").build();
//		Variable varAux = varSUT.clon();
		Term varAux = varSUT.clon();

		assertTrue(varSUT.equal(varAux));	
	}
	
	@Test
	public void GivenVariable_constantIsInteger_convertToString() {
		Variable varSUT = new VariableTestBuilder().constantValue(4,2).nameValue("x").build();		
		assertEquals(varSUT.toString(), "+(4/2)x");	
    }

	@Test
	public void GivenVariable_constantWithDecimalPart_convertToString() {
		Variable varSUT = new VariableTestBuilder().constantValue(18,10).nameValue("x").build();		
		assertEquals(varSUT.toString(), new String("+(18/10)x"));	
    }
	
	@Test
	public void GivenVariable_constantWithNegativeAndDecimalPart_convertToString() {
		Variable varSUT = new VariableTestBuilder().constantValue(-18,10).nameValue("x").build();		
		assertEquals(varSUT.toString(), new String("-(18/10)x"));	
    }
}

