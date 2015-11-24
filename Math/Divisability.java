/* Divisability
 * Calculates (alternating) k-digitSum for integer number given by M.
 */

//START
public static long digit_sum(String M, int k, boolean alt) {
	long dig_sum = 0;
	int vz = 1;
	while (M.length() > k) {
		if (alt) vz *= -1;
		dig_sum += vz*Integer.parseInt(M.substring(M.length()-k));
		M = M.substring(0, M.length()-k);
	}
	if (alt) vz *= -1;
	dig_sum += vz*Integer.parseInt(M);
	return dig_sum;
}
// example: divisibility of M by 13
public static boolean divisible13(String M) {
	return digit_sum(M, 3, true)%13 == 0;
}
//END
