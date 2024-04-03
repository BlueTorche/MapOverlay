import DataClass.EventPoint;
import DataStructures.AVLTree;
import DataStructures.BSTree;

public class AVLTreeTest {
	public static void main(String[] args) {
		BSTree<EventPoint> t = new AVLTree<EventPoint>();

		System.out.println(t.searchMin());
		System.out.println("------------");

		t.insert(new EventPoint(1, 1));
		t.insert(new EventPoint(5, 2));
		t.insert(new EventPoint(2, 5));
		t.insert(new EventPoint(3, 4));
		t.insert(new EventPoint(6, 3));
		t.insert(new EventPoint(2, 1));
		t.insert(new EventPoint(2, 2));
		t.insert(new EventPoint(8, 7));
		t.print();
		System.out.println("-----------");

		System.out.println(t.searchSucc(new EventPoint(1, 1)));
		System.out.println("-----------");

		t.suppress(new EventPoint(1, 1));
		t.print();
		System.out.println("-----------");

		System.out.println(t.searchMax());
		System.out.println("-----------");
	}
}
