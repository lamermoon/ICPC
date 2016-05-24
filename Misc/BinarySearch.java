/* Binary Search
 ** \log n
 * Binary searchs for an element in a sorted array. \\
 * \emph{Input:} sorted $array$ to search in, amount $N$ of elements in $array$, element to search for $a$ \\
 * \emph{Output:} \texttt{true}, if $array$ contains $a$, \texttt{false} otherwise
 */

class Main {
  public static void main(String[] args) {
	  
  }

//START
public static boolean BinarySearch(int[] array,
                                   int N, int a) {
	int lo = 0;
	int hi = N-1;
	// a might be in interval [lo,hi] while lo <= hi
	while(lo <= hi) {
		int mid = (int) (((lo + hi) / 2.0) + 0.6);
		// if a > elem in mid of interval,
		// search the right subinterval
		if(array[mid] < a)
			lo = mid+1;
		// else search the left subinterval
		else
			hi = mid-1;
	}
	// lo < N avoids ArrayOutOfBoundsException
	// if array[lo] == a, array contains a
	if(lo < N  && array[lo] == a)
		return true;
	else
		return false;
}
//END

}
