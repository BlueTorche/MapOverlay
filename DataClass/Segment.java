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

    @Override
    public int compareTo(Segment s) {
        return 1; //TODO make the comparaison
    }

    // Implémentation de la méthode toString() pour afficher la valeur de manière lisible
    @Override
    public String toString() {
        return "(" + startPoint.toString() + ");(" + endPoint.toString() + ")";
    }
}
