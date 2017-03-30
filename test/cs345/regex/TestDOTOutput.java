package cs345.regex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestDOTOutput {
	public String regex;
	public String dot;

	public static final Object[][] tests = new Object[][]{
		{"a",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"}\n"},

		{"ab",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 2 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 3 [label=\"b\", fontsize=18];\n"+
			"}\n"},

		{"abcd",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 2 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 3 [label=\"b\", fontsize=18];\n"+
			"  3 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 5 [label=\"c\", fontsize=18];\n"+
			"  5 -> 6 [label=\"&epsilon;\", fontsize=18];\n"+
			"  6 -> 7 [label=\"d\", fontsize=18];\n"+
			"}\n"},

		{"a|b",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  2 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 5 [label=\"b\", fontsize=18];\n"+
			"  5 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"(a|b)",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  2 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 5 [label=\"b\", fontsize=18];\n"+
			"  5 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"(a|b|c)",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  2 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 5 [label=\"b\", fontsize=18];\n"+
			"  5 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 6 [label=\"&epsilon;\", fontsize=18];\n"+
			"  6 -> 7 [label=\"c\", fontsize=18];\n"+
			"  7 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"(a|)",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  2 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 5 [label=\"&epsilon;\", fontsize=18];\n"+
			"  5 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"a*",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  2 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 2 [label=\"&epsilon;\", fontsize=18];\n"+
			"  1 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"(a|b|c)*",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  8 -> 2 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  3 -> 8 [label=\"&epsilon;\", fontsize=18];\n"+
			"  3 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 5 [label=\"b\", fontsize=18];\n"+
			"  5 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 6 [label=\"&epsilon;\", fontsize=18];\n"+
			"  6 -> 7 [label=\"c\", fontsize=18];\n"+
			"  7 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  8 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"x(a|b)*y",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  0 -> 1 [label=\"x\", fontsize=18];\n"+
			"  1 -> 8 [label=\"&epsilon;\", fontsize=18];\n"+
			"  8 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 2 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 3 [label=\"a\", fontsize=18];\n"+
			"  3 -> 5 [label=\"&epsilon;\", fontsize=18];\n"+
			"  5 -> 8 [label=\"&epsilon;\", fontsize=18];\n"+
			"  5 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"  9 -> 10 [label=\"&epsilon;\", fontsize=18];\n"+
			"  10 -> 11 [label=\"y\", fontsize=18];\n"+
			"  4 -> 6 [label=\"&epsilon;\", fontsize=18];\n"+
			"  6 -> 7 [label=\"b\", fontsize=18];\n"+
			"  7 -> 5 [label=\"&epsilon;\", fontsize=18];\n"+
			"  8 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"(a(c|d))*",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  8 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 2 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 3 [label=\"c\", fontsize=18];\n"+
			"  3 -> 5 [label=\"&epsilon;\", fontsize=18];\n"+
			"  5 -> 8 [label=\"&epsilon;\", fontsize=18];\n"+
			"  5 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 6 [label=\"&epsilon;\", fontsize=18];\n"+
			"  6 -> 7 [label=\"d\", fontsize=18];\n"+
			"  7 -> 5 [label=\"&epsilon;\", fontsize=18];\n"+
			"  8 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"},

		{"(a|b)*|c",
			"digraph nfa {\n"+
			"  rankdir = LR;\n"+
			"  node [shape = circle, height = 0.45, fontsize=18, fixedsize=true];\n"+
			"  8 -> 6 [label=\"&epsilon;\", fontsize=18];\n"+
			"  6 -> 2 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 0 [label=\"&epsilon;\", fontsize=18];\n"+
			"  0 -> 1 [label=\"a\", fontsize=18];\n"+
			"  1 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  3 -> 6 [label=\"&epsilon;\", fontsize=18];\n"+
			"  3 -> 7 [label=\"&epsilon;\", fontsize=18];\n"+
			"  7 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"  2 -> 4 [label=\"&epsilon;\", fontsize=18];\n"+
			"  4 -> 5 [label=\"b\", fontsize=18];\n"+
			"  5 -> 3 [label=\"&epsilon;\", fontsize=18];\n"+
			"  6 -> 7 [label=\"&epsilon;\", fontsize=18];\n"+
			"  8 -> 10 [label=\"&epsilon;\", fontsize=18];\n"+
			"  10 -> 11 [label=\"c\", fontsize=18];\n"+
			"  11 -> 9 [label=\"&epsilon;\", fontsize=18];\n"+
			"}\n"}
	};

	public TestDOTOutput(String regex, String dot) {
		this.regex = regex;
		this.dot = dot;
	}

	@Before
	public void reset() { NFA.globalStateCounter = 0; }

	@Test
	public void testParsing() {
		RegexParser parser = new RegexParser(regex);
		NFA nfa = parser.parse();
		assertEquals("syntax errors:\n"+parser.getErrors(), 0, parser.getErrors().size());
		String output = nfa.toDOT();
		assertEquals(dot, output);
	}

	@Parameterized.Parameters(name="{index}: {0} => {1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(tests);
	}
}
