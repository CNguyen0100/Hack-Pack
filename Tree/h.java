// Arup Guha
// 1/31/2013
// Solution to 2011 South East Regional Problem H: Family Fortune
import java.util.*;

public class h {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);

		int N = stdin.nextInt();
		int K = stdin.nextInt();

		// Process each case.
		while (N != 0) {

			// Read in parent and wealth data.
			int[] parent = new int[N+1];
			int[] wealth = new int[N+1];
			for (int i=0; i<N; i++) {
				parent[i+1] = stdin.nextInt();
				wealth[i+1] = stdin.nextInt();
			}

			// Solve and proceed to next case.
			int ans = solve(K, parent, wealth);
			System.out.println(ans);

			N = stdin.nextInt();
			K = stdin.nextInt();
		}
	}

	// Returns the number of leaves in the tree at tree[root] and fills
	// in each node object with this information.
	public static int leaves(node[] tree, int root) {

		// Simple case.
		if (tree[root].kids.size() == 0) {
			tree[root].leaves = 1;
			return 1;
		}

		// Go through all kids and add.
		int sum = 0;
		for (int i=0; i<tree[root].kids.size(); i++)
			sum += leaves(tree, tree[root].kids.get(i));

		// Set instance variable and return.
		tree[root].leaves = sum;
		return sum;
	}

	// Solves the given problem
	public static int solve(int K, int[] parent, int[] wealth) {

		// Create our "tree".
		int N = parent.length-1;
		node[] tree = new node[N+1];
		for (int i=0; i<N+1; i++)
			tree[i] = new node();

		// Add in child links.
		for (int i=0; i<N; i++)
			tree[parent[i+1]].addChild(i+1);

		// Take out 0 case.
		if (leaves(tree, 0) < K)
			return 0;

		// Works for regular cases.
		int[] ans = postOrder(tree, K, 0, wealth);
		return ans[K];
	}

	// Solve the problem in a post-order traversal.
	public static int[] postOrder(node[] tree, int K, int root, int[] wealth) {

		// Will store the best wealth values for 1..K relatives, or the max # of
		// valid relatives.
		int[] best = new int[Math.min(tree[root].leaves+1,K+1)];

		// Go through each child.
		for (int i=0; i<tree[root].kids.size(); i++) {
			int[] thisChild = postOrder(tree, K, tree[root].kids.get(i), wealth);

			// Update our best array for our original root, based on this child.
			if (i > 0)
				best = calculate(best, thisChild, K);

			// No data available, just take from this child tree.
			else
				best = thisChild;
		}

		// If this ancestor is richer than all his descendants, change this value!
		if (wealth[root] > best[1])
			best[1] = wealth[root];
		return best;
	}

	// Given that one stores the current maximum wealths and two stores the maximum wealth
	// of a new subtree, we calculate the necessary maximum wealths incorporating tree two.
	public static int[] calculate(int[] one, int[] two, int K) {

		// This is as big as our array needs to be.
		int[] best = new int[Math.min(K+1, one.length+two.length-1)];

		// We can use an algorithm similar to merge here. Be careful of array out of bounds.
		int i = 0, j = 0;
		while (i < one.length && j < two.length && i+j+1 < best.length && i+j+1 <= K) {

			// Set the next item to "add" from tree one. (Note: this could be negative, since
			// we might be replacing one big node with two smaller nodes...
			int curOne = -1000000000;
			if (i+1 < one.length)
				curOne = one[i+1] - one[i];

			// Same here. Note: The sentinel value is so we never mistake no solution for a
			// wealth of 0.
			int curTwo = -1000000000;
			if (j+1 < two.length)
				curTwo = two[j+1] - two[j];

			// Add in the best upcoming wealth.
			if (curOne > curTwo) {
				best[i+j+1] = best[i+j] + curOne;
				i++;
			}
			else {
				best[i+j+1] = best[i+j] + curTwo;
				j++;
			}

		}

		// This is our array of best wealths.
		return best;
	}
}

// Stores the children's ID numbers and # leaves in subtree.
class node {

	public ArrayList<Integer> kids;
	public int leaves;

	public node() {
		kids = new ArrayList<Integer>();
	}

	public void addChild(int val) {
		kids.add(val);
	}
}