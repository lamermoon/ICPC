/* Tuple
 ** 1
 * Simple tuple class used for priority queue in Dijkstra and Prim
 */

//START
class Tuple implements Comparable<Tuple> {

	int id;
	int dist;

	public Tuple(int id, int dist) {
		this.id = id;
		this.dist = dist;
	}

	public int compareTo(Tuple other) {
		return Integer.compare(this.dist, other.dist);
	}
}
//END
