package shuhuai.datastructure.stack.linkstack;


public class LinkStackNode<ElemType> {
    protected ElemType elem;
    protected LinkStackNode<ElemType> next;

    public LinkStackNode(ElemType elem,LinkStackNode<ElemType> next) {
        this.elem = elem;
        this.next = next;
    }
}