/* Depth First Search
 ** n^2
 * Searches for a path between two vertices in a graph per DFS. \\
 * Input: A source vertex $s$, a target vertex $t$, an adjacency matrix $G$ and two new (empty) lists $path$ and $list$ (for recursion). \\
 * Output: A boolean, indicating whether a path exists or not. If a path exists, a possible path is stored in $path$.
 */
import java.util.*;
class DFS {
//START
 public static boolean DFS(int s, int t, int[][] G, List<Integer> path, List<Integer> list) {
	if (path.size() == 0) {
		path.add(s);
	}
	if (s == t) {
		return true;
	}

	for (int i = 0; i < G.length; i++) {
		if (G[s][i] > 0 && !list.contains(i)) {
			path.add(i);
			list.add(i);
			if (DFS(i, t, G, path, list)) {
				return true;
			} else {
				path.remove(path.size() - 1);
			}
		}
	}
	return false;
}
//END
}
