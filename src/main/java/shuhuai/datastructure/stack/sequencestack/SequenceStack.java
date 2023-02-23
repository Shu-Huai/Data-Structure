package shuhuai.datastructure.stack.sequencestack;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.stack.Stack;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class SequenceStack<ElemType> implements Stack<ElemType> {
    protected ElemType[] elems;
    protected int top;

    protected int capacity;

    public SequenceStack(int capacity) {
        elems = (ElemType[]) new Object[capacity];
        top = -1;
        this.capacity = capacity;
    }

    public SequenceStack(SequenceStack<ElemType> sequenceStack) {
        top = sequenceStack.top;
        capacity = sequenceStack.capacity;
        elems = (ElemType[]) new Object[capacity];
        if (top + 1 >= 0) {
            System.arraycopy(sequenceStack.elems, 0, elems, 0, top + 1);
        }
    }

    @Override
    public int getLength() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void clear() {
        top = -1;
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
        for (int i = top; i >= 0; i--) {
            output.apply(elems[i] + " ");
        }
    }

    @Override
    public void push(ElemType elem) throws OverFlowException {
        if (top == capacity) {
            throw new OverFlowException("空间已满");
        }
        elems[++top] = elem;
    }

    @Override
    public ElemType getTop() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("栈空");
        }
        return elems[top];
    }

    @Override
    public ElemType pop() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("栈空");
        }
        return elems[top--];
    }
}