package shuhuai.datastructure.queue.linkqueue;

public class LinkQueueNode<ElemType> {
    protected ElemType elem;
    protected LinkQueueNode<ElemType> next;

    public LinkQueueNode(ElemType elem, LinkQueueNode<ElemType> next) {
        this.elem = elem;
        this.next = next;
    }
}