package shuhuai.datastructure.generalizedlist;

@SuppressWarnings({"unused"})
public class Node<ElemType> {
    protected Tag tag;
    protected Node<ElemType> next;
    protected int refCount;
    protected ElemType elem;
    protected Node<ElemType> child;

    public Node(Tag tag) {
        this.tag = tag;
        this.next = null;
    }

    public Node(Tag tag, Node<ElemType> next) {
        this.tag = tag;
        this.next = next;
    }
}
