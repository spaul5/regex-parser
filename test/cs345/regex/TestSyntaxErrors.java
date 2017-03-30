package cs345.regex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestSyntaxErrors {
	public String regex;
	public String errors;

	public static final Object[][] tests = new Object[][] {
		{ "(a))",
			"[unrecognized input in (a))\n"+
			"                          ^]"},

		{ ")a)",
			"[unrecognized input in )a)\n"+
			"                       ^]"},

		{ "*",
			"[unrecognized input in *\n"+
			"                       ^]"},

		{ ")",
			"[unrecognized input in )\n"+
			"                       ^]"},

		{ "(a",
			"[expected ) at EOF in (a\n"+
			"                        ^]"},

		{ "a**",
			"[unrecognized input in a**\n"+
			"                         ^]"},
	};

	public TestSyntaxErrors(String regex, String errors) {
		this.errors = errors;
		this.regex = regex;
	}

	@Before
	public void reset() { NFA.globalStateCounter = 0; }

	@Test
	public void testParsing() {
		RegexParser parser = new RegexParser(regex);
		parser.parse();
		String parsingErrors = parser.getErrors().toString();
		assertEquals(errors, parsingErrors);
	}

	@Parameterized.Parameters(name="{index}: {1} matches {0} is {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(tests);
	}
}
