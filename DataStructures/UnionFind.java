/* Union-Find
 ** \alpha(n)
 * Union-Find is a data structure that keeps track of a set of elements partitioned into a number of disjoint subsets. $UnionFind$ creates $n$ disjoint sets each containing one element. $union$ joins the sets $x$ and $y$ are contained in. $find$ returns the representative of the set $x$ is contained in.\\
 * \emph{Input:} number of elements $n$, element $x$, element $y$\\
 * \emph{Output:} the representative of element $x$ or a boolean indicating whether sets got merged.
 */

//START
class UnionFind {
	private int[] p = null;
	private int[] r = null;
	private int count = 0;

	public int count() {
		return count;
	} // number of sets

	public UnionFind(int n) {
		count = n; // every node is its own set
		r = new int[n]; // every node is its own tree with height 0
		p = new int[n];
		for (int i = 0; i < n; i++)
			p[i] = -1; // no parent = -1
	}

	public int find(int x) {
		int root = x;
		while (p[root] >= 0) { // find root
			root = p[root];
		}
		while (p[x] >= 0) { // path compression
			int tmp = p[x];
			p[x] = root;
			x = tmp;
		}
		return root;
	}

	// return true, if sets merged and false, if already from same set
	public boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px == py)
			return false; // same set -> reject edge
		if (r[px] < r[py]) { // swap so that always h[px]>=h[py]
			int tmp = px;
			px = py;
			py = tmp;
		}
		p[py] = px; // hang flatter tree as child of higher tree
		r[px] = Math.max(r[px], r[py] + 1); // update (worst-case) height
		count--;
		return true;
	}
}
//END
