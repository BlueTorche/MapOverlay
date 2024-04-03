import DataClass.EventPoint;
import DataClass.Segment;
import DataStructures.AVLTree;
import DataStructures.BSTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Main {
    static BSTree<EventPoint>  Q = new AVLTree<>();
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

        Q.print();

        while (!Q.isEmpty()) {
            EventPoint p = Q.suppressMin();
            HandleEventPoint(p);
        }
    }

    public static void HandleEventPoint(EventPoint p){

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
