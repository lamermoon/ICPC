/* Kruskal
 ** |E| + \log |V|
 * Computes a minimum spanning tree for a weighted undirected graph.
 */

//START
public static int kruskal(Edge[] edges, int n) {
	Arrays.sort(edges);
	//n is the number of vertices
	UnionFind uf = new UnionFind(n);
	//we will only compute the sum of the MST, one could of course also store the edges
	int sum = 0;
	int cnt = 0;
	for(int i = 0; i < edges.length; i++) {
		if(cnt == n-1) break;
		if(uf.union(edges[i].s, edges[i].t)) {
			sum += edges[i].w;
			cnt++;
		}
	}
	return sum;
}
//END
