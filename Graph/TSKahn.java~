/* Kahns Algorithm for TS
** V+E
* Gives the specific TS where Vertices first in G are first in the sorting
*/


public static LinkedList<Integer> TS(Vertex[] G) {
    
    LinkedList<Integer> sorting = new LinkedList<Integer>();
    PriorityQueue<Vertex> p = new PriorityQueue<Vertex>();
    //inc counts the number of incoming edges, if they are zero put the vertex in the queue
    for(int i = 0; i < G.length; i++) {
	if(G[i].inc == 0) {
	    p.add(G[i]);
	    G[i].vis = true;
	}
    }
    while(!p.isEmpty()) {
	Vertex u = p.poll();
	sorting.add(u.id);
	//update inc
	for(Vertex v : u.out) {
	    if(v.vis) continue;
	    v.inc--;
	    if(v.inc == 0) {
		p.add(v);
		v.vis = true;
	    }
	}
    }
    return sorting;
}
