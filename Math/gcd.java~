/* Greatest Common Devisor
 ** \log a + \log b
 * Calculates the gcd of two numbers $a$ and $b$ or of an array of numbers $input$.\\
 * \emph{Input:} Numbers $a$ and $b$ or array of numbers $input$\\
 * \emph{Output:} Greatest common devisor of the input
 */
//START
private static long gcd(long a, long b) {
    while (b > 0) {
        long temp = b;
        b = a % b; // % is remainder
        a = temp;
    }
    return a;
}

private static long gcd(long[] input) {
    long result = input[0];
    for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
    return result;
}
//END
