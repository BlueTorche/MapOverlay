package DataClass;

import java.util.HashSet;
import java.util.Set;

public class EventPoint implements Comparable<EventPoint> {
    protected Point point;
    protected Set<Segment> segments = new HashSet<>();

    public EventPoint(float x, float y, Segment s) {
        this.point = new Point(x,y);
        this.segments.add(s);
    }

    public EventPoint(Point p, Segment s) {
        this.point = p;
        this.segments.add(s);
    }

    public EventPoint(Point p, Set<Segment> s) {
        this.point = p;
        this.segments.addAll(s);
    }

    public void addSegment(Segment s) { this.segments.add(s); }
    public void addAllSegment(Set<Segment> s) { this.segments.addAll(s); }

    @Override
    public int compareTo(EventPoint q) { return this.point.compareTo(q.point); }

    // Implémentation de la méthode toString() pour afficher la valeur de manière lisible
    @Override
    public String toString() {
        String toReturn = point.toString() + " -> ";
        for (Segment s : segments)
            toReturn += "[" + s.toString() + "] ";
        return toReturn;
    }
}
