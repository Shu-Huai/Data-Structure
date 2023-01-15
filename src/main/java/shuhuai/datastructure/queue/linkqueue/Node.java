package shuhuai.datastructure.queue.linkqueue;

public class Node<ElemType> {
    protected ElemType elem;
    protected Node<ElemType> next;

    public Node(ElemType elem, Node<ElemType> next) {
        this.elem = elem;
        this.next = next;
    }
}