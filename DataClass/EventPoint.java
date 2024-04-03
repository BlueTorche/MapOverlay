package DataClass;

public class EventPoint implements Comparable<EventPoint> {
    protected int x,y;

    public EventPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(EventPoint q) {
        if (this.y == q.y && this.x == q.x)
            return 0;
        else if (this.y > q.y || (this.y == q.y && this.x < q.x))
            return -1;
        else
            return 1;
    }

    // Implémentation de la méthode toString() pour afficher la valeur de manière lisible
    @Override
    public String toString() {
        return Integer.toString(this.x) + " " + Integer.toString(this.y);
    }
}
