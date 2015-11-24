/* BellmanFord
 ** |V|\cdot |E|
 * Finds shortest pathes from a single source. Negative edge weights are allowed. Can be used for finding negative cycles.
 */

//START
public static boolean bellmanFord(Vertex[] vertices) {
   //source is 0
   vertices[0].mindistance = 0;
   //calc distances
   for(int i = 0; i < vertices.length-1; i++) {
      for(int j = 0; j < vertices.length; j++) {
         for(Edge e: vertices[j].adjacencies) {
            if(vertices[j].mindistance != Integer.MAX_VALUE 
               && e.target.mindistance > vertices[j].mindistance + e.distance) {
               e.target.mindistance = vertices[j].mindistance + e.distance;
            }
         }
      }
   }
   //check for negative-length cycle
   for(int i = 0; i < vertices.length; i++) {
      for(Edge e: vertices[i].adjacencies) {
         if(vertices[i].mindistance != Integer.MAX_VALUE && e.target.mindistance > vertices[i].mindistance + e.distance) {
            return true;
         }
      }
   }
   return false;
}
//END
