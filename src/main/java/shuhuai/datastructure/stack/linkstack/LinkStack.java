package shuhuai.datastructure.stack.linkstack;

import shuhuai.datastructure.stack.Stack;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public class LinkStack<ElemType> implements Stack<ElemType> {
    protected Node<ElemType> top;

    public LinkStack() {
        top = null;
    }

    public LinkStack(LinkStack<ElemType> linkStack) {
        Node<ElemType> p = linkStack.top;
        if (p == null) {
            top = null;
            return;
        }
        top = new Node<>(p.elem, null);
        Node<ElemType> q = top;
        p = p.next;
        while (p != null) {
            q.next = new Node<>(p.elem, null);
            q = q.next;
            p = p.next;
        }
    }

    @Override
    public int getLength() {
        int count = 0;
        Node<ElemType> p = top;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void clear() {
        top = null;
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
        Node<ElemType> p = top;
        while (p != null) {
            output.apply(p.elem + " ");
            count++;
            p = p.next;
        }
    }

    @Override
    public void push(ElemType elem) {
        top = new Node<>(elem, top);
    }

    @Override
    public ElemType getTop() {
        return top.elem;
    }

    @Override
    public ElemType pop() {
        ElemType result = top.elem;
        top = top.next;
        return result;
    }
}