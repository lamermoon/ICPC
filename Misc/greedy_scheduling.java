/* Greedy-Scheduling
*/

import java.util.Scanner;
import java.util.TreeSet;
import java.util.PriorityQueue;
import java.util.Arrays;
//START
public class ebox {

    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	int n = s.nextInt();
	int k = s.nextInt();
	Show[] S = new Show[n];
	for(int i = 0; i < n; i++) {
	    Show cur = new Show(s.nextInt(), s.nextInt());
	    S[i] = cur;
	}
	Arrays.sort(S);
	TreeSet<Band> t = new TreeSet<Band>();
	for(int i = 0; i < k; i++) {
	    t.add(new Band(0, i));
	}
	int sum = 0;
	for(int i = 0; i < n; i++) {
	    Band cmp = new Band(S[i].s, Integer.MAX_VALUE);
	    Band rm = t.floor(cmp);
	    if(rm == null) continue;
	    int id = rm.id;
	    t.remove(rm);
	    t.add(new Band(S[i].f, id));
	    sum++;
	}
	System.out.println(sum);
    }
}

class Show implements Comparable<Show> {

    int s;
    int f;

    public Show(int s, int f) {
	this.s = s;
	this.f = f;
    }

    public int compareTo(Show o) {
	if(Integer.valueOf(this.f).compareTo(Integer.valueOf(o.f)) != 0) {
	    return Integer.valueOf(this.f).compareTo(Integer.valueOf(o.f));
	} else {
	    return Integer.valueOf(this.s).compareTo(Integer.valueOf(o.s));
	}
    }
}

class Band implements Comparable<Band> {

    int lt;
    int id;

    public Band(int lt, int id) {
	this.lt = lt;
	this.id = id;
    }

    public int compareTo(Band o) {
	if(Integer.valueOf(this.lt).compareTo(Integer.valueOf(o.lt)) != 0) {
	    return Integer.valueOf(this.lt).compareTo(Integer.valueOf(o.lt));
	} else {
	    return Integer.valueOf(this.id).compareTo(Integer.valueOf(o.id));
	}
    }
}
//END