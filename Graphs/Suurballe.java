/* Suurballe
 * Finds two edge-disjoint paths from s to t with minimal sum length, depends on
 *  Dijkstra. Add to Vertex class 2 HashMaps backupNext and resultSuurballe.
 * For also vertex-disjoint paths split vertices in in- and outgoing vertices
 * connected with zero-valued edges.
 */
import java.util.*;
class Vertex implements Comparable<Vertex> {
	public int id;
	public int dist;
	public int pred;
	public boolean removed;
	public HashMap<Integer,Integer> next = new HashMap<Integer,Integer>();
	public HashMap<Integer,Integer> backupNext = new HashMap<Integer,Integer>();
	public HashMap<Integer,Integer> suurbaleResult = new HashMap<Integer,Integer>();
	public Vertex(int i){id=i;}
	public int compareTo(Vertex v) {
		if (dist < v.dist) return -1;
		else if (dist > v.dist) return 1;
		return 0;
	}
}
class Suurballe {
//START
public static int suurballe(int s, int t, Vertex[] G) {
	dijkstra(s, G); //find a shortest path
	ArrayList<Integer> path = new ArrayList<Integer>();
	int id = t;
	while (G[id].pred != id) {
		path.add(0, id);
		id = G[id].pred;
	}
	path.add(0, id);

	//modify weights
	for (int i=0; i<G.length; i++) {
		Vertex u = G[i];
		if (u==null) continue;
		u.backupNext = new HashMap<Integer,Integer>(u.next); //copy old values
		for (Integer j : u.backupNext.keySet()) {
			Vertex v = G[j];
			int weight = u.next.get(j);
			u.next.put(j, weight - v.dist + u.dist);
		}
	}
	//reverse edges on shortest path
	id = s;
	for (int i=0; i<path.size()-1; i++) {
		G[path.get(i)].next.remove(path.get(i+1));
		G[path.get(i+1)].next.put(path.get(i), 0);
	}
	//remove edges to s
	for (int i=0; i<G.length; i++) {
		if (G[i]==null) continue;
		if (G[i].next.containsKey(s))
			G[i].next.remove(s);
	}

	dijkstra(s, G);
	ArrayList<Integer> path2 = new ArrayList<Integer>();
	id = t;
	if (G[id].pred == -1)
		return -1; //no 2nd path!

	while (G[id].pred != id) {
		path2.add(0, id);
		id = G[id].pred;
	}
	path2.add(0, id);

	int totalpath = 0;

	//disregard 0-cycles and edges not on both paths
	id = s;
	//add edges on first shortest path
	for (int i=0; i<path.size()-1; i++) {
		int u = path.get(i);
		int v = path.get(i+1);

		G[u].suurbaleResult.put(v, G[u].backupNext.get(v));
		totalpath += G[u].suurbaleResult.get(v);
	}
	//add second path, remove cycles
	for (int i=0; i<path2.size()-1; i++) {
		int u = path2.get(i);
		int v = path2.get(i+1);

		if (G[v].suurbaleResult.containsKey(u)) {
			totalpath -= G[v].suurbaleResult.get(u);
			G[v].suurbaleResult.remove(u);
		} else {
			G[u].suurbaleResult.put(v, G[u].backupNext.get(v));
			totalpath += G[u].suurbaleResult.get(v);
		}
	}

	return totalpath;
}
//END
public static void dijkstra(int s, Vertex[] G) {
	PriorityQueue<Vertex> H = new PriorityQueue<Vertex>();

	Vertex start = G[s];
	start.pred = start.id;
	for (int i=0; i<G.length; i++) { // init dists with infty
		if (G[i]==null) continue;
		Vertex ivert = G[i];
		ivert.dist = Integer.MAX_VALUE;
		ivert.pred = -1;
		ivert.removed = false;
	}
	start.pred = start.id; //init start vertex
	start.dist = 0;
	H.add(G[s]);

	while (!H.isEmpty()) {
		Vertex u = H.poll();
		if (u.removed) continue;
		u.removed = true;

		for (int v : u.next.keySet()) {
			Vertex vert = G[v];
			int utov = u.next.get(v);
			if (vert.dist > u.dist + utov) {
				vert.dist = u.dist + utov;
				vert.pred = u.id;
				H.add(vert);
			}
		}
	}
}
}
