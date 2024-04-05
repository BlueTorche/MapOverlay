package DataClass;

public class Point implements Comparable<Point> {
    protected float x,y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY() { return this.y; }

    @Override
    public int compareTo(Point q) {
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
        return this.x + " " + this.y;
    }
}
