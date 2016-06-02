/* 2SAT
** O(V + E)
*/


//We assume that ind(not a) = ind(a) + N, with N being the number of variables
//could however be changed easily
public static boolean 2SAT(Vertex[] G) {

    //call SCC
    double DFS(G);
    //check for contradiction
    boolean poss = true;
    for(int i = 0; i < S+A; i++) {
	if(G[i].comp == G[i + (S+A)].comp) {
	    poss = false;
	}
    }
    return poss;

}
