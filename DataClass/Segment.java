package DataClass;

public class Segment implements Comparable<Segment> {
    protected int x1,y1,x2,y2;
    protected int ySweepline;

    public Segment(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public int compareTo(Segment s) {
        return 1;
    }

    // Implémentation de la méthode toString() pour afficher la valeur de manière lisible
    @Override
    public String toString() {
        return Integer.toString(this.x1) + " " + Integer.toString(this.y1) + ";" +
                Integer.toString(this.x2) + " " + Integer.toString(this.y2);
    }
}
