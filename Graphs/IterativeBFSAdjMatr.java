/* Breadth First Search AdjMtrx Iterative
 ** |V| + |E|
 * Iterative BFS on adjacency matrix. Needs a little adaption, if graph is not connected. \\
 * \emph{Input:} nodes s and g as int and graph as adjMatrix \\
 * \emph{Output:} true if there is a connection between s and g, false otherwise 
 */

//START
public static boolean BFSWithoutPathForAdjMatr(int s, int g, int[][] graph) {
	//s being the start and g the goal
	boolean[] visited = new boolean[graph.length];
	for(int i = 0; i < visited.length; i++)
		visited[i] = false;
	// FIFO-Queue
	Queue<Integer> queue = new LinkedList<Integer>();
	queue.add(s);
	visited[s] = true;
	// search all nodes reachable from s
	while(!queue.isEmpty()) {
		int node = queue.poll();
		// if goal reached, return true
		if(node == g)
			return true;
		// else add all neighbours to queue
		// if not yet visited
		for(int i = 0; i < graph.length; i++) {
			if(graph[node][i] > 0 && !visited[i]) {
				queue.add(i);
				visited[i] = true;
			}
		}
	}
	return false;
}
//END
