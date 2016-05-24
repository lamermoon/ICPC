/* Binary Search
 ** \log n
 * Binary searchs for an element in a sorted array. \\
 * \emph{Input:} sorted $array$ to search in, amount $N$ of elements in $array$, element to search for $a$ \\
 * \emph{Output:} returns the index of $a$ in $array$ or $-1$ if $array$ does not contain $a$
 */

class Main {
  public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int[] arr = new int[args.length-1];
		for(int i = 1; i < args.length; i++){
			arr[i-1] = Integer.parseInt(args[i]);
		}
		System.out.println(BinarySearch(arr, arr.length, a));
  }

//START
public static int BinarySearch(int[] array,
                                   int N, int a) {
	int lo = 0;
	int hi = N-1;
	// a might be in interval [lo,hi] while lo <= hi
	while(lo <= hi) {
		int mid = (lo + hi) / 2;
		// if a > elem in mid of interval,
		// search the right subinterval
		if(array[mid] < a)
			lo = mid+1;
		// else if a < elem in mid of interval,
		// search the left subinterval
		else if(array[mid] > a)
			hi = mid-1;
		// else a is found
		else
			return mid;
	}
	// array does not contain a
	return -1;
}
//END

}
