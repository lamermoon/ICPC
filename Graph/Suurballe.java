/* Suurballe
**V log V + E
* Finds the min cost of two edge disjoint paths in a graph. If vertex disjoint needed, split vertices.
* \emph{Input:} Graph $G$, Source $s$, Target $t$
* \emph{Output:} Min cost as int
*/
//START
public static int suurballe(Vertex[] G, int s, int t) {
    //this uses the usual dijkstra implementation with stored predecessors
    dijkstra(G, s);
    //Modifying weights
    for(int i = 0; i < G.length; i++) {
	for(Edge e : G[i].adj) {
	    e.dist = e.dist - e.t.dist + G[i].dist;
	}
    }
    //reversing path and storing used edges
    int old = t;
    int pre = G[t].pre;
    HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    while(pre != -1) {
	for(int i = 0; i < G[pre].adj.size(); i++) {
	    if(G[pre].adj.get(i).t.id == old) {
		hm.put(pre * G.length + old, G[pre].adj.get(i).tdist);
		G[pre].adj.remove(i);
		break;
	    }
	}
	boolean found = false;
	for(int i = 0; i < G[old].adj.size(); i++) {
	    if(G[old].adj.get(i).t.id == pre) {
		G[old].adj.get(i).dist = 0;
		found = true;
		break;
	    }
	}
	if(!found) {
	    G[old].adj.add(new Edge(G[pre], 0));
	}
	old = pre;
	pre = G[pre].pre;
    }
    //reset graph
    for(int i = 0; i < G.length; i++) {
	G[i].pre = -1;
	G[i].dist = Integer.MAX_VALUE;
	G[i].vis = false;
    }
    
    dijkstra(G, s);
    //store edges of second path
    old = t;
    pre = G[t].pre;
    while(pre != -1) {
	//store edges and remove if reverse
	for(int i = 0; i < G[pre].adj.size(); i++) {
	    if(G[pre].adj.get(i).t.id == old) {
		if(!hm.containsKey(pre + old * G.length)) {
		    hm.put(pre * G.length + old, G[pre].adj.get(i).tdist);
		} else {
		    hm.remove(pre + old * G.length);
		}
		break;
	    }
	}
	old = pre;
	pre = G[pre].pre;
    }
    //sum up weights
    int sum = 0;
    for(int i : hm.keySet()) {
	sum += hm.get(i);
    }
    return sum;
} 
//END
