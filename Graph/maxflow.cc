/* EdmondsKarp
 ** |V|^2 \cdot |E|
 * Finds the greatest flow in a graph. Capacities must be positive.
 */

//START
#include<iostream>
#include<vector>
#include<queue>
#include<unordered_map>
#include<cmath>

using namespace std;

bool bfs(vector<unordered_map<int, long long>> &g, int s, int t, vector<int> &pre)
{
    int n = g.size();
    for(int i = 0; i < n; ++i) {
        pre[i] = -1;
    }
    vector<bool> vis (n);
    queue<int> q;
    vis[s] = true;
    q.push(s);
    while(!q.empty()) {
        int u = q.front();
        q.pop();
        if(u == t) return true;
        for(auto v = g[u].begin(); v != g[u].end(); ++v) {
            if(!vis[v->first] && (v->second) > 0) {
                vis[v->first] = true;
                pre[v->first] = u;
                q.push(v->first);
            }
        }
    }
    return vis[t];
}

long long ed_karp(vector<unordered_map<int, long long>> &g, int s, int t)
{
    long long mxf = 0;
    int n = g.size();
    vector<int> pre (n);
    while(bfs(g, s, t, pre)) {
        long long pf = (1L << 58);
        for(int v = t; v != s; v = pre[v]) {
            int u = pre[v];
            pf = min(pf, g[u][v]);
        }
        for(int v = t; v != s; v = pre[v]) {
            int u = pre[v];
            g[u][v] -= pf;
            g[v][u] += pf;
        }
        mxf += pf;
    }
    return mxf;
}
//END
