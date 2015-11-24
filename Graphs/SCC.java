/* Path-Based SCCs
 * Finds the strongly connected components in given directed graph.
 ** |E|+|V|
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Vertex {
	public int id;
	public Vertex(int i){id = i;}
	public ArrayList<Integer> next = new ArrayList<>();
}

public class SCC {
//START
public static Integer[] scc(Vertex[] G) {
	Stack<Integer> call = new Stack<>();
	
	Stack<Integer> reps = new Stack<>();
	Stack<Integer> open = new Stack<>();
	Integer[] order = new Integer[G.length];
	int count = 0;
	
	Integer[] sccs = new Integer[G.length];
	int sccnum = 0;
	
	for (int i=0; i<G.length; i++) {
		if (G[i]==null) //no such vertex
			continue;
		
		if (sccs[i]==null) {
			call.push(i);
			while (!call.isEmpty()) {
				int v = call.peek();
				if (order[v]==null) { //first entered
					order[v] = count++;
					reps.push(v);
					open.push(v);
					
					for (int w : G[v].next) { //process edges
						if (order[w]==null) {
							call.push(w);
						} else if (sccs[w]==null) {
							while (order[reps.peek()]>order[w])
								reps.pop();
						}
					}
					
				} else { //returned from recursion
					//is still rep. -> completed SCC
					if (reps.peek()==v) {
						int tmp = 0;
						do {
							tmp = open.pop();
							sccs[tmp] = sccnum;
						} while (tmp != v);
						sccnum++;
						reps.pop();
					}
					
					call.pop(); //node done
				}
			}	
		}
	}
	return sccs;
}
//END
	public static void main(String[] args) {
		Vertex[] vs = new Vertex[11];
		for (int i=1; i<vs.length; i++)
			vs[i] = new Vertex(i);
		
		vs[10].next.add(1);
		vs[1].next.add(2);
		vs[2].next.add(1);
		vs[2].next.add(3);
		vs[3].next.add(1);
		vs[2].next.add(4);
		vs[4].next.add(5);
		vs[5].next.add(4);
		vs[5].next.add(6);
		//vs[6].next.add(2);
		vs[6].next.add(7);
		vs[8].next.add(9);
		vs[9].next.add(8);
		vs[8].next.add(1);
		
		Integer[] sccs = scc(vs);
		for (int i=0; i<vs.length; i++)
			System.out.println(i+": "+sccs[i]);
		
	}
}
