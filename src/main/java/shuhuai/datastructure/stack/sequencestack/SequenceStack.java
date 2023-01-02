package shuhuai.datastructure.stack.sequencestack;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.stack.Stack;

public class SequenceStack<ElemType extends Comparable<? super ElemType>> implements Stack<ElemType> {
    protected ElemType[] elems;
    protected int top;

    protected int capacity;

    @SuppressWarnings("unchecked")
    public SequenceStack(int capacity) {
        elems = (ElemType[]) new Comparable[capacity];
        top = -1;
        this.capacity = capacity;
    }

    public int getLength() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void clear() {
        top = -1;
    }

    public void traverse() {
        for (int i = top; i >= 0; i--) {
            System.out.print(elems[i] + " ");
        }
        System.out.println();
        System.out.println("长度是：" + getLength());
    }

    public void push(ElemType elem) throws OverFlowException {
        if (top == capacity) {
            throw new OverFlowException("空间已满");
        }
        elems[++top] = elem;
    }

    public int getTop() {
        return top;
    }

    public ElemType getTopElem() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("栈空");
        }
        return elems[top];
    }

    public ElemType pop() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("栈空");
        }
        return elems[top--];
    }

    @SuppressWarnings("unchecked")
    public SequenceStack(SequenceStack<ElemType> sequenceStack) {
        top = sequenceStack.top;
        capacity = sequenceStack.capacity;
        elems = (ElemType[]) new Comparable[capacity];
        if (top + 1 >= 0) {
            System.arraycopy(sequenceStack.elems, 0, elems, 0, top + 1);
        }
    }
}