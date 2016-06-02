/* Knuth-Morris-Pratt
 * \emph{Input:} String $s$ to be searched, String $w$ to search for. \\
 * \emph{Output:} Array with all starting positions of matches
 ** n+m
 */
import java.util.ArrayList;
class Main {
public static void main(String[] args) {
	String str = args[0];
	String match = args[1];

	ArrayList<Integer> matches = kmp(str, match);

	System.out.println("Matches at: ");
	for (int m : matches)
		System.out.print(m+", ");
	System.out.println("");
}

//START
public static ArrayList<Integer> kmp(String s, String w) {
	ArrayList<Integer> ret = new ArrayList<>();
	//Build prefix table
	int[] N = new int[w.length()+1];
	int i=0; int j =-1; N[0]=-1;
	while (i<w.length()) {
		while (j>=0 && w.charAt(j) != w.charAt(i))
			j = N[j];
		i++; j++; N[i]=j;
	}
	//Search string
	i=0; j=0;
	while (i<s.length()) {
		while (j>=0 && s.charAt(i) != w.charAt(j))
			j = N[j];
		i++; j++;
		if (j==w.length()) { //match found
			ret.add(i-w.length()); //add its start index
			j = N[j];
		}
	}
	return ret;
}
//END

}
