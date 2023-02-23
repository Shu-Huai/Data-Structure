package shuhuai.datastructure.queue.sequencequeue;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.queue.Queue;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class SequenceQueue<ElemType extends Comparable<? super ElemType>> implements Queue<ElemType> {
    protected int front;
    protected int rear;
    protected int capacity;
    protected ElemType[] elems;

    public SequenceQueue(int capacity) {
        elems = (ElemType[]) new Comparable[capacity];
        front = 0;
        rear = 0;
        this.capacity = capacity;
    }

    public SequenceQueue(SequenceQueue<ElemType> sequenceQueue) {
        front = sequenceQueue.front;
        rear = sequenceQueue.rear;
        capacity = sequenceQueue.capacity;
        elems = (ElemType[]) new Comparable[capacity];
        for (int i = front; i != rear; i = (i + 1) % capacity) {
            elems[i] = sequenceQueue.elems[i];
        }
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
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    @Override
    public void traverse(Function<String, Void> output) {
        for (int i = front; i != rear; i = (i + 1) % capacity) {
            output.apply(elems[i] + " ");
        }
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
}