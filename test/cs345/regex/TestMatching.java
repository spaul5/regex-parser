package cs345.regex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestMatching {
	public String regex;
	public String input;
	public boolean expectedResults;

	public static final Object[][] tests = new Object[][] {
		{"",				"",							true},
		{"",				"a",						false},
		{"a",				"a",						true},
		{"a",				"b",						false},
		{"ab",				"ab",						true},
		{"ab",				"abx",						false},
		{"abcd",			"abcd",						true},
		{"abcd",			"ab",						false},
		{"abcd",			"abc",						false},
		{"a|b",				"a",						true},
		{"a|b",				"b",						true},
		{"(a)",				"a",						true},
		{"(ab)",			"ab",						true},
		{"(ab)",			"ax",						false},
		{"(ab)",			"x",						false},
		{"(a|b)",			"a",						true},
		{"(a|b)",			"b",						true},
		{"(a|b)",			"c",						false},
		{"a*",  			"",						    true},
		{"a*",  			"a",					    true},
		{"a*",  			"b",					    false},
		{"a*",  			"aaaa",					    true},
		{"a*",  			"aaaab",				    false},
		{"(a|)",  			"",						    true},
		{"(a|)",  			"a",					    true},
		{"(a)*",  			"aaaa",					    true},
		{"(a)*",  			"abaa",					    false},
		{"a*b*",  			"",					        true},
		{"a*b*",  			"a",				        true},
		{"a*b*",  			"ab",				        true},
		{"a*b*",  			"b",				        true},
		{"a*b*",  			"b",				        true},
		{"a*b*",  			"bbbb",				        true},
		{"(a|b)*",  		"a",				        true},
		{"(a|b)*",  		"b",				        true},
		{"(a|b)*",  		"ab",				        true},
		{"(a|b)*",  		"ba",				        true},
		{"(a|b)*",  		"cba",				        false},
		{"(a|b)*",  		"baaac",			        false},
	};

	public TestMatching(String regex, String input, boolean expectedResults) {
		this.regex = regex;
		this.input = input;
		this.expectedResults = expectedResults;
	}

	@Before
	public void reset() { NFA.globalStateCounter = 0; }

	@Test
	public void testMatching() {
		Regex r = new Regex(this.regex);
		boolean result = r.matches(input);
		assertEquals(expectedResults, result);
	}

	@Parameterized.Parameters(name="{index}: {0} matches {1} is {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(tests);
	}
}
