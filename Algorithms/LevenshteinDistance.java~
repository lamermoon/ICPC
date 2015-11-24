/* Levenshtein Distance
** a\cdot b
 * Calculates the Levenshtein distance for two strings (minimum number of
 * insertions, deletions, or substitutions). \\
 * Input: A string $a$ and a string $b$.  \\
 * Output: An integer holding the distance.
 */
//START
public static int levenshteinDistance(String a, String b) {

	a = a.toLowerCase();
	b = b.toLowerCase();

	int[] costs = new int[b.length() + 1];

	for (int j = 0; j < costs.length; j++) {
		costs[j] = j;
	}

	for (int i = 1; i <= a.length(); i++) {
		costs[0] = i;
		int nw = i - 1;
		for (int j = 1; j <= b.length(); j++) {
			int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
					a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
			nw = costs[j];
			costs[j] = cj;
		}
	}

	return costs[b.length()];
}
//END
