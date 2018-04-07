// Arup Guha
// 10/8/2015
// Prim's Algorithm - written as an example for the programming team.

import java.util.*;

class edge implements Comparable<edge> {

	public int v1;
	public int v2;
	public int w;

	public edge(int a, int b, int weight) {
		v1 = a;
		v2 = b;
		w = weight;
	}

	public int compareTo(edge other) {
		return this.w - other.w;
	}
}

class prims {

	public static int mst(ArrayList[] graph, int v) {

		// Mark vertex v as being in mst.
		int n = graph.length;
		boolean[] used = new boolean[n];
		used[v] = true;

		// Add all of v's edges into the priority queue.
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		for (int i=0; i<graph[v].size(); i++)
			pq.offer( ((ArrayList<edge>)graph[v]).get(i));

		int numEdges = 0, res = 0;

		while (pq.size() > 0) {

			// Get next edge.
			edge next = pq.poll();
			if (used[next.v1] && used[next.v2]) continue;

            // Add new items to priority queue - need to check which vertex is new.
			if (!used[next.v1]) {
                for (int i=0; i<graph[next.v1].size(); i++)
                    pq.offer( ((ArrayList<edge>)graph[next.v1]).get(i));
                used[next.v1] = true;
			}
			else {
                for (int i=0; i<graph[next.v2].size(); i++)
                    pq.offer( ((ArrayList<edge>)graph[next.v2]).get(i));
                used[next.v2] = true;
			}

			// Bookkeeping
			numEdges++;
			res += next.w;
			if (numEdges == n-1) break;
		}

		// -1 indicates no MST, so not connected.
		return numEdges == n-1 ? res : -1;
	}
}
