package shuhuai.datastructure.queue.linkqueue;

import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.queue.Queue;

public class LinkQueue<ElemType extends Comparable<? super ElemType>> implements Queue<ElemType> {
    protected LinkQueueNode<ElemType> front;
    protected LinkQueueNode<ElemType> rear;

    public LinkQueue() {
        front = new LinkQueueNode<>(null, null);
        rear = front;
    }

    @Override
    public int getLength() {
        int count = 0;
        for (LinkQueueNode<ElemType> p = front.next; p != null; p = p.next) {
            count++;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return front.next == null;
    }

    @Override
    public void clear() {
        front.next = null;
        rear = front;
    }

    @Override
    public void traverse() {
        int count = 0;
        for (LinkQueueNode<ElemType> p = front.next; p != null; p = p.next) {
            System.out.print(p.elem + " ");
            count++;
        }
        System.out.println();
        System.out.println("长度是：" + count);
    }

    @Override
    public ElemType deQueue() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("队空");
        }
        LinkQueueNode<ElemType> p = front.next;
        ElemType result = p.elem;
        front.next = p.next;
        if (rear == p) {
            rear = front;
        }
        return result;
    }

    @Override
    public ElemType getHead() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("队空");
        }
        return front.next.elem;
    }

    @Override
    public void enQueue(ElemType elem) {
        rear.next = new LinkQueueNode<>(elem, null);
        rear = rear.next;
    }

    public LinkQueue(LinkQueue<ElemType> linkQueue) {
        front = new LinkQueueNode<>(null, null);
        LinkQueueNode<ElemType> q = front;
        for (LinkQueueNode<ElemType> p = linkQueue.front.next; p != null; p = p.next) {
            q.next = new LinkQueueNode<>(p.elem, null);
            q = q.next;
        }
        rear = q;
    }
}