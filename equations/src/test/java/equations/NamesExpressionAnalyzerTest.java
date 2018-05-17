package equations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NamesExpressionAnalyzerTest {

	@Test
	public void Given2cons3var_getNameSet() {
 
		List<TermForTest> expressionTest = new ArrayList<TermForTest>();
		
		expressionTest.add(new TermForTest(1, "x"));
		expressionTest.add(new TermForTest(2, "x"));
		expressionTest.add(new TermForTest(3));
		expressionTest.add(new TermForTest(4, "x"));
		expressionTest.add(new TermForTest(5));
		expressionTest.add(new TermForTest(6, "y"));
		expressionTest.add(new TermForTest(7, "z"));

		NamesExpressionAnalyzer namesExprAnalyzerSUT = 
				new NamesExpressionTestBuilder(expressionTest).build();

		assertThat( namesExprAnalyzerSUT.getNameSet(), 
				containsInAnyOrder("x","y","z"));
	}
}
