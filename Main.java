import DataClass.EventPoint;
import DataClass.Segment;
import DataClass.Point;
import DataStructures.AVLTree;
import DataStructures.BSTree;
import DataStructures.EventPointTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Main {
    static BSTree<EventPoint>  Q = new EventPointTree<>();
    static BSTree<Segment>     T = new AVLTree<>();

    static Set<EventPoint> intersections = new HashSet<>();

    public static void main(String[] args) {
        String filename = "cartes/fichier1.txt";
        Set<Segment> segments = LoadSegments(filename);

        FindIntersection(segments);
    }

    public static void FindIntersection(Set<Segment> segments) {
        for (Segment s : segments) {
            Q.insert(new EventPoint(s.getStartPoint(), s));
            Q.insert(new EventPoint(s.getEndPoint()  , s));
        }

        while (!Q.isEmpty()) {
            EventPoint p = Q.suppressMin();
            HandleEventPoint(p);
        }
    }

    public static void HandleEventPoint(EventPoint p){
        if (p.getSegments().size() > 1)
            intersections.add(p);

        Set<Segment> U = new HashSet<>(); // Segments whose upper point is p
        Set<Segment> L = new HashSet<>(); // Segments whose lower point is p
        Set<Segment> C = new HashSet<>(); // Segments that contain p in their interior

        for (Segment s : p.getSegments()) {
            if (s.getStartPoint().compareTo(p.getPoint()) == 0)
                U.add(s); // StartPoint of s is EventPoint p
            else if (s.getEndPoint().compareTo(p.getPoint()) == 0)
                L.add(s); // EndPoint of s is EventPoint p
            else
                C.add(s);
        }

        // delete all Segments from L(p) u C(p) from T // TODO make intelligent suppression with an suppressAll
        for (Segment s : L)
            T.suppress(s);
        for (Segment s : C)
            T.suppress(s);

        // insert all Segments from U(p) u C(p) from T // TODO make intelligent addition with an addAll
        for (Segment s : U)
            T.insert(s);
        for (Segment s : C)
            T.insert(s);

        // TODO generate sL and sR the left and right segments neighbors of p (can be done when suppressAll and insertAll)
        Segment sL = null;
        Segment sR = null;

        if (U.size() + C.size() > 0) {
            // TODO generate s' and s'' the leftmost and rightmost segments of U(p) u C(p)
            Segment leftmostSegment = null;
            Segment rightmostSegment = null;
            if (sL != null)
                FindNewEvent(sL, leftmostSegment, p);
            if (sR != null)
                FindNewEvent(sR, rightmostSegment, p);
        }
        else {
            if (sL != null && sR != null)
                FindNewEvent(sL, sR, p);
        }
    }

    public static void FindNewEvent(Segment s1, Segment s2, EventPoint p) {
        // Generating the intersection between s1 and s2
        Point intersection = s1.getIntersectionWith(s2);

        if (intersection != null && p.getPoint().compareTo(intersection) < 0) {
            // Creating new EventPoint containing intersection and both segments
            EventPoint q = new EventPoint(intersection, s1);
            q.addSegment(s2);
            // Add intersection EventPoint to Q
            Q.insert(q);
        }
    }

    public static Set<Segment> LoadSegments(String filename) {
        Set<Segment> segments = new HashSet<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(" ");
                segments.add(new Segment(Float.parseFloat(values[0]),
                                         Float.parseFloat(values[1]),
                                         Float.parseFloat(values[2]),
                                         Float.parseFloat(values[3])));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier n'a pas été trouvé : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erreur de conversion en float : " + e.getMessage());
        }
        return segments;
    }
}
