package shuhuai.datastructure.stack.linkstack;

import shuhuai.datastructure.stack.Stack;

public class LinkStack<ElemType extends Comparable<? super ElemType>> implements Stack<ElemType> {
    protected LinkStackNode<ElemType> top;

    public LinkStack() {
        top = null;
    }

    @Override
    public int getLength() {
        int count = 0;
        LinkStackNode<ElemType> p = top;
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
        int count = 0;
        LinkStackNode<ElemType> p = top;
        while (p != null) {
            System.out.print(p.elem + " ");
            count++;
            p = p.next;
        }
        System.out.println();
        System.out.println("长度是：" + count);
    }

    @Override
    public void push(ElemType elem) {
        top = new LinkStackNode<>(elem, top);
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

    public LinkStack(LinkStack<ElemType> linkStack) {
        LinkStackNode<ElemType> p = linkStack.top;
        if (p == null) {
            top = null;
            return;
        }
        top = new LinkStackNode<>(p.elem, null);
        LinkStackNode<ElemType> q = top;
        p = p.next;
        while (p != null) {
            q.next = new LinkStackNode<>(p.elem, null);
            q = q.next;
            p = p.next;
        }
    }
}