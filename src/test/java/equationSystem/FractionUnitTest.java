package equationSystem;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

public class FractionUnitTest {

	@Test
	public void getValueTest() {
		Fraction fractionSUT = new Fraction(21234,10000);
		float result = fractionSUT.getValue();

		assertThat((double) result , closeTo(2.1234,0.0001));
	}

	@Test
	public void addZeroFractionTest() {
		Fraction fractionSUT = new Fraction(5,1);
		Fraction fractionAux = new Fraction(0,1);
		assertThat(fractionSUT.add(fractionAux).toString() , is("+5"));
	}

	@Test
	public void addFractionResultRealTest() {
		Fraction fractionSUT = new Fraction(-50,10);
		Fraction fractionAux = new Fraction(700,100);		
		assertThat(fractionSUT.add(fractionAux).toString() , is("+2"));
	}

	@Test
	public void addFractionResultFractionTest() {
		Fraction fractionSUT = new Fraction(-3,2);
		Fraction fractionAux = new Fraction(8,-5);
		assertThat(fractionSUT.add(fractionAux).toString() , is("-(31/10)"));
	}

	@Test
	public void addFractionResultZeroTest() {
		Fraction fractionSUT = new Fraction(-72,1);
		Fraction fractionAux = new Fraction(72,1);
		assertThat(fractionSUT.add(fractionAux).toString() , is("+0"));
	}

	@Test
	public void toStringPositiveRealTest() {
		Fraction fractionSUT = new Fraction(72,1);
		assertThat(fractionSUT.toString() , is("+72"));
	}

	@Test
	public void toStringNegativeRealTest() {
		Fraction fractionSUT = new Fraction(-72,1);
		assertThat(fractionSUT.toString() , is("-72"));		
	}

	@Test
	public void toStringNegativeNumFractionTest() {
		Fraction fractionSUT = new Fraction(-720,10);
		assertThat(fractionSUT.toString() , is("-(720/10)"));		
	}

	@Test
	public void toStringNegativeDenFractionTest() {
		Fraction fractionSUT = new Fraction(720,-10);
		assertThat(fractionSUT.toString() , is("-(720/10)"));		
	}

	@Test
	public void toStringNegativeDenFraction2Test() {
		Fraction fractionSUT = new Fraction(1,-2);
		assertThat(fractionSUT.toString() , is("-(1/2)"));		
	}

	@Test
	public void toStringDenZeroFractionTest() {
		Fraction fractionSUT = new Fraction(720, 0);
		assertThat(fractionSUT.toString() , is("Infinite"));		
	}

	@Test
	public void toStringPositive2membersNegativeFractionTest() {
		Fraction fractionSUT = new Fraction(-72,-10);
		assertThat(fractionSUT.toString() , is("+(72/10)"));	
	}

	@Test
	public void simplifyNoNeedToSimplify0Test() {
		Fraction fractionSUT = new Fraction(0, -1);
		fractionSUT.simplify();
		assertThat(fractionSUT.toString() , is("+0"));	
	}

	@Test
	public void simplifyNoNeedToSimplifyFractionTest() {
		Fraction fractionSUT = new Fraction(5, 4);
		fractionSUT.simplify();
		assertThat(fractionSUT.toString() , is("+(5/4)"));	
	}

	@Test
	public void simplifyNumGtDenTest() {
		Fraction fractionSUT = new Fraction(2000, 1000);
		fractionSUT.simplify();
		assertThat(fractionSUT.toString() , is("+2"));	
	}

	@Test
	public void simplifyDenGtNumTest() {
		Fraction fractionSUT = new Fraction(1000, -2000);
		fractionSUT.simplify();
		assertThat(fractionSUT.num , equalTo(1));	
		assertThat(fractionSUT.den , equalTo(-2));
		assertThat(fractionSUT.toString() , is("-(1/2)"));	
	}


	@Test
	public void multiplyByInvertedTest() {
		Fraction fractionSUT = new Fraction(4, 5);
		Fraction fractionInverted = new Fraction (5,4);
		fractionSUT = fractionSUT.multiply(fractionInverted);
		fractionSUT.simplify();
		assertThat(fractionSUT.num , equalTo(1));	
		assertThat(fractionSUT.den , equalTo(1));
		assertThat(fractionSUT.toString() , is("+1"));	
	}

	@Test
	public void invertTest() {
		Fraction fractionSUT = new Fraction(4, 5);

		fractionSUT = fractionSUT.invert();
		assertThat(fractionSUT.toString() , is("+(5/4)"));	
	}
	
	@Test
	public void multiplyByInvertTest() {
		Fraction fractionSUT = new Fraction(4, 5);
		
		fractionSUT = fractionSUT.multiply(fractionSUT.invert());
		assertThat(fractionSUT.toString() , is("+1"));	
	}

}

