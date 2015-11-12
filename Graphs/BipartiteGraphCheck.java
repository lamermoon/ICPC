public static boolean bipartiteGraphCheck(ArrayList<ArrayList<Integer>> graph, int N) {
    int[] color = new int[N];
    for(int i = 0; i < N; i++) color[i] = -1;
    color[0] = 1;
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(0);
    while(!q.isEmpty()) {
        int u = q.poll();
        for(int i : graph.get(u)) {
            if(color[i] == -1) {
                color[i] = 1-color[u];
                q.add(i);
            } else if(color[u] == color[i]) {
                return false;
            }
        }
    }
    return true;
}