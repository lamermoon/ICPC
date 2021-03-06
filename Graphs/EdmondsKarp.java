/* EdmondsKarp
 ** |V|^2 \cdot |E|
 * Finds the greatest flow in a graph. Capacities must be positive.
 */

//START
public static boolean BFS(int[][] graph, int s, int t, int[] parent) {
   int N = graph.length;
   boolean[] visited = new boolean[N];
   for(int i = 0; i < N; i++) {
      visited[i] = false;
   }
   Queue<Integer> queue = new LinkedList<Integer>();
   queue.add(s);
   visited[s] = true;
   parent[s] = -1;
   while(!queue.isEmpty()) {
      int u = queue.poll();
      if(u == t) return true;
      for(int v= 0; v < N; v++) {
         if(visited[v] == false && graph[u][v] > 0) {
            queue.add(v);
            parent[v] = u;
            visited[v] = true;
         }
      }
   }
   return (visited[t]);
}
//END

//START
public static int fordFulkerson(int[][] graph, int s, int t) {
   int N = graph.length;
   int[][] rgraph = new int[graph.length][graph.length];
   for(int u = 0; u < graph.length; u++) {
      for(int v = 0; v < graph.length; v++) {
         rgraph[u][v] = graph[u][v];
      }
   }
   int[] parent = new int[N];
   int maxflow = 0;
   while(BFS(rgraph, s, t, parent)) {
      int pathflow = Integer.MAX_VALUE;
      for(int v = t; v!= s; v = parent[v]) {
         int u = parent[v];
         pathflow = Math.min(pathflow, rgraph[u][v]);
      }

      for(int v = t; v != s; v = parent[v]) {
         int u = parent[v];
         rgraph[u][v] -= pathflow;
         rgraph[v][u] += pathflow;
      }

      maxflow += pathflow;
   }
   return maxflow;
}
//END
