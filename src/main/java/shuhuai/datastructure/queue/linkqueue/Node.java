package shuhuai.datastructure.queue.linkqueue;

@SuppressWarnings({"unused"})
public class Node<ElemType> {
    protected ElemType elem;
    protected Node<ElemType> next;

    public Node(ElemType elem, Node<ElemType> next) {
        this.elem = elem;
        this.next = next;
    }
}