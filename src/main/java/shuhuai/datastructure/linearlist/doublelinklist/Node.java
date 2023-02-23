package shuhuai.datastructure.linearlist.doublelinklist;

@SuppressWarnings({"unused"})
public class Node<ElemType> {
    protected ElemType elem;
    protected Node<ElemType> prev;
    protected Node<ElemType> next;

    public Node() {
        this.elem = null;
        this.prev = null;
        this.next = null;
    }

    public Node(ElemType elem) {
        this.elem = elem;
        this.prev = null;
        this.next = null;
    }

    public Node(ElemType elem, Node<ElemType> prev, Node<ElemType> next) {
        this.elem = elem;
        this.prev = prev;
        this.next = next;
    }
}
