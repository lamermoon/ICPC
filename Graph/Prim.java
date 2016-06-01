/* Prim
**
*
*/

//START
//s is the startpoint of the algorithm, in general not too important
//we assume that graph is connected
public static int prim(Vertex[] G, int s) {

    //make sure dists are maxint
    G[s].dist = 0;
    Tuple st = new Tuple(s, 0);

    PriorityQueue<Tuple> q = new PriorityQueue<Tuple>();
    q.add(st);

    //we will store the sum and each nodes predecessor
    int sum = 0;
    
    while(!q.isEmpty()) {
	Tuple sm = q.poll();
	Vertex u = G[sm.id];
	//u has been visited already
	if(u.vis) continue;
	//this is not the latest version of u
	if(sm.dist > u.dist) continue;
	u.vis = true;
	//u is part of the new tree and u.dist the cost of adding it
	sum += u.dist;
	for(Edge e : u.adj) {
	    Vertex v = e.t;
	    if(!v.vis && v.dist > e.w) {
		v.pre = u.id;
		v.dist = e.w;
		Tuple nt = new Tuple(v.id, e.w);
		q.add(nt);
	    }
	}
	
    }
    return sum;

}
//END
