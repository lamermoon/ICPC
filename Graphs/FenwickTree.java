import java.util.Scanner;

class MovieCollection {
    // Fenwick Tree
    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		for (int r = 0; r < cases; r++) {
			// Eingabe einlesen
			int n = scan.nextInt();
			int m = scan.nextInt();
			int[] a = new int[m + 1];
			int[] mn = new int[m + n + 1];
			int[] pos = new int[n + 1];
			// mn ist tempraerer Fenwick Tree
			int[] fwktree = new int[m + n + 1];
			//
			for (int i = 1; i <= (m > n ? m : n); i++) {
			if (i <= m) {
				a[i] = scan.nextInt();
				mn[i] = 0;
			}
			if (i <= n) {
				pos[i] = i + m;
				mn[i + m] = 1;
			}
			}
			// Eingabe Ende

			// Tree init
			for (int i = 1; i < fwktree.length; i++) {
			fwktree = update(i, mn[i], fwktree);
			}
			//
			// Fuehre Umstapelung durch
			String answer = "";
			int idx = 1;
			for (int i = m; i > 0; i--) {
			answer += (read(pos[a[idx]], fwktree) - 1) + " ";
			// DVD entfernen
			fwktree = update(pos[a[idx]], -1, fwktree);
			mn[pos[a[idx]]] = 0;
			// DVD einfuegen
			fwktree = update(i, 1, fwktree);
			pos[a[idx]] = i;
			mn[i] = 1;

			idx++;
			}
			answer = answer.trim();
			System.out.println(answer);

		}
		scan.close();
    }
//START
    public static int read(int index, int[] fenwickTree) {
		int sum = 0;
		while (index > 0) {
			sum += fenwickTree[index];
			index -= (index & -index);
		}
		return sum;
    }
//END

//START
    public static int[] update(int index, int addValue, int[] fenwickTree) {
		while (index <= fenwickTree.length - 1) {
			fenwickTree[index] += addValue;
			index += (index & -index);
		}
		return fenwickTree;
    }
//END
}
