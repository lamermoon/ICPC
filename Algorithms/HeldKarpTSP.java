/* Held Karp
 ** 2^n n^2
 * Algorithm for TSP
 */


class Main {

    
//START
 public static int[] tsp(int[][] graph) {
   int n = graph.length;
   if(n == 1) return new int[]{0};
   //C stores the shortest distance to node of the second dimension
   //first dimension is the bitstring of included nodes on the way
   int[][] C = new int[1<<n][n];
   int[][] p = new int[1<<n][n];
   //initialize
   for(int k = 1; k < n; k++) {
      C[1<<k][k] = graph[0][k];
   }
   for(int s = 2; s < n; s++) {
      for(int S = 1; S < (1<<n); S++) {
         if(Integer.bitCount(S)!=s || (S&1) == 1)
            continue;
         for(int k = 1; k < n; k++) {
                 if((S & (1 << k)) == 0)
                     continue;
                 
            //Smk is the set of nodes without k
            int Smk = S ^ (1<<k);

            int min = Integer.MAX_VALUE;
            int minprev = 0;
            for(int  m=1; m<n; m++) {
               if((Smk & (1<<m)) == 0)
                  continue;
               //distance to m with the nodes in Smk + connection from m to k
               int tmp = C[Smk][m] +graph[m][k];
               if(tmp < min) {
                  min = tmp;
                  minprev = m;
               }
            }
            C[S][k] = min;
            p[S][k] = minprev;
         }
      }
   }
     
   //find shortest tour length
   int min = Integer.MAX_VALUE;
   int minprev = -1;
   for(int k = 1; k < n; k++) {
      //Set of all nodes except for the first + cost from 0 to k
      int tmp = C[(1<<n) - 2][k] + graph[0][k];
      if(tmp < min) {
         min = tmp;
         minprev = k;
      }
   }
     
   //Note that the tour has not been tested yet, only the correctness of the min-tour-value
   //backtrack tour
   int[] tour = new int[n+1];
   tour[n] = 0;
   tour[n-1] = minprev;
   int bits = (1<<n)-2;
   for(int k = n-2; k>0; k--) {
      tour[k] = p[bits][tour[k+1]];
      bits = bits ^ (1<<tour[k+1]);
   }
   tour[0] = 0;
   return tour;
}
//END
   
}
