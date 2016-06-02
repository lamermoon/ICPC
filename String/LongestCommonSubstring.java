/* Longest common substring
** |a| * |b|
* gets two String and finds all LCSs and returns them in a set
*/


public static TreeSet<String> LCS(String a, String b) {

    int[][] t = new int[a.length()+1][b.length()+1];
    for(int i = 0; i <= b.length(); i++) {
	t[0][i] = 0;
    }
    
    for(int i = 0; i <= a.length(); i++) {
	t[i][0] = 0;
    }
    
    for(int i = 1; i <= a.length(); i++) {
	for(int j = 1; j <= b.length(); j++) {
	    if(a.charAt(i-1) == b.charAt(j-1)) {
		t[i][j] = t[i-1][j-1] + 1;
	    } else {
		t[i][j] = 0;
	    }
	}
    }
    int max = -1;
    for(int i = 0; i <= a.length(); i++) {
	for(int j = 0; j <= b.length(); j++) {
	    if(max < t[i][j]) {
		max = t[i][j];
	    }
	}
    }
    if(max == 0 || max == -1) return new TreeSet<String>();
    TreeSet<String> res = new TreeSet<String>();
    for(int i = 0; i <= a.length(); i++) {
	for(int j = 0; j <= b.length(); j++) {
	    if(max == t[i][j]) {
		res.add(a.substring(i-max, i));
	    }
	}
    }
    return res;
    
}
