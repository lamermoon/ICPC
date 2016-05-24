/* Solve 2SAT
 * Allocate a graph with $|V|=2\cdot n$ for $x_{1...n}$. Add clauses,
 * for example for $(x_1 \lor x_2) \land (\lnot x_3 \lor x_4)$: \\
 * \texttt{addClause(G,1,2); addClause(G,-3,4); int[] b = solve2Sat(G);} \\
 * returns a satisfying mapping for the $x_i, i>0$, or \texttt{null}.
 ** |E|+|V|
*/
import java.util.*;
class Vertex {
	public int id;
	public Vertex(int i){id = i;}
	public ArrayList<Integer> next = new ArrayList<>();
}
public class TwoSAT {
	public static Integer[] scc(Vertex[] G) {
		Stack<Integer> call = new Stack<>(); //explicit call stack
		
		Stack<Integer> reps = new Stack<>(); //potential representants
		Stack<Integer> open = new Stack<>(); //undecided nodes
		Integer[] order = new Integer[G.length]; //visiting order
		int count = 0;
		
		Integer[] sccs = new Integer[G.length];  //assigned components
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
						//is rep. -> completed component
						if (reps.peek()==v) {
							int tmp = 0;
							do {
								tmp = open.pop();
								sccs[tmp] = sccnum;
							} while (tmp != v);
							sccnum++;
							reps.pop();
						}
						
						call.pop(); //node completely done
					}
				}	
			}
		}
		return sccs;
	}

//START
public static void addClause(Vertex[] G, int a, int b) {
	int nega = a<0 ? 0 : 1; int negb = b<0 ? 0 : 1;
	a = Math.abs(a)-1; b = Math.abs(b)-1;
	int Xa = (a<<1)+nega; int Xb = (b<<1)+negb;
	G[Xa^1].next.add(Xb);
	G[Xb^1].next.add(Xa);
}
public static int[] solve2Sat(Vertex[] G) {
	Integer[] color = scc(G);
	for (int i=0; i<G.length; i+=2)
		if (color[i] == color[i+1])
			return null; //contradiction!

	HashSet<Integer>[] sccV = new HashSet[G.length];
	HashSet<Integer>[] sccEn = new HashSet[G.length];
	HashSet<Integer>[] sccEp = new HashSet[G.length];
	Integer[] vals = new Integer[G.length];
	for (int i=0; i<G.length; i++) {
		sccV[i] = new HashSet<Integer>();
		sccEn[i] = new HashSet<Integer>();
		sccEp[i] = new HashSet<Integer>();
	}
	//create reverse SCC DAG
	for (int i=0; i<G.length; i++)
		if (G[i]!=null) {
			sccV[color[i]].add(i);
			for (int j : G[i].next)
				if (color[i] != color[j]) {
					sccEn[color[i]].add(color[j]);
					sccEp[color[j]].add(color[i]);
				}
		}
	//go in rev topo order and set vars
	Stack<Integer> tail = new Stack<Integer>();
	for (int i=0; i<G.length; i++)
		if (!sccV[i].isEmpty() && sccEn[i].isEmpty())
			tail.push(i);
	while (!tail.isEmpty()) {
		int curr = tail.pop();
		for (int i : sccV[curr]) {
			if (vals[i]!=null)
				break;
			vals[i] = 1;
			vals[i^1] = 0;
		}
		for (int i : sccEp[curr]) {
			sccEn[i].remove(curr);
			if (sccEn[i].isEmpty())
				tail.push(i);
		}
	}

	int[] ret = new int[G.length/2+1];
	for (int i=0; i<G.length; i+=2)
		if (vals[i+1]==1)
			ret[i/2+1] = 1;
	return ret;
}
//END
public static void main(String args[]) {
	Vertex[] G = new Vertex[8];

	for (int i=0; i<G.length; i++) G[i] = new Vertex(i);
	addClause(G, 1, 2);
	addClause(G, 2, 3);
	addClause(G, 3, 4);
	addClause(G, -1, -3);
	addClause(G, -2, -4);
	int[] ret = solve2Sat(G);
	for(int i=1; i<ret.length; i++)
		System.out.println(i+": "+ret[i]);
	//true (0 1 1 0)
		
	for (int i=0; i<G.length; i++) G[i] = new Vertex(i);
	addClause(G, 1, 2);
	addClause(G, 2, 4);
	addClause(G, -1, 2);
	addClause(G, -2, 1);
	addClause(G, -1, -2);
	System.out.println(solve2Sat(G)!=null); //false

	for (int i=0; i<G.length; i++) G[i] = new Vertex(i);
	addClause(G, 1, 2);
	addClause(G, 2, 4);
	addClause(G, -1, 2);
	addClause(G, 2, -1);
	addClause(G, -1, -2);
	ret = solve2Sat(G);
	for(int i=1; i<ret.length; i++)
		System.out.println(i+": "+ret[i]);
	//true (0 1)
}
}
