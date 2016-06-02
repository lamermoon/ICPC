/* BellmanFord
 ** |V|\cdot |E|
 * Finds shortest pathes from a single source. Negative edge weights are allowed. Can be used for finding negative cycles.
 */

//START
public static boolean bellmanFord(Vertex[] G) {
	//source is 0
	G[0].dist = 0;
	//calc distances
	//the path has max length |V|-1
	for(int i = 0; i < G.length-1; i++) {
		//each iteration relax all edges
		for(int j = 0; j < G.length; j++) {
			for(Edge e : G[j].adj) {
				if(G[j].dist != Integer.MAX_VALUE 
				&& e.t.dist > G[j].dist + e.w) {
					e.t.dist = G[j].dist + e.w;
				}
			}
		}
	}
	//check for negative-length cycle
	for(int i = 0; i < G.length; i++) {
		for(Edge e : G[i].adj) {
			if(G[i].dist != Integer.MAX_VALUE
			    && e.t.dist > G[i].dist + e.w) {
				return true;
			}
		}
	}
	return false;
}
//END
