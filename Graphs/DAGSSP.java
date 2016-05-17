/* Single-source shortest paths in dag
** |V| + |E|
*
*/

//START
public static void dagSSP(Vertex[] G, int s) {
    //calls topological sort method
    LinkedList<Integer> sorting = TS(G);

    G[s].dist = 0;

    //go through vertices in ts order
    for(int u : sorting) {
	for(Edge e : G[u].adj) {
	    Vertex v = e.t;
	    if(v.dist > u.d + e.w) {
		v.dist = u.d + e.w;
		v.pre = u.id;
	    }
	}
    }
}
//END
