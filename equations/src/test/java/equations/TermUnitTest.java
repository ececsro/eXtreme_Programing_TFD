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



public class TermUnitTest {
	
	@Test
	public void GivenOneTerm_MultiplyByValue() {
		Term t = new Variable ((float)(2.3),"distancia");
		
		assertEquals(t.getValue()*2, 4.6f, 0000.1);
	}	
	
	@Test
	public void GivenOneConstantTerm_HasNameIsFalse() {
		Term t = new Constant ((float)(2.3));
		
		String name="altura";
		
		assertFalse(t.hasName(name));
	}	
	
	@Test
	public void GivenOneConstantTerm_HasNameSetIsFalse() {
		Term t = new Constant ((float)(2.3));

		Set<String> nameSet = new HashSet<String>();
		nameSet.add("altura");
		nameSet.add("peso");
		nameSet.add("longitud");
		
		assertFalse(t.hasName(nameSet));
	}	
	
	
	@Test
	public void GivenNameSet_CheckIsOneOfThem() {
		Variable v = new Variable ((float)(2.3),"longitud");
		Set<String> nameSet = new HashSet<String>();
		nameSet.add("altura");
		nameSet.add("peso");
		nameSet.add("longitud");
	
		assertTrue (v.hasName(nameSet));
	}
	
	
	@Test
	public void GivenTwoVariables_CheckAreEqual() {
		Variable v1 = new Variable ((float) 3.2, "longitud");
		Variable v2 = new Variable ((float) 3.2, "longitud");
		assertTrue(v1.equal(v2));
		
	}
	
	@Test
	public void GivenTwoVariables_CheckAreDifferent() {
		Variable c1 = new Variable ((float) 1.8, "longitud");
		Variable c2 = new Variable ((float) 3.2, "longitud");
		assertFalse(c1.equal(c2));
	}
	
	@Test
	public void GivenVariable_CreateAClon() {
		
		Variable v1 = new Variable ((float) 1.8, "longitud");
		Variable v2 = v1.clon();
		assertTrue(v1.equal(v2));	
	}
	
	@Test
	public void GivenVariable_convertToString() {
		
		Variable v1 = new Variable ((float) 1.8, "longitud");
		assertEquals(v1.toString(), new String("1.8"+"longitud"));	
    }
	
}

