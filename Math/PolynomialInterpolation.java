/* Polynomial Interpolation
 */
 
//START
public class interpol {
	
	// divided differences for points given by vectors x and y
	public static rat[] divDiff(rat[] x, rat[] y) {
		rat[] temp = y.clone();
		int n = x.length;
		rat[] res = new rat[n];
		res[0] = temp[0];
		for (int i=1; i < n; i++) {
			for (int j = 0; j < n-i; j++) {
				temp[j] = (temp[j+1].sub(temp[j])).div(x[j+i].sub(x[j]));
			}
			res[i] = temp[0];
		}
		return res;
	}
	
	// evaluates interpolating polynomial p at t for given 
	// x-coordinates and divided differences
	public static rat p(rat t, rat[] x, rat[] dD) {
		int n = x.length;
		rat p = new rat(0);
		for (int i = n-1; i > 0; i--) {
			p = (p.add(dD[i])).mult(t.sub(x[i-1]));
		}
		p = p.add(dD[0]);
		return p;
	}
	
	public static void main(String[] args) {
	
		rat[] test = {new rat(4,5), new rat(7,10), new rat(3,4)};
		test = rat.commonDenominator(test);
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i].toString());
		}
	
		rat[] x = {new rat(0),new rat(1), new rat(2), new rat(3), new rat(4), new rat(5)};
		rat[] y = {new rat(-10), new rat(9), new rat(0), new rat(1), new rat(1,2), new rat(1,80)};
		rat[] dD = divDiff(x,y);
		System.out.println("p("+7+") = "+p(new rat(7), x, dD));
	}

}
//END

//START
// implementation of rational numbers
class rat {

	public long c;
	public long d;
	
	public rat (long c, long d) {
		this.c = c;
		this.d = d;
		this.shorten();
	}
	
	public rat (long c) {
		this.c = c;
		this.d = 1;
	}
	
	public static long ggT(long a, long b) {
		while (b != 0) {
			long h = a%b;
			a = b;
			b = h;
		}
		return a;
	}
	
	public static long kgV(long a, long b) {
		return a*b/ggT(a,b);
	}
	
	public static rat[] commonDenominator(rat[] c) {
		long kgV = 1;
		for (int i = 0; i < c.length; i++) {
			kgV = kgV(kgV, c[i].d);
		}
		for (int i = 0; i < c.length; i++) {
			c[i].c *= kgV/c[i].d;
			c[i].d *= kgV/c[i].d;
		}
		return c;
	}
	
	public void shorten() {
		long ggT = ggT(this.c, this.d);
		this.c = this.c / ggT;
		this.d = this.d / ggT;
		if (d < 0) {
			this.d *= -1;
			this.c *= -1;
		}
	}
	
	public String toString() {
		if (this.d == 1) return ""+c;
		return ""+c+"/"+d;
	}
	
	public rat mult(rat b) {
		return new rat(this.c*b.c, this.d*b.d);
	}
	
	public rat div(rat b) {
		return new rat(this.c*b.d, this.d*b.c);
	}
	
	public rat add(rat b) {
		long new_d = kgV(this.d, b.d);
		long new_c = this.c*(new_d/this.d) + b.c*(new_d/b.d);
		return new rat(new_c, new_d);
	}
	
	public rat sub(rat b) {
		return this.add(new rat(-b.c, b.d));
	}
	
}
//END
