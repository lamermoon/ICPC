/* Graham Scan 
 ** ???
 * GrahamScan finds convex hull. Still has collinear point problematic at the last diagonal.
 */

//START
public static int ccw(Point src, Point q1, Point q2) {
   return (q1.x - src.x) * (q2.y - src.y) - (q2.x - src.x) * (q1.y - src.y);
}

public static boolean isColl(Point a, Point b, Point c) {
   if((b.y - a.y) * (c.x - b.x) == (c.y - b.y) * (b.x - a.x)) {
      return true;
   } else {
      return false;
   }
}

public static double calcDist(Point src, Point target) {
   return Math.sqrt((src.x + target.x) * (src.x + target.x) + (src.y + target.y) * (src.y * target.y));
}

//Expects a array sorted with PolarComp as Comparator
//IMPORTANT! before sorting put lowest, and if two are the same leftmost, element at position 0 in array
public static void grahamScan(Point[] points) {
   int m = 1;
   for(int i = 2; i < points.length; i++) {
      while(ccw(points[m-1], points[m], points[i]) < 0) {
         if(m > 1) m--;
         else if(i == points.length) break;
         else i++;
      }
      m++;
      Point tmp = points[i];
      points[i] = points[m];
      points[m] = tmp;
   }
}

class Point {
   int x;
   int y;
   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }
}

class PolarComp implements Comparator<Point> {
   Point src;

   public PolarComp(Point source) {
      src = source;
   }

   public double calcDist(Point q1, Point q2) {
      return Math.sqrt((q1.x - q2.x) * (q1.x - q2.x) + (q1.y - q2.y) * (q1.y - q2.y));
   }

   public int ccw(Point q1, Point q2) {
      return (q1.x - src.x) * (q2.y - src.y) - (q2.x - src.x) * (q1.y - src.y);
   }

   public int compare(Point q1, Point q2) {
      int res = ccw(q1, q2);
      double dist1 = calcDist(src, q1);
      double dist2 = calcDist(src, q2);
      if(res > 0) return -1;
      else if(res < 0) return 1;
      else if(res == 0 && dist1 < dist2) return 1;
      else if(res == 0 && dist1 > dist2) return -1;
      else  return 0;
   }
}
//END
