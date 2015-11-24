/* Kruskal
 ** E + \log E
 * Computes a minimum spanning tree
 */

import java.util.Arrays;
import java.util.Scanner;

//START
public class Freckles {
   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      int t = s.nextInt();
      for(int i = 0; i < t; i++) {
         int n = s.nextInt();
         double[] x = new double[n];
         double[] y = new double[n];
         for(int j = 0; j < n; j++) {
            x[j] = s.nextDouble();
            y[j] = s.nextDouble();
         }
         Edge1[] edge = new Edge1[n*n];
         for(int j = 0; j < n; j++) {
            for(int l = 0; l < n; l++) {
               double distance = Math.sqrt((x[l]-x[j]) * (x[l] - x[j]) + (y[l]-y[j]) * (y[l] - y[j]));
               edge[j * n + l] = new Edge1(distance, j, l);
            }
         }
         Arrays.sort(edge);
         UnionFind uf = new UnionFind(n);
         double sum = 0;
         int  cnt = 0;
         for(int j = 0; j < n*n; j++) {
            if(cnt == n-1) 
               break;
            if(uf.union(edge[j].start, edge[j].end)) {
               sum += edge[j].distance;
               cnt++;
            }
         }
         System.out.printf("%.2f\n", sum);
         if(i < t-1)
            System.out.println();
      }
   }
}

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

class Edge1 implements Comparable<Edge1> {
   double distance;
   int start;
   int end;

   public Edge1(double distance, int start, int end) {
      this.distance = distance;
      this.start = start;
      this.end = end;
   }

   public int compareTo(Edge1 arg0) {
      return Double.compare(this.distance, arg0.distance);
   }
}
//END
