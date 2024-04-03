package DataStructures;

import DataClass.EventPoint;

public class EventPointTree<D extends EventPoint> extends AVLTree<D> {
    public EventPointTree() {
        super();
    }

    @Override
    public void insert(D d) {
        if (isEmpty())
            insertEmpty(d);
        else {
            if (getData().compareTo(d) < 0)
                getRight().insert(d);
            else if (getData().compareTo(d) > 0)
                getLeft().insert(d);
            else if (getData().compareTo(d) == 0) {
                getData().addAllSegment(d.getSegments());
            }
            equilibrate();
        }
    }
}
