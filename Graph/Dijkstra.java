/* Dijkstra
 ** |E|\log |V|
 * Finds the shortest paths from one vertex to every other vertex in the graph (SSSP).\\
 * For negative weights, add |min|+1 to each edge, later subtract from result.\\
 * To get a different shortest path when edges are ints, add an
 * $\epsilon=\frac{1}{k+1}$ on each edge of the shortest path of length $k$, run again. \\
 * \emph{Input:} A source vertex $s$ and an adjacency list $G$. \\
 * \emph{Output:} Modified adj. list with distances from s and predcessor vertices set.
 */

//START
public static void dijkstra(Vertex[] G, int s) {

    G[s].dist = 0;
    Tuple st = new Tuple(s, 0);
    PriorityQueue<Tuple> q = new PriorityQueue<Tuple>();
    q.add(st);
    
    while(!q.isEmpty()) {
	Tuple sm = q.poll();
	Vertex u = G[sm.id];
	//this checks if the Tuple is still useful, both checks should be equivalent
	if(u.vis || sm.dist > u.dist) continue;
	u.vis = true;
	for(Edge e : u.adj) {
	    Vertex v = e.t;
	    if(!v.vis && v.dist > u.dist + e.w) {
		v.pre = u.id;
		v.dist = u.dist + e.w;
		Tuple nt = new Tuple(v.id, v.dist);
		q.add(nt);
	    }
	}
    }
}
//END

