/* Geometric sum modulo 
 * calculates geometric series with parameters a, n modulo mod
 */
//START
long long powmod(long long base, long long exp, long long mod) {
    base %= mod;
    long long res = 1;
    while (exp > 0) {
	if (exp & 1) res = (res * base) % mod;
	base = (base * base) % mod;
	exp >>= 1;
    }
    return res;
}

long long geomod(long long a, long long n, long long mod) {
    long long factor = 1, sum = 0;
    while(n > 0 && a != 0) {
	if(n % 2 == 0) {
	    long long tmp = (factor * powmod(a, n, mod)) % mod;
	    sum = (sum + tmp) % mod;
	    --n;
	}
	factor = (((1 + a) % mod) * factor) % mod;
	a = a*a % mod;
	n = n / 2;
    }
    return sum + factor % mod;
}
//END