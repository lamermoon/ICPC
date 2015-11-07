import java.util.ArrayList;
import java.util.Scanner;
/*
 * TopologischeSortierung
 * Sortiert einen Grafen (hier als AdjMtrx) topologisch
 * Laufzeit: O(V+E)
 */

class OrderingTasks {

    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			int n = scan.nextInt();
			int m = scan.nextInt();
			if (n == 0 && m == 0) {
				break;
			}
			boolean[][] adjMtrx = new boolean[n][n];
			int[] edgesIn = new int[n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					adjMtrx[i][j] = false;
				}
				edgesIn[i] = 0;
			}
			for (int i = 0; i < m; i++) {
				int a = scan.nextInt();
				int b = scan.nextInt();
				adjMtrx[a - 1][b - 1] = true;
				edgesIn[b - 1] += 1;
			}
			// Einlesen Ende
//START	
			// l enthaelt alle Knoten topologisch sortiert (Start: 0, Ende= n)
			int[] l = new int[n];
			int idx = 0;
			// s enthaelt alle Knoten, die keine eingehende Kante haben
			ArrayList<Integer> s = new ArrayList<Integer>();
			// initialisiere s
			for (int i = 0; i < n; i++) {
				if (edgesIn[i] == 0) {
					s.add(i);
				}
			}
			// Algo Beginn
			while (!s.isEmpty()) {
				int node = s.remove(0);
				l[idx++] = node;
				for (int i = 0; i < n; i++) {
					if (adjMtrx[node][i]) {
						adjMtrx[node][i] = false;
						edgesIn[i] -= 1;
						if (edgesIn[i] == 0) {
							s.add(i);
						}
					}
				}
			}
//END
			System.out.print(l[0] + 1);
			for (int i = 1; i < n; i++) {
				System.out.print(" " + (l[i] + 1));
			}
			System.out.println();
		}
		scan.close();
    }
}
