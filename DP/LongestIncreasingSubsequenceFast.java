/* LongestIncreasingSubsequence
 ** n \log n
 * \emph{Input:} array $arr$ containing a sequence and empty array $p$ of length $arr.length$ for storing indices of the LIS \\
 * \emph{Output:} array $s$ containing the longest increasing subsequence
 */

//START
public static int[] LISfast(int[] arr, int[] p) {
	// p[k] stores index of the predecessor of arr[k]
	// in the LIS ending at arr[k]
	// m[j] stores index k of smallest value arr[k]
	// so there is a LIS of length j ending at arr[k]
	int[] m = new int[arr.length+1];
	int l = 0;
	for(int i = 0; i < arr.length; i++) {
		// bin search for the largest positive j <= l
		// with arr[m[j]] < arr[i]
		int lo = 1;
		int hi = l;
		while(lo <= hi) {
			int mid = (int) (((lo + hi) / 2.0) + 0.6);
			if(arr[m[mid]] <= arr[i])
				lo = mid+1;
			else
				hi = mid-1;
		}
		// lo is 1 greater than length of the
		// longest prefix of arr[i]
		int newL = lo;
		p[i] = m[newL-1];
		m[newL] = i;
		// if LIS found is longer than the ones
		// found before, then update l
		if(newL > l)
			l = newL;
	}
	// reconstruct the LIS
	int[] s = new int[l];
	int k = m[l];
	for(int i= l-1; i>= 0; i--) {
		s[i] = arr[k];
		k = p[k];
	}
	return s;
}
//END
