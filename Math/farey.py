/* prints farey seq
*/
//START
def farey( n, asc=True ):
    """Python function to print the nth Farey sequence, either ascending or descending."""
    if asc: 
        a, b, c, d = 0, 1,  1 , n     # (*)
    else:
        a, b, c, d = 1, 1, n-1, n     # (*)
    print "%d/%d" % (a,b)
    while (asc and c <= n) or (not asc and a > 0):
        k = int((n + b)/d)
        a, b, c, d = c, d, k*c - a, k*d - b
        print "%d/%d" % (a,b)
//END
