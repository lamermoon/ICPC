/* Min Cut
* Calculates the min cut using Edmonds Karp algorithm.
*/

//this is a different bfs than the other used in edkarp in the sense that it will
//not stop when finding the target, actually this will never happen anyway
public static void bfs(Vertex[] G, int s) {
    for(int i = 0; i < G.length; i++) {
	G[i].vis = false;
    }
    Queue<Vertex> q = new LinkedList<Vertex>();
    q.add(G[s]);
    
    while(!q.isEmpty()) {
	Vertex u = q.poll();
	u.vis = true;
	
	for(int i : u.adj.keySet()) {
	    Edge e = u.adj.get(i);
	    if(e.rw == 0) continue;
	    Vertex v = e.t;
	    if(v.vis) continue;
	    q.add(v);
	}
    }
}

public static int minCut(Vertex[] G, int s, int t) {
    //get residual graph
    edmondsKarp(G, s, t);
    //find all vertices reachable from s
    bfs(G, s);
    int sum = 0;
    for(int i = 0; i < G.length; i++) {
	for(int j : G[i].adj.keySet()) {
	    Edge e = G[i].adj.get(j);
	    Vertex v = e.t;
	    //if i is reachable and j not this is a cut edge
	    if(G[i].vis && !G[j].vis) {
		//System.out.println((i+1) + " " + (j+1));
		sum += e.w;
	    }
	}
    }
    return sum;
}
