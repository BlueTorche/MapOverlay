package Main;

import DataClass.EventPoint;
import DataClass.Point;
import DataClass.Segment;
import DataStructures.AVLTree;
import DataStructures.BSTree;

public class AVLTreeTest {
	public static void main2(String[] args) {
		BSTree<Point> t = new AVLTree<>();

		System.out.println(t.searchMin());
		System.out.println("------------");

		t.insert(new Point(1, 1));
		t.insert(new Point(5, 2));
		t.insert(new Point(2, 5));
		t.insert(new Point(3, 4));
		t.insert(new Point(6, 3));
		t.insert(new Point(2, 1));
		t.insert(new Point(2, 2));
		t.insert(new Point(8, 7));
		t.print();
		System.out.println("-----------");

		System.out.println(t.searchSucc(new Point(1, 1)));
		System.out.println("-----------");

		t.suppress(new Point(1, 1));
		t.print();
		System.out.println("-----------");

		System.out.println(t.searchMax());
		System.out.println("-----------");
	}


	public static void main(String[] args) {
		Segment s1 = new Segment(1, 7, 9, 3);
		Segment s2 = new Segment(4, 1, 6, 9);
		System.out.println(s1.getIntersectionWith(s2));
	}
}
