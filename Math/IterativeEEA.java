/* Extended Euclidean Algorithm (iterative)
 ** \log a + \log b
 * Calculates the gcd of two numbers $a$ and $b$ and their modular inverse $x = a^{-1}\mod b$ and $y = b^{-1}\mod a$.\\
 * \emph{Input:} Numbers $a$ and $b$ or array of numbers $input$\\
 * \emph{Output:} Least common multiple of the input
 */

import java.util.Scanner;

public class IterativeEEA {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			long a = scan.nextLong();
			long b = scan.nextLong();
			System.out.println();
			long[] eea = eea(a, b);
			System.out.println("gcd = " + eea[0] + ", x = " + eea[1] + ", y = "
					+ eea[2]);
		}
	}
//START
// Extended Euclidean Algorithm - iterativ
public static long[] eea(long a, long b) {
	if (b > a) {
		long tmp = a;
		a = b;
		b = tmp;
	}
	long x = 0, y = 1, u = 1, v = 0;
	while (a != 0) {
		long q = b / a, r = b % a;
		long m = x - u * q, n = y - v * q;
		b = a; a = r; x = u; y = v; u = m; v = n;
	}
	long gcd = b;
	// x = a^-1 % b, y = b^-1 % a
	// ax + by = gcd
	long[] erg = { gcd, x, y };
	return erg;
}
//END
}
