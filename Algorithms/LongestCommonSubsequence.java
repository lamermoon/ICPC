/* Longest Common Subsequence
** n \cdot m
 * Finds the longest common subsequence of two strings. \\
 * Input: Two strings $string1$ and $string2$. \\
 * Output: The LCS as a string.
 */
//START
public static String longestCommonSubsequence(String string1, String string2) {

	char[] s1 = string1.toCharArray();
	char[] s2 = string2.toCharArray();

	int[][] num = new int[s1.length + 1][s2.length + 1];

	// Actual algorithm
	for (int i = 1; i <= s1.length; i++)
		for (int j = 1; j <= s2.length; j++)
			if (s1[i - 1] == s2[j - 1])
				num[i][j] = 1 + num[i - 1][j - 1];
			else
				num[i][j] = Math.max(num[i - 1][j], num[i][j - 1]);

	// System.out.println("length of LCS = " + num[s1.length][s2.length]);

	int s1position = s1.length, s2position = s2.length;
	List<Character> result = new LinkedList<Character>();

	while (s1position != 0 && s2position != 0) {
		if (s1[s1position - 1] == s2[s2position - 1]) {
			result.add(s1[s1position - 1]);
			s1position--;
			s2position--;
		} else if (num[s1position][s2position - 1] >= num[s1position][s2position]) {
			s2position--;
		} else {
			s1position--;
		}
	}
	Collections.reverse(result);

	char[] resultString = new char[result.size()];
	int i = 0;

	for (Character c : result) {
		resultString[i] = c;
		i++;
	}

	return new String(resultString);
}
//END
