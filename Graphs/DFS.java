/* Depth First Search
 ** |V|^2
 * Searches for a path between two vertices and in a graph per DFS. \\
 * \emph{Input:} A source vertex $s$, a target vertex $t$, an adjacency matrix $G$ and two new (empty) lists $path$ and $list$ (for recursion). \\
 * \emph{Output:} A boolean, indicating whether a path exists or not. If a path exists, a possible path is stored in $path$.
 */
import java.util.*;
class DFS {
//START
 public static boolean DFS(int s, int t, int[][] G, List<Integer> path, List<Integer> list) {
	// needed for start of recursion
	if (path.size() == 0)
		path.add(s);
	// return true if target reached
	if (s == t)
		return true;
	// otherwise recursively search neighbour
	for (int i = 0; i < G.length; i++) {
		// if node reachable but not yet visible
		if (G[s][i] > 0 && !list.contains(i)) {
			path.add(i); // i is on path from s to t
			list.add(i); // mark i as visited
			// if path from i to t found
			// return true
			if (DFS(i, t, G, path, list))
				return true;
			// else i is not on path from s to t
			// search next neighbour
			else
				path.remove(path.size() - 1);
		}
	}
	return false;
}
//END
}
