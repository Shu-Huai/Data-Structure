package shuhuai.datastructure.matrix.crosslistmatrix;

@SuppressWarnings({"unused"})
public class Node<ElemType> {
    protected Triple<ElemType> elem;
    protected Node<ElemType> right;
    protected Node<ElemType> down;

    public Node() {
        elem = null;
        right = null;
        down = null;
    }

    public Node(Triple<ElemType> elem) {
        this.elem = elem;
        right = null;
        down = null;
    }

    public Node(Triple<ElemType> elem, Node<ElemType> right, Node<ElemType> down) {
        this.elem = elem;
        this.right = right;
        this.down = down;
    }
}