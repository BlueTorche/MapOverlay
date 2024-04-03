package DataClass;

public class Segment implements Comparable<Segment> {
    protected Point startPoint;
    protected Point endPoint;

    public Segment(float x1, float y1, float x2, float y2) {
        Point p = new Point(x1, y1);
        Point q = new Point(x2, y2);
        if (p.compareTo(q) < 0) {
            this.startPoint = p;
            this.endPoint   = q;
        }
        else {
            this.startPoint = q;
            this.endPoint   = p;
        }
    }

    public Point getStartPoint() {return this.startPoint;}
    public Point getEndPoint() {return this.endPoint;}

    public Point getIntersectionWith(Segment s) {
        // Compute direction of both Segment
        float[] Direction1 = {endPoint.x-startPoint.x, endPoint.y-startPoint.y};
        float[] Direction2 = {s.endPoint.x-s.startPoint.x, s.endPoint.y-s.startPoint.y};

        // Compute the solution of the intersection equation describe as below :
        //      x11 + v11 * t = x21 + v21 * s
        //      y11 + v12 * t = y21 + v22 * s
        float denominator = Direction1[0]*Direction2[1]-Direction1[1]*Direction2[0];

        // If denominator is null, segments are parallel
        if (denominator == 0)
            return null;

        // Compute t and s
        float tVal = Direction2[1]*(s.startPoint.x - startPoint.x) - Direction2[0] * (s.startPoint.y - startPoint.y);
        tVal /= denominator;
        float sVal = Direction1[1]*(s.startPoint.x - startPoint.x) - Direction1[0] * (s.startPoint.y - startPoint.y);
        sVal /= denominator;

        // if tVal and sVal are not included in [0;1], the segments doesn't intersect
        if (tVal < 0 || tVal > 1 || sVal < 0 || sVal > 1)
            return null;

        // Compute the intersection
        float intersection_x = startPoint.x + tVal*Direction1[0];
        float intersection_y = startPoint.y + tVal*Direction1[1];
        return new Point(intersection_x, intersection_y);
    }

    @Override
    public int compareTo(Segment s) {
        if (startPoint.compareTo(s.startPoint) == 0 && endPoint.compareTo(s.endPoint) == 0)
            return 0;
        if (startPoint.compareTo(s.startPoint) > 0)
            return -1;
        return 1; //TODO make the good comparaison
    }

    // Implémentation de la méthode toString() pour afficher la valeur de manière lisible
    @Override
    public String toString() {
        return "(" + startPoint.toString() + ");(" + endPoint.toString() + ")";
    }
}
