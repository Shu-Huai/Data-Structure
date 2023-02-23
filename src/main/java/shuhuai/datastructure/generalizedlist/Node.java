package shuhuai.datastructure.generalizedlist;

@SuppressWarnings({"unused"})
public class Node<ElemType> {
    protected NodeType tag;
    protected Node<ElemType> next;
    protected int refCount;
    protected ElemType elem;
    protected Node<ElemType> child;

    public Node(NodeType tag) {
        this.tag = tag;
        this.next = null;
    }

    public Node(NodeType tag, Node<ElemType> next) {
        this.tag = tag;
        this.next = next;
    }
}
