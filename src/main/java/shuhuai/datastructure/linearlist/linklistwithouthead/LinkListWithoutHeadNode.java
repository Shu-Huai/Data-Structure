package shuhuai.datastructure.linearlist.linklistwithouthead;

@SuppressWarnings({"unused"})
public class LinkListWithoutHeadNode<ElemType> {
    protected ElemType elem;
    protected LinkListWithoutHeadNode<ElemType> next;

    public LinkListWithoutHeadNode() {
        this.elem = null;
        this.next = null;
    }

    public LinkListWithoutHeadNode(ElemType elem) {
        this.elem = elem;
        this.next = null;
    }

    public LinkListWithoutHeadNode(ElemType elem, LinkListWithoutHeadNode<ElemType> next) {
        this.elem = elem;
        this.next = next;
    }
}