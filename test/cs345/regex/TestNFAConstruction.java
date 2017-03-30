package cs345.regex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestNFAConstruction {
	public String regex;
	public String states;

	public static final Object[][] tests = new Object[][] {
		{"",				"[s0, s1]"},
		{"a",				"[s0, s1]"},
		{"ab",				"[s0, s1, s2, s3]"},
		{"abc",				"[s0, s1, s2, s3, s4, s5]"},
		{"a|b",				"[s2, s0, s1, s3, s4, s5]"},
		{"(a)",				"[s0, s1]"},
		{"(ab)",			"[s0, s1, s2, s3]"},
		{"(a|b)",			"[s2, s0, s1, s3, s4, s5]"},
		{"(a|)",			"[s2, s0, s1, s3, s4, s5]"},
		{"(|)",				"[s2, s0, s1, s3, s4, s5]"}, 	// weird but legal
		{"(a|b|c|d)",		"[s2, s0, s1, s3, s4, s5, s6, s7, s8, s9]"},
		{"a*",				"[s2, s0, s1, s3]"},
		{"(a|b)*",			"[s6, s2, s0, s1, s3, s7, s4, s5]"},
	};

	public TestNFAConstruction(String regex, String states) {
		this.regex = regex;
		this.states = states;
	}

	@Before
	public void reset() { NFA.globalStateCounter = 0; }

	@Test
	public void testParsing() {
		RegexParser parser = new RegexParser(regex);
		NFA nfa = parser.parse();
		assertEquals("syntax errors:\n"+parser.getErrors(), 0, parser.getErrors().size());
		String result = nfa.getStates().toString();
		assertEquals(states, result);
	}

	@Parameterized.Parameters(name="{index}: {0} => {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(tests);
	}
}
