package shuhuai.datastructure.queue.sequencequeue;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.queue.Queue;

public class SequenceQueue<ElemType extends Comparable<? super ElemType>> implements Queue<ElemType> {
    protected int front;
    protected int rear;
    protected int capacity;
    protected ElemType[] elems;

    @SuppressWarnings("unchecked")
    public SequenceQueue(int capacity) {
        elems = (ElemType[]) new Comparable[capacity];
        front = 0;
        rear = 0;
        this.capacity = capacity;
    }

    @Override
    public int getLength() {
        return (rear - front + capacity) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void clear() {
        front = 0;
        rear = 0;
    }

    @Override
    public void traverse() {
        for (int i = front; i != rear; i = (i + 1) % capacity) {
            System.out.print(elems[i] + " ");
        }
        System.out.println();
        System.out.println("长度是：" + getLength());
    }

    @Override
    public ElemType deQueue() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("队空");
        }
        ElemType result = elems[front];
        front = (front + 1) % capacity;
        return result;
    }

    @Override
    public ElemType getHead() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("队空");
        }
        return elems[front];
    }

    @Override
    public void enQueue(ElemType elem) throws OverFlowException {
        if ((rear + 1) % capacity == front) {
            throw new OverFlowException("空间已满");
        }
        elems[rear] = elem;
        rear = (rear + 1) % capacity;
    }

    @SuppressWarnings("unchecked")
    public SequenceQueue(SequenceQueue<ElemType> sequenceQueue) {
        front = sequenceQueue.front;
        rear = sequenceQueue.rear;
        capacity = sequenceQueue.capacity;
        elems = (ElemType[]) new Comparable[capacity];
        for (int i = front; i != rear; i = (i + 1) % capacity) {
            elems[i] = sequenceQueue.elems[i];
        }
    }
}