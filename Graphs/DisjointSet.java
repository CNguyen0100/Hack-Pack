// Arup Guha
// 9/25/09
// Written for Exam #1 question for COP 3503H Fall 2009

// Just stores a pair of values associated with an item in a disjoint set.
class Pair {
	
	public int value;
	public int height;
	
	public Pair(int v, int h) {
		value = v;
		height = h;
	}
}

public class DisjointSet {
	
	// This array stores our data structure.
	private Pair[] parentList;
	
	// Initialize each item to a value from 0 to n-1 all with a height of 0, meaning separate trees.
	public DisjointSet(int n) {
		parentList = new Pair[n];
		for (int i=0; i<n; i++) {
			parentList[i] = new Pair(i,0);
		}
	}
	
	// Returns the value that is the root of the tree that stores value.
	public int find(int value) {
		
		// Continue going up the ancestral tree until you get to a node without a parent.
		while (parentList[value].value != value) {
			value = parentList[value].value;
		}
		
		// Return this root.
		return value;
	}
	
	// Create a union between the tree storing indexA and the one storing indexB.
	// Precondition: the values indexA and indexB are stored in different subtrees.
	public void union(int indexA, int indexB) {
		
		// Get each respective root node.
		int rootA = find(indexA);
		int rootB = find(indexB);
		
		// Attach tree A to be a child of tree B since A is shorter.
		if (parentList[rootA].height < parentList[rootB].height) {
			parentList[rootA].value = rootB;
		}
		else {
			
			// Attach B to be the child of A.
			parentList[rootB].value = rootA;
			
			// If the trees are equal height, then A's root goes up 1 in height.
			if (parentList[rootA].height == parentList[rootB].height)
				parentList[rootA].height++;
		}
	}
	
}