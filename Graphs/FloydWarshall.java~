/*floydWarshall
 ** n^3
 * finds all shortest paths
 * paths in array next, distances in ans
 */

//START
public static void floydWarshall(int[][] graph, int[][] next, int[][] ans) {
    for(int i = 0; i < ans.length; i++) {
        for(int j = 0; j < ans.length; j++) {
            ans[i][j] = graph[i][j];
        }
    }
    for (int k = 0; k < ans.length; k++) {
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans.length; j++) {
                if (ans[i][k] + ans[k][j] < ans[i][j]
                    && ans[i][k] < Integer.MAX_VALUE && ans[k][j] < Integer.MAX_VALUE) {
                    ans[i][j] = ans[i][k] + ans[k][j];
                    next[i][j] = next[i][k];
                }
            }
        }
    }
}
//END