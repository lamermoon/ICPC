/* Single-source shortest paths in dag
** |V| + |E|
* Not tested but should be working fine
* Similar approach can be used for longest paths. Simply go through ts and add
* 1 to the largest longest path value of the incoming neighbors
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
	    if(v.dist > u.dist + e.w) {
		v.dist = u.dist + e.w;
		v.pre = u.id;
	    }
	}
    }
}
//END
