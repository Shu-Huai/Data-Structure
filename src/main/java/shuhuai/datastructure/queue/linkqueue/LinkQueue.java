package shuhuai.datastructure.queue.linkqueue;

import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.queue.Queue;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public class LinkQueue<ElemType> implements Queue<ElemType> {
    protected Node<ElemType> front;
    protected Node<ElemType> rear;

    public LinkQueue() {
        front = new Node<>(null, null);
        rear = front;
    }

    public LinkQueue(LinkQueue<ElemType> linkQueue) {
        front = new Node<>(null, null);
        Node<ElemType> q = front;
        for (Node<ElemType> p = linkQueue.front.next; p != null; p = p.next) {
            q.next = new Node<>(p.elem, null);
            q = q.next;
        }
        rear = q;
    }

    @Override
    public int getLength() {
        int count = 0;
        for (Node<ElemType> p = front.next; p != null; p = p.next) {
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
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    @Override
    public void traverse(Function<String, Void> output) {
        int count = 0;
        for (Node<ElemType> p = front.next; p != null; p = p.next) {
            output.apply(p.elem + " ");
            count++;
        }
    }

    @Override
    public ElemType deQueue() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("队空");
        }
        Node<ElemType> p = front.next;
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
        rear.next = new Node<>(elem, null);
        rear = rear.next;
    }
}