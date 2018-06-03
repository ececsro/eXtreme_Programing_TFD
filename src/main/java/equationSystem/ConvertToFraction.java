package equationSystem;

public class ConvertToFraction {

	public Fraction convertToFraction(double doubleValue) {
		return convertToFraction((float) doubleValue);
	}

	public Fraction convertToFraction (float realNumber) {
		int newNumerator;
		int newDenominator = 1;
		
		while (realNumber % 1 != 0 ) {
			newDenominator *= 10;
			realNumber *= 10;
		}
		newNumerator = (int) realNumber;
		
		return new Fraction (newNumerator , newDenominator);
	}

	
}
