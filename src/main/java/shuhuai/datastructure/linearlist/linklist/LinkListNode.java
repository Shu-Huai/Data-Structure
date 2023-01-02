package shuhuai.datastructure.linearlist.linklist;

public class LinkListNode<ElemType> {
    protected ElemType elem;
    protected LinkListNode<ElemType> next;

    public LinkListNode() {
        this.elem = null;
        this.next = null;
    }

    public LinkListNode(ElemType elem) {
        this.elem = elem;
        this.next = null;
    }

    public LinkListNode(ElemType elem, LinkListNode<ElemType> next) {
        this.elem = elem;
        this.next = next;
    }
}