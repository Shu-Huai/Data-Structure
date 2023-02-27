package shuhuai.datastructure.unionfindsets;

@SuppressWarnings({"unused"})
public class Node<ElemType> {
    protected ElemType elem;
    protected int parent;

    public Node() {
        elem = null;
        parent = -1;
    }

    public Node(ElemType elem) {
        this.elem = elem;
        parent = -1;
    }

    public Node(Node<ElemType> node) {
        if (node == null) {
            return;
        }
        elem = node.elem;
        parent = node.parent;
    }

    public Node(ElemType elem, int parent) {
        this.elem = elem;
        this.parent = parent;
    }

    public ElemType getElem() {
        return elem;
    }

    public void setElem(ElemType data) {
        this.elem = data;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void copy(Node<ElemType> node) {
        elem = node.elem;
        parent = node.parent;
    }
}