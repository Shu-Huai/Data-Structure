package shuhuai.datastructure.linearlist.linklist;

import shuhuai.datastructure.exceptions.RangeException;

public class LinkList<ElemType extends Comparable<? super ElemType>> {
    protected Node<ElemType> head;
    protected Integer length;

    public LinkList() {
        head = new Node<>();
        length = 0;
    }

    public LinkList(final ElemType[] elems) {
        head = new Node<>();
        length = elems.length;
        Node<ElemType> p = head;
        for (ElemType elem : elems) {
            p.next = new Node<>(elem);
            p = p.next;
        }
    }

    public LinkList(final LinkList<ElemType> linkList) {
        head = new Node<>();
        length = linkList.length;
        Node<ElemType> p = head;
        Node<ElemType> q = linkList.head;
        while (q.next != null) {
            p.next = new Node<>(q.elem);
            p = p.next;
            q = q.next;
        }
    }

    public Node<ElemType> getHead() {
        return head;
    }

    public void setHead(final Node<ElemType> head) {
        this.head = head;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(final Integer length) {
        this.length = length;
    }

    public void clear() {
        head = new Node<>();
        length = 0;
    }

    public Boolean isEmpty() {
        return length == 0;
    }

    public void display() {
        System.out.print("头 -> ");
        traverse();
        System.out.println("长度是: " + length);
    }

    public void traverse() {
        Node<ElemType> p = head.next;
        while (p != null) {
            System.out.print(p.elem);
            if (p.next != null) {
                System.out.print(" -> ");
            }
            p = p.next;
        }
        System.out.println();
    }

    public Node<ElemType> getNode(final Integer index) throws RangeException {
        if (index < -1 || index >= length) {
            throw new RangeException("下标越界");
        }
        Node<ElemType> p = head;
        for (int i = -1; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    public void setNode(final Integer index, final Node<ElemType> linkListNode) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        Node<ElemType> p = getNode(index - 1);
        linkListNode.next = p.next.next;
        p.next = linkListNode;
    }

    public void appendElem(final ElemType elem) {
        Node<ElemType> p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node<>(elem);
        length++;
    }

    public void insertElem(final ElemType elem, final Integer index) throws RangeException {
        if (index < 0 || index > length) {
            throw new RangeException("下标越界");
        }
        Node<ElemType> p = getNode(index - 1);
        p.next = new Node<>(elem, p.next);
        length++;
    }

    public void deleteElem(final Integer index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        Node<ElemType> p = getNode(index - 1);
        p.next = p.next.next;
        length--;
    }

    public ElemType getElem(final Integer index) throws RangeException {
        return getNode(index).elem;
    }

    public void setElem(final ElemType elem, final Integer index) throws RangeException {
        getNode(index).elem = elem;
    }

    public Integer getIndex(final ElemType elem) {
        Node<ElemType> p = head.next;
        for (int i = 0; i < length; i++) {
            if (p.elem.equals(elem)) {
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    public void swapElem(final Integer indexA, final Integer indexB) throws RangeException {
        if (indexA < 0 || indexA >= length || indexB < 0 || indexB >= length) {
            throw new RangeException("下标越界");
        }
        Node<ElemType> pointerA = getNode(indexA);
        Node<ElemType> pointerB = getNode(indexB);
        Node<ElemType> temp = pointerA.next;
        pointerA.next = pointerB.next;
        pointerB.next = temp;
    }

    public void reverse() {
        Node<ElemType> p = head.next;
        head.next = null;
        while (p != null) {
            Node<ElemType> temp = p.next;
            p.next = head.next;
            head.next = p;
            p = temp;
        }
    }

    public void extend(final LinkList<ElemType> linkList) {
        Node<ElemType> p = head.next;
        while (p.next != null) {
            p = p.next;
        }
        p.next = linkList.head.next;
        length += linkList.length;
    }

    public void deleteBetween(final ElemType minElem, final ElemType maxElem) throws RangeException {
        if (minElem.compareTo(maxElem) > 0) {
            throw new RangeException("最小值大于最大值");
        }
        Node<ElemType> p = head;
        while (p != null && p.next != null) {
            if (p.next.elem.compareTo(minElem) > 0 && p.next.elem.compareTo(maxElem) < 0) {
                Node<ElemType> temp = p.next;
                p.next = p.next.next;
                length--;
            } else {
                p = p.next;
            }
        }
    }
}