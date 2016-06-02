/* Recursive Depth First Search
** |V| + |E|
* Recursive DFS with different options (storing times, connected/unconnected graph).
* Needs testing.\\
* \emph{Input:} A source vertex $s$, a target vertex $t$, and adjlist $G$ and the time (0 at the start)\\
* \emph{Output:} Indicates if there is connection between $s$ and $t$.
*/

//START
//if we want to visit the whole graph, even if it is not connected we might use this
public static void DFS(Vertex[] G) {
	//make sure all vertices vis value is false etc 
	int time = 0;
	for(int i = 0; i < G.length; i++) {
		if(!G[i].vis) {
			//note that we leave out t so this does not work with the below function
			//adaption will not be too difficult though
			//time should not always start at zero, change if needed
			recDFS(i, G, 0);
		}
	}
}

//first call with time = 0
public static boolean recDFS(int s, int t, Vertex[] G, int time){
	//it might be necessary to store the time of discovery
	time = time + 1;
	G[s].dtime = time;

	G[s].vis = true; //new vertex has been discovered
	//when reaching the target return true
	//not necessary when calculating the DFS-tree
	if(s == t) return true;
	for(Vertex v : G[s].adj) {
		//exploring a new edge
		if(!v.vis) {
			v.pre = u.id;
			if(recDFS(v.id, t, G)) return true;
		}
	}
	//storing finishing time
	time = time + 1;
	G[s].ftime = time;
	return false;
}
