package shuhuai.datastructure.linearlist.linklist;

public class Node<ElemType> {
    protected ElemType elem;
    protected Node<ElemType> next;

    public Node() {
        this.elem = null;
        this.next = null;
    }

    public Node(ElemType elem) {
        this.elem = elem;
        this.next = null;
    }

    public Node(ElemType elem, Node<ElemType> next) {
        this.elem = elem;
        this.next = next;
    }
}