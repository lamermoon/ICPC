/*Breadth First Search
** |V| + |E|
* Iterative BFS. Needs testing. Uses ref Vertex class, no Edge class needed. 
* In this version we look for a shortest path from s to t though we could also find the BFS-tree by leaving out t.
*\emph{Input:} IDs of start and goal vertex and graph as AdjList
*\emph{Output:} \texttt{true}  if there is a connection between $s$ and $g$, \texttt{false} otherwise
*/

//START
public static boolean BFS(int s, int t, Vertex[] G) {

    //make sure that all Vertices vis values are false etc
    
    Queue<Vertex> q = new LinkedList<Vertex>();
    G[s].vis = true;
    G[s].dist = 0;
    G[s].pre = -1;
    q.add(G[s]);
    //expand frontier between undiscovered and discovered vertices
    while(!q.isEmpty()) {
	Vertex u = q.poll();
	//when reaching the goal, return true
	//if we want to construct a BFS-tree delete this line
	if(u.id = t) return true;
	//else add adj vertices if not visited
	for(Vertex v : u.adj) {
	    if(!v.vis) {
		v.vis = true;
		v.dist = u.dist + 1;
		v.pre = u.id;
		q.add(v);
	    }
	}
    }

}
