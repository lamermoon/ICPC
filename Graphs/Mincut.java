/* MinCut
 * Calculates the min-cut of a graph (represented as adjMtrx).
 */

//START
public static void MinCut(int s, int[][] graph, LinkedList<Integer> S, LinkedList<Integer> T) {
   boolean[] visited = new boolean[graph.length];
   for(int i = 0; i < visited.length; i++)
      visited[i] = false;
   Queue<Integer> queue = new LinkedList<Integer>();
   queue.add(s);
   S.add(s);
   visited[s] = true;
   while(!queue.isEmpty()) {
      int node = queue.poll();
      for(int i = 0; i < graph.length; i++) {
         if(graph[node][i] > 0 && !visited[i]) {
            queue.add(i);
            if(!S.contains(i))
               S.add(i);
            visited[i] = true;
         }
      }
   }
   for(int i = 0; i < graph.length; i++) {
      if(!S.contains(i)) {
         T.add(i);
      }
   }
   for(int i = 0; i < graph.length; i++) {
      for(int j = 0; j < graph.length; j++) {
         if((graph[i][j] > 0 || graph[j][i] > 0) && S.contains(i) && T.contains(j)) {
            System.out.println((i+1) + " " + (j+1));
         }
      }
   }
}
//END
