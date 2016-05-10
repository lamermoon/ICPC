/* Bipartite Graph Check
 ** |V| + |E|
 * Checks a graph represented as adjList for being bipartite. \\
 * INPUT: graph as adjList, amount of nodes N as int \\
 * OUTPUT: true if graph is bipartite, false otherwise
 */

//START
public static boolean bipartiteGraphCheck(
		ArrayList<ArrayList<Integer>> graph, int N) {
	int[] color = new int[N];
	for(int i = 0; i < N; i++) color[i] = -1;
	// use bfs for coloring each node
	color[0] = 1;
	// FIFO-Queue
	Queue<Integer> q = new LinkedList<Integer>();
	q.add(0);
	while(!q.isEmpty()) {
		int u = q.poll();
		for(int i : graph.get(u)) {
			// if node i not yet visited,
			// give opposite color of parent node u
			if(color[i] == -1) {
				color[i] = 1-color[u];
				q.add(i);
			// if node i has same color as parent node u
			// the graph is not bipartite
			} else if(color[u] == color[i])
				return false;
			// if node i has different color
			// than parent node u keep going
		}
	}
	return true;
}
//END
