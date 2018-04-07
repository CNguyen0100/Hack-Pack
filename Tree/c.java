// Arup Guha
// 9/20/2013
// Solution to 2010 ACPC Problem C: Normalized Form

import java.util.*;

public class c {

	public static String expr;

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		expr = stdin.next();
		int loop = 1;

		// Process cases
		while (!expr.equals("()")) {

			System.out.println(loop+". "+solve(expr));

			// Get next expression.
			expr = stdin.next();
			loop++;
		}
	}

	// Returns the maximum nesting of parens in s.
	public static int getDepth(String s) {
		int cnt = 0, max = 0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) =='(')
				cnt++;
			else if (s.charAt(i) == ')')
				cnt--;
			if (cnt > max) max = cnt;
		}
		return max;
	}

	public static boolean solve(String expr) {

		int maxDepth = getDepth(expr);

		Stack<Character> s = new Stack<Character>();
		int curDepth = 0;

		// Process expression forward.
		for (int i=0; i<expr.length(); i++) {

			char c = expr.charAt(i);

			// Put on stack and change current level.
			if (c == '(') {
				s.push(c);
				curDepth++;
			}

			// These just go on the stack.
			else if (c == 'T' || c == 'F')
				s.push(c);

			// Here we finish a tree of some sort.
			else {

				boolean hasTrue = false;
				boolean hasFalse = false;

				// Pop off stack until we get open paren.
				while (true) {

					c = s.pop();

					// Mark character, or get out.
					if (c == 'T') hasTrue = true;
					else if (c == 'F') hasFalse = true;
					else break;
				}

				// And case.
				if ((maxDepth - curDepth)%2 == 0) {
					if (hasFalse) s.push('F');
					else          s.push('T');
				}

				// Or case.
				else {
					if (hasTrue) s.push('T');
					else         s.push('F');
				}

				// Change in depth also...
				curDepth--;
			}
		} // end for

		// Here is what we care about.
		return s.pop() == 'T';
	}
}