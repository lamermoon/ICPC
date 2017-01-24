/* Dijkstra
 ** |E|\log |V|
 * Finds the shortest paths from one vertex to every other vertex in the graph (SSSP).\\
 * For negative weights, add |min|+1 to each edge, later subtract from result.\\
 * To get a different shortest path when edges are ints, add an
 * $\varepsilon=\frac{1}{k+1}$ on each edge of the shortest path of length $k$, run again. \\
 * \emph{Input:} A source vertex $s$ and an adjacency list $G$. \\
 * \emph{Output:} Modified adj. list with distances from s and predcessor vertices set.
 */

//START
int mxi = (1 << 25);

bool cmp(pair<int, int> a, pair<int, int> b)
{
    return (a.second > b.second);
}

int dijkstra(vector<vector<pair<int, int>>> &g, int N)
{
    priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(cmp) *> pq(cmp);
    vector<int> dist (N, mxi);
    dist[0] = 0;
    pq.push({0, 0});
    while(!pq.empty()) {
        int u = pq.top().first;
        int d = pq.top().second;
        pq.pop();
        if(d > dist[u]) continue;
        if(u == N-1) return d;
        for(auto it = g[u].begin(); it != g[u].end(); ++it) {
            int v = it -> first;
            int w = it -> second;
            if(w + dist[u] < dist[v]) {
                dist[v] = w + dist[u];
                pq.push({v, dist[v]});
            }
        }
    }
    return dist[N-1];
}
//END
