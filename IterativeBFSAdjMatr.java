//returns true or false, depending on whether there is a connection between s and g
public static boolean BFSWithoutPathForAdjMatr(int s, int g, int[][] graph) {
    //s being the start and g the goal
    boolean[] visited = new boolean[graph.length];
    for(int i = 0; i < visited.length; i++) visited[i] = false;
    Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(s);
    visited[s] = true;
    while(!queue.isEmpty()) {
        int node = queue.poll();
        if(node == g) return true;
        for(int i = 0; i < graph.length; i++) {
            if(graph[node][i] > 0 && !visited[i]) {
                queue.add(i);
                visited[i] = true;
            }
        }
     }
     return false;
}