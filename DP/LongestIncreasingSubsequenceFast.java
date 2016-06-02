/* LongestIncreasingSubsequence
 ** n \log n
 * Computes the longest increasing subsequence using binary search.
 */

//START
public static int[] LongestIncreasingSubsequencenlogn(int[] a, int[] p) {
   int[] m = new int[a.length+1];
   int l = 0;
   for(int i = 0; i < a.length; i++) {
      int lo = 1;
      int hi = l;
      while(lo <= hi) {
         int mid = (int) (((lo + hi) / 2.0) + 0.6);
         if(a[m[mid]] < a[i]) {
            lo = mid+1;
         } else {
            hi = mid-1;
         }
      }
      int newL = lo;
      p[i] = m[newL-1];
      m[newL] = i;
      if(newL > l) {
         l = newL;
      }
   }
   int[] s = new int[l];
   int k = m[l];
   for(int i= l-1; i>= 0; i--) {
      s[i] = a[k];
      k = p[k];
   }
   return s;
}
//END
