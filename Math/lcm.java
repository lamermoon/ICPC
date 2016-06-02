/* Least Common Multiple
 ** \log a + \log b
 * Calculates the lcm of two numbers $a$ and $b$ or of an array of numbers $input$.\\
 * \emph{Input:} Numbers $a$ and $b$ or array of numbers $input$\\
 * \emph{Output:} Least common multiple of the input
 */
//START
private static long lcm(long a, long b) {
    return a * (b / gcd(a, b));
}

private static long lcm(long[] input) {
    long result = input[0];
    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
    return result;
}
//END
