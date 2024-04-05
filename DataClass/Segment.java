package DataClass;

import Main.Main;

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

    public float getIntersectionWithSweepline(float y) {
        float[] Direction1 = {endPoint.x-startPoint.x, endPoint.y-startPoint.y};

        float t = (y - startPoint.y)/Direction1[1];

        return startPoint.x + t * Direction1[0];
    }

    @Override
    public int compareTo(Segment s) {
        float x1 = this.getIntersectionWithSweepline(Main.ySweepLine);
        float x2 =     s.getIntersectionWithSweepline(Main.ySweepLine);

        // Check who intersect the SweepLine first
        if (x1 < x2)
            return -1;
        else if (x1 > x2)
            return 1;

        // Check if there is horizontal segment (or if segment ends on the SweepLine (shouldn't happen)
        else if (endPoint.y == Main.ySweepLine && s.getEndPoint().y == Main.ySweepLine)
            return 0;
        else if (endPoint.y == Main.ySweepLine)
            return 1;
        else if (s.getEndPoint().y == Main.ySweepLine)
            return -1;

        // Check who has the higher (x-x_sweepline)/(y-y_sweepline)
        else if ((endPoint.x - x1)/(endPoint.y - Main.ySweepLine) < (s.getEndPoint().x - x2)/(s.getEndPoint().y - Main.ySweepLine))
            return -1;
        else if ((endPoint.x - x1)/(endPoint.y - Main.ySweepLine) > (s.getEndPoint().x - x2)/(s.getEndPoint().y - Main.ySweepLine))
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "(" + startPoint.toString() + ");(" + endPoint.toString() + ")";
    }
}
