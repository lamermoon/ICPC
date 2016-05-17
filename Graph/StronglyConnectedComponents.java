/*Strongly Connected Components
**|V| + |E|
*
*/

//START
public static void fDFS(Vertex u, LinkedList<Integer> sorting) {

    //compare with TS
    u.vis = true;
    for(Vertex v : u.out) {
	if(!v.vis) {
	    fDFS(v, sorting);
	}
    }
    sorting.addFirst(u.id);
    return sorting;
}

public static void sDFS(Vertex u, cnt) {
    //basic DFS, all visited vertices get cnt
    u.vis = true;
    u.comp = cnt;
    for(Vertex v : u.in) {
	if(!v.vis) {
	    sDFS(u, cnt);
	}
    }
}

public static void doubleDFS(Vertex[] G) {

    //first calc a topological sort by first DFS
    LinkedList<Integer> sorting = new LinkedList<Integer>();
    for(int i = 0; i < G.length; i++) {
	if(!G[i].vis) {
	    fDFS(G[i], sorting);
	}
    }
    //then go through the sort and do another DFS on G^T
    //each tree is a component and gets a unique number
    int cnt = 0;
    for(int i : sorting) {
	if(!G[i].vis) {
	    sDFS(G[i], cnt++);
	}
    }
}

public static void SCC(Vertex[] G) {

    doubleDFS(Vertex[] G);
    

}
