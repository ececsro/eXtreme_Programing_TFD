package equationSystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	EquationSystemTest.class, 
	EquationTest.class,
	ExpressionTest.class,
	ExpressionUnitTest.class,
	TermUnitTest.class,
	VariableUnitTest.class,
	ConstantUnitTest.class,
	FractionUnitTest.class })
public class AllTests {

}
