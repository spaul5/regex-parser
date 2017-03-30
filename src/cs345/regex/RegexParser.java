package cs345.regex;

import java.util.ArrayList;
import java.util.List;

public class RegexParser {
	public static final char EOF = (char) -1;
	protected final char[] pattern;	// the regex pattern to parse
	protected int p = 0;	 		// pattern[p] is next char to match
	protected final int n;
	protected final List<String> errors = new ArrayList<>();

	public RegexParser(String pattern) {
		this.pattern = pattern.toCharArray();
		n = this.pattern.length;
	}

	public List<String> getErrors() {
		return errors;
	}

	public NFA parse() {
		NFA nfa = regex();
		match(EOF);
		if ( p<n ) {
			error("unrecognized input");
			return NFA.error();
		}
		if (nfa == null) {
			return new NFA(NFA.atom(NFA.EPSILON));
		}
		return nfa;
	}

	// P a r s e  m e t h o d s

	/** Parse regex : sequence ('|' sequence)* ; */
	public NFA regex() {
		NFA l = sequence();
		NFA r = null;
		NFA e = null;
		if (look() == '|') {
			if (l == null) {
				l = NFA.atom(NFA.EPSILON);
			}
			e = new NFA(new NFA.State(), new NFA.State());
			e.start.epsilon(l.start);
			l.stop.epsilon(e.stop);
			while (look() != ')' && look() != EOF) {
				match('|');
				r = sequence();
				e.start.epsilon(r.start);
				r.stop.epsilon(e.stop);
			}
		}

		if (r == null) {
			return l;
		}
		else {
			return e;
		}
	}

	/** Parse sequence : closure sequence | ; */
	public NFA sequence() {
		NFA l = null;
		NFA r = null;
		if (look() == '(' || isLetter(look())){
			l = closure();
			r = sequence();
			if (r == null) {
				return l;
			}
			l.stop.epsilon(r.start);
			l.stop = r.stop;
			return l;
		}
		else if (look() == ')') {
			if (p < n-1) {
				return null;
			}
			if (!isLetter(pattern[p-1])){
				return new NFA(NFA.atom(NFA.EPSILON));
			}
			return null;
		}
		else {
			return null;
		}
		
		
	}

	/** Parse closure : element '*' | element ; */
	public NFA closure() {
		NFA nfa = element();
		NFA e = null;
		if (look() == '*') {
			match('*');
			e = new NFA(new NFA.State(), new NFA.State());
			e.start.epsilon(nfa.start);
			e.start.epsilon(e.stop);
			nfa.stop.epsilon(e.start);
			nfa.stop.epsilon(e.stop);
			return e;
		}
		return nfa;
	}

	/** Parse element : letter | '(' regex ')' ; */
	public NFA element() {
		NFA nfa;
		if (isLetter(look())) {
			nfa = NFA.atom(letter());
		}
		else if (look() == '(') {
			match('(');
    		nfa = regex();
    		match(')');
		}
		else {
			return null;
		}
    	
    	return nfa;
	}
	
	public char letter() {
		char c = look();
		matchRange();
		return c;
	}

	// S u p p o r t

	
	public char look() {
		if (p >= pattern.length) return (char)-1;
		return pattern[p];
	}
	
	public boolean isLetter(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
			return true;
		}
		else {
			return false;
		}
	}

	public void matchRange() {
		if ((look() >= 'a' && look() <= 'z') | (look() >= 'A' && look() <= 'Z')) {
			consume();
		}
		else {
			System.out.println("Expecting letter");
		}
	}
	
	public void match(char c) {
		if (look() == c) {
			consume();
		}
		else {
			System.out.println("mismatched char");
		}
	}

	public void consume() {
		p++;
	}

	public void error(String msg) {
		StringBuilder buf = new StringBuilder();
		buf.append(msg+" in "+new String(pattern));
		buf.append("\n");
		int spaces = p+msg.length()+" in ".length();
		for (int i=0; i<=spaces; i++) buf.append(" ");
		buf.append("^");
		errors.add(buf.toString());
	}
}
