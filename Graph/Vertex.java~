/*Reference for Vertex classes
 *Used in many graph algorithms, implements a vertex with its edges. Needs testing.
 */


//START
class Vertex {

    int id;
    boolean vis = false;
    int pre = -1;

    //for dijkstra and prim
    int dist = Integer.MAX_VALUE;

    //for SCC store number indicating the dedicated component
    int comp = -1;

    //for DFS we could store the start and finishing times
    int dtime = -1;
    int ftime = -1;
    
    //use an ArrayList of Edges if those information are needed
    ArrayList<Edge> adj = new ArrayList<Edge>();
    //use an ArrayList of Vertices else
    ArrayList<Vertex> adj = new ArrayList<Vertex>();
    //use two ArrayLists for SCC
    ArrayList<Vertex> in = new ArrayList<Vertex>();
    ArrayList<Vertex> out = new ArrayList<Vertex>();

    //for EdmondsKarp we need a HashMap to store Edges
    HashMap<Integer, Edge> adj = new HashMap<Integer, Edge>();

    //for bipartite graph check
    int color = -1;

    //we store as key the target
    public Vertex(int id) {
	this.id = id;
    }

}
