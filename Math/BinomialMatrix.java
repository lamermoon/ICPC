/* Binomial Matrix 
 ** N \cdot K
 * Gives binomial coefficients for all K <= N.
 */
 
//START
public static long[][] binomial_matrix(int N, int K) {
	long[][] B = new long[N+1][K+1];
	for (int k = 1; k <= K; k++) { 
		B[0][k] = 0;
	}
	for (int m = 0; m <= N; m++) {
		B[m][0] = 1;
	}
	for (int m = 1; m <= N; m++) {
		for (int k = 1; k <= K; k++) {
			B[m][k] = B[m-1][k-1] + B[m-1][k];
		}
	}
	return B;
}
//END
