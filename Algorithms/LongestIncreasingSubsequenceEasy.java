/*LongestIncreasingSubsequence
 *computes the LIS in quadratic time, but is easy to adapted
 */

//START
//This has not been tested yet (adapted from tested C++ Murcia Code)
public static int longestInc(int[] array, int N) {
    int[] m = new int[N];
    for (int i = N - 1; i >= 0; i--) {
        m[i] = 1;
        for (int j = i + 1; j < N; j++) {
            if (array[j] > array[i]) {
                if (m[i] < m[j] + 1) {
                    m[i] = m[j] + 1;
                }
            }
        }
    }
    int longest = 0;
    for (int i = 0; i < N; i++) {
        if (m[i] > longest) {
            longest = m[i];
        }
    }
    return longest;
}
//END