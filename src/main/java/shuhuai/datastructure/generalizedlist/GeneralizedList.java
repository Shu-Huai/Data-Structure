package shuhuai.datastructure.generalizedlist;

import shuhuai.datastructure.exceptions.RangeException;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public class GeneralizedList<ElemType> {
    protected Node<ElemType> head;

    public GeneralizedList() {
        head = new Node<>(NodeType.Head);
        head.refCount = 1;
    }

    public GeneralizedList(Node<ElemType> head) {
        this.head = head;
    }

    public GeneralizedList(GeneralizedList<ElemType> generalizedList) {
        head = copy(generalizedList.head);
    }

    public void clear(Node<ElemType> head) {
        head.refCount--;
        if (head.refCount == 0) {
            for (Node<ElemType> p = head.next; p != null; p = p.next) {
                if (p.tag == NodeType.List) {
                    clear(p.child);
                }
            }
        }
    }

    public Node<ElemType> first() {
        return head.next;
    }

    public Node<ElemType> next(Node<ElemType> node) {
        return node.next;
    }

    boolean isEmpty() {
        return head.next == null;
    }

    void insert(ElemType elem) {
        Node<ElemType> p = new Node<>(NodeType.Data, head.next);
        p.elem = elem;
        head.next = p;
    }

    void insert(GeneralizedList<ElemType> subList) {
        Node<ElemType> p = new Node<>(NodeType.List, head.next);
        p.child = subList.head;
        subList.head.refCount++;
        head.next = p;
    }

    void delete(int index) throws RangeException {
        if (index < 0 || index > getLength()) {
            throw new RangeException("下标越界");
        }
        Node<ElemType> pre = head;
        Node<ElemType> p = head.next;
        for (int i = 0; i < index; i++) {
            pre = p;
            p = p.next;
        }
        pre.next = p.next;
        if (p.tag == NodeType.List) {
            clear(p.child);
        }
    }

    int getDepth() {
        return getDepth(head);
    }

    int getDepth(Node<ElemType> head) {
        if (head.next == null) {
            return 1;
        }
        int subMaxDepth = 0;
        for (Node<ElemType> p = head.next; p != null; p = p.next) {
            if (p.tag == NodeType.List) {
                int curSubDepth = getDepth(p.child);
                if (subMaxDepth < curSubDepth) {
                    subMaxDepth = curSubDepth;
                }
            }
        }
        return subMaxDepth + 1;
    }

    int getLength() {
        Node<ElemType> p = head.next;
        int length = 0;
        while (p != null) {
            p = p.next;
            length++;
        }
        return length;
    }

    public Node<ElemType> copy(Node<ElemType> sourceHead) {
        Node<ElemType> destHead = new Node<>(NodeType.Head);
        Node<ElemType> destPtr = destHead;
        destHead.refCount = 1;
        for (Node<ElemType> p = sourceHead.next; p != null; p = p.next) {
            destPtr = destPtr.next = new Node<>(p.tag);
            if (p.tag == NodeType.List) {
                destPtr.child = copy(p.child);
            } else {
                destPtr.elem = p.elem;
            }
        }
        return head;
    }

    public void traverse() {
        traverse(head, value -> {
            System.out.print(value);
            return null;
        });
    }

    public void traverse(Function<String, Void> output) {
        traverse(head, output);
    }

    public void traverse(Node<ElemType> head, Function<String, Void> output) {
        boolean first = true;
        output.apply("(");
        for (Node<ElemType> p = head.next; p != null; p = p.next) {
            if (first) {
                first = false;
            } else {
                output.apply(", ");
            }
            if (p.tag == NodeType.Data) {
                output.apply((String) p.elem);
            } else {
                traverse(p.child, output);
            }
        }
        output.apply(")\n");
    }
}