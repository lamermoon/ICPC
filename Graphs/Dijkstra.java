/* Dijkstra
 ** |E|\log |V|
 * Finds the shortest paths from one vertex to every other vertex in the graph (SSSP).\\
 * For negative weights, add |min|+1 to each edge, later subtract from result.\\
 * To get a different shortest path when edges are ints, add an
 * $\epsilon=\frac{1}{k+1}$ on each edge of the shortest path of length $k$, run again. \\
 * \emph{Input:} A source vertex $s$ and an adjacency list $G$. \\
 * \emph{Output:} Modified adj. list with distances from s and predcessor vertices set.
 */

//START
public static void dijkstra(Vertex[] vertices, int src) {
   vertices[src].mindistance = 0;
   PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
   queue.add(vertices[src]);
   while(!queue.isEmpty()) {
      Vertex u = queue.poll();
      if(u.visited)
         continue;
      u.visited = true;
      for(Edge e : u.adjacencies) {
         Vertex v = e.target;
         if(v.mindistance > u.mindistance + e.distance) {
            v.mindistance = u.mindistance + e.distance;
            queue.add(v);
         }
      }
   }
}
//END

//START
class Vertex implements Comparable<Vertex> {
   public int id;
   public int mindistance = Integer.MAX_VALUE;
   public LinkedList<Edge> adjacencies = new LinkedList<Edge>();
   public boolean visited = false;

   public int compareTo(Vertex other) {
      return Integer.compare(this.mindistance, other.mindistance);
   }
}
//END

//START
class Edge {
   public Vertex target;
   public int distance;
   
   public Edge (Vertex target, int distance) {
      this.target = target;
      this.distance = distance;
   }
}
//END

