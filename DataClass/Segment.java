package DataClass;

public class Segment implements Comparable<Segment> {
    protected EventPoint startPoint;
    protected EventPoint endPoint;

    public Segment(float x1, float y1, float x2, float y2) {
        EventPoint p = new EventPoint(x1, y1);
        EventPoint q = new EventPoint(x2, y2);
        if (p.compareTo(q) < 0) {
            this.startPoint = p;
            this.endPoint   = q;
        }
        else {
            this.startPoint = q;
            this.endPoint   = p;
        }
    }

    public EventPoint getStartPoint() {return this.startPoint;}
    public EventPoint getEndPoint() {return this.endPoint;}

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
