/*Dijkstra
 * finds all shortest paths from vertex src
 *does not work with negative weights
 */

//START
public static void dijkstra(Vertex[] vertices, int src) {
    vertices[src].mindistance = 0;
    PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
    queue.add(vertices[src]);
    while(!queue.isEmpty()) {
        Vertex u = queue.poll();
        if(u.visited) continue;
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

