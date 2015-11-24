/* Bitonic TSP
 * Input: Distance matrix $d$ with vertices sorted in x-axis direction. \\
 * Output: Shortest bitonic tour length
 */
class Main {
//START
public static double bitonic(double[][] d) {
	int N = d.length;
	double[][] B = new double[N][N];
	for (int j = 0; j < N; j++) {
		for (int i = 0; i <= j; i++) {
			if (i < j - 1)
				B[i][j] = B[i][j - 1] + d[j - 1][j];
			else {
				double min = 0;
				for (int k = 0; k < j; k++) {
					double r = B[k][i] + d[k][j];
					if (min > r || k == 0)
						min = r;
					}
				B[i][j] = min;
			}
		}
	}
	return B[N-1][N-1];
}
//END
}
