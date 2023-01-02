package shuhuai.datastructure.linearlist.doublelinklist;

public class DoubleLinkListNode<ElemType> {
    protected ElemType elem;
    protected DoubleLinkListNode<ElemType> prev;
    protected DoubleLinkListNode<ElemType> next;

    public DoubleLinkListNode() {
        this.elem = null;
        this.prev = null;
        this.next = null;
    }

    public DoubleLinkListNode(ElemType elem) {
        this.elem = elem;
        this.prev = null;
        this.next = null;
    }

    public DoubleLinkListNode(ElemType elem, DoubleLinkListNode<ElemType> prev, DoubleLinkListNode<ElemType> next) {
        this.elem = elem;
        this.prev = prev;
        this.next = next;
    }
}
