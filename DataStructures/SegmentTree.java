package DataStructures;

import DataClass.EventPoint;
import DataClass.Segment;

public class SegmentTree<D extends Segment> extends AVLTree<D> {
    public SegmentTree() {
        super();
    }

    public void insertEmpty(SegmentTree<D> t) {
        this.setData(t.getData());
        this.setLeft(t.getLeft());
        this.setRight(t.getRight());
    }

    public void insertTree(SegmentTree<D> t) {
        if (isEmpty())
            insertEmpty(t);
        else {
            if (getData().compareTo(t.getData()) < 0)
                getRight().insertTree(t);
            else if (getData().compareTo(t.getData()) > 0)
                getLeft().insertTree(t);
            equilibrate();
        }
    }

    public SegmentTree<D> getLeft() {
        return (SegmentTree<D>) super.getLeft();
    }
    public SegmentTree<D> getRight() {
        return (SegmentTree<D>) super.getRight();
    }

    @Override
    public void equilibrate() {
        while (balance() < -1 && balance() > 1) {
            if (balance() >= 2)
                if (getRight().balance() >= 0)
                    leftRotation();
                else {
                    getRight().rightRotation();
                    leftRotation();
                }
            else if (balance() <= -2)
                if (getLeft().balance() <= 0)
                    rightRotation();
                else {
                    getLeft().leftRotation();
                    rightRotation();
                }
            else computeHeight();
        }
    }
}