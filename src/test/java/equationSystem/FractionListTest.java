package equationSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

//insertAtBegining
//returnElementsNumber
//removeFromBegining
//containsElement

public class FractionListTest {
	
	@Test
	public void insertAtBeginingTest() {
		Fraction fraction = new Fraction(8,7);
		FractionList fractionList = new FractionList();
		fractionList.insert(fraction);

		assertTrue(fractionList.contains(fraction));
		assertThat(fractionList.size(),is(1));
	}
}

