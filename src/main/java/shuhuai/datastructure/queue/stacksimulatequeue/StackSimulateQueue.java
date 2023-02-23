package shuhuai.datastructure.queue.stacksimulatequeue;

import shuhuai.datastructure.exceptions.BaseException;
import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.queue.Queue;
import shuhuai.datastructure.stack.Stack;
import shuhuai.datastructure.stack.linkstack.LinkStack;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public class StackSimulateQueue<ElemType> implements Queue<ElemType> {
    protected Stack<ElemType> firstStack;
    protected Stack<ElemType> secondStack;

    public StackSimulateQueue() {
        firstStack = new LinkStack<>();
        secondStack = new LinkStack<>();
    }

    public StackSimulateQueue(final StackSimulateQueue<ElemType> queue) {
        firstStack = new LinkStack<>((LinkStack<ElemType>) queue.firstStack);
        secondStack = new LinkStack<>((LinkStack<ElemType>) queue.secondStack);
    }

    @Override
    public int getLength() {
        return firstStack.getLength();
    }

    @Override
    public boolean isEmpty() {
        return firstStack.isEmpty();
    }

    @Override
    public void clear() {
        try {
            while (!firstStack.isEmpty()) {
                firstStack.pop();
            }
            while (!secondStack.isEmpty()) {
                secondStack.pop();
            }
        } catch (BaseException ignored) {
        }
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
        try {
            int length = firstStack.getLength();
            for (int i = 0; i < length; i++) {
                secondStack.push(firstStack.pop());
            }
            for (int i = 0; i < length; i++) {
                output.apply(secondStack.getTop() + " ");
                firstStack.push(secondStack.pop());
            }
        } catch (BaseException ignored) {
        }
    }

    @Override
    public ElemType deQueue() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("队空");
        }
        try {
            int length = firstStack.getLength();
            for (int i = 0; i < length; i++) {
                secondStack.push(firstStack.pop());
            }
            secondStack.pop();
            for (int i = 0; i < length - 1; i++) {
                firstStack.push(secondStack.pop());
            }
        } catch (BaseException ignored) {
        }
        return null;
    }

    @Override
    public ElemType getHead() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("队空");
        }
        try {
            int length = firstStack.getLength();
            for (int i = 0; i < length; i++) {
                secondStack.push(firstStack.pop());
            }
            ElemType elem = secondStack.getTop();
            for (int i = 0; i < length; i++) {
                firstStack.push(secondStack.pop());
            }
            return elem;
        } catch (BaseException ignored) {
        }
        return null;
    }

    @Override
    public void enQueue(ElemType elem) {
        ((LinkStack<ElemType>) firstStack).push(elem);
    }
}
