/*Binary Search
 ** logn
 * binary searchs for an element in a sorted array
 */

class Main {
  public static void main(String[] args) {
  }

//START
public static boolean BinarySearch(int[] array, int N, int a) {
    int lo = 0;
    int hi = N-1;
    while(lo <= hi) {
        int mid = (int) (((lo + hi) / 2.0) + 0.6);
        if(array[mid] < a) {
            lo = mid+1;
         } else {
             hi = mid-1;
         }
     }
     if(lo < N  && array[lo] == a) {
        return true;
     } else {
        return false;
     }
}
//END

}