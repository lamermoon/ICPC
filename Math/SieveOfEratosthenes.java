/* Sieve of Eratosthenes
 ** n
 * Calculates Sieve of Eratosthenes. \\
 * Input: A integer $N$ indicating the size of the sieve. \\
 * Output: A boolean array, which is true at an index $i$ iff i is prime.
 */

class Main {
	public static void main(String[] args) {
		boolean[] primes = sieveOfEratosthenes(100);
		for (int i=0; i<primes.length; i++)
			if (primes[i])
				System.out.println(i);
	}

//START
public static boolean[] sieveOfEratosthenes(int N) {
	boolean[] isPrime = new boolean[N+1];
    for (int i=2; i<=N; i++) isPrime[i] = true;
    for (int i = 2; i*i <= N; i++)
        if (isPrime[i])
            for (int j = i*i; j <= N; j+=i)
                isPrime[j] = false;
    return isPrime;
}
//END
}
