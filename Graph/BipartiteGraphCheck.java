/* Bipartite Graph Check
 ** |V| + |E|
 * Checks a graph represented as adjList for being bipartite. Needs a little adaption, if the graph is not connected. \\
 * \emph{Input:} $graph$ as adjList, amount of nodes $N$ as int \\
 * \emph{Output:} \texttt{true} if graph is bipartite, \texttt{false} otherwise
 */

//START
public static boolean bipartiteGraphCheck(Vertex[] G) {

    // use bfs for coloring each node
    G[0].color = 1;
    Queue<Vertex> q = new LinkedList<Vertex>();
    q.add(G[0]);
    while(!q.isEmpty()) {
	Vertex u = q.poll();
	for(Vertex v : u.adj) {
	    // if node i not yet visited,
	    // give opposite color of parent node u
	    if(v.color == -1) {
		v.color = 1-u.color;
		q.add(v);
		// if node i has same color as parent node u
		// the graph is not bipartite
	    } else if(u.color == v.color)
		return false;
	    // if node i has different color
	    // than parent node u keep going
	}
    }
    return true;
}
//END
