package shuhuai.datastructure.linearlist.LinkListWithoutHead;

import shuhuai.datastructure.exceptions.RangeException;

public class LinkListWithoutHead<ElemType extends Comparable<? super ElemType>> {
    protected LinkListNode<ElemType> head;
    protected Integer length;

    public LinkListWithoutHead() {
        head = null;
        length = 0;
    }

    public LinkListWithoutHead(final ElemType[] elems) {
        head = new LinkListNode<>(elems[0]);
        LinkListNode<ElemType> p = head;
        for (int i = 1; i < elems.length; i++) {
            p.next = new LinkListNode<>(elems[i]);
            p = p.next;
        }
        this.length = elems.length;
    }

    public LinkListWithoutHead(final LinkListWithoutHead<ElemType> linkList) {
        head = new LinkListNode<>(linkList.head.elem);
        LinkListNode<ElemType> p = head;
        LinkListNode<ElemType> q = linkList.head;
        while (q.next != null) {
            p.next = new LinkListNode<>(q.next.elem);
            p = p.next;
            q = q.next;
        }
        this.length = linkList.length;
    }

    public LinkListNode<ElemType> getHead() {
        return head;
    }

    public void setHead(final LinkListNode<ElemType> head) {
        this.head = head;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(final Integer length) {
        this.length = length;
    }

    public void clear() {
        head = null;
        length = 0;
    }

    public Boolean isEmpty() {
        return length == 0;
    }

    public void display() {
        traverse();
        System.out.println("长度是: " + length);
    }

    public void traverse() {
        LinkListNode<ElemType> p = head;
        while (p != null) {
            System.out.print(p.elem);
            if (p.next != null) {
                System.out.print(" -> ");
            }
            p = p.next;
        }
        System.out.println();
    }

    public void appendElem(final ElemType elem) {
        if (head == null) {
            head = new LinkListNode<>(elem);
        } else {
            LinkListNode<ElemType> p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new LinkListNode<>(elem);
        }
        length++;
    }

    public void insertElem(final ElemType elem, final Integer index) throws RangeException {
        if (index < 0 || index > length) {
            throw new RangeException("下标越界");
        }
        if (index == 0) {
            head = new LinkListNode<>(elem, head);
        } else {
            LinkListNode<ElemType> p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            p.next = new LinkListNode<>(elem, p.next);
        }
        length++;
    }

    public void deleteElem(final Integer index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        if (index == 0) {
            head = head.next;
        } else {
            LinkListNode<ElemType> p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            p.next = p.next.next;
        }
        length--;
    }

    public LinkListNode<ElemType> getNode(final Integer index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        LinkListNode<ElemType> p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    public ElemType getElem(final Integer index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        return getNode(index).elem;
    }

    public void setElem(final ElemType elem, final Integer index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        getNode(index).elem = elem;
    }

    public Integer getIndex(final ElemType elem) {
        LinkListNode<ElemType> p = head;
        for (int i = 0; i < length; i++) {
            if (p.elem.equals(elem)) {
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    public void reverse() {
        LinkListNode<ElemType> p = head;
        LinkListNode<ElemType> q = null;
        while (p != null) {
            LinkListNode<ElemType> r = p.next;
            p.next = q;
            q = p;
            p = r;
        }
        head = q;
    }

    public void extend(final LinkListWithoutHead<ElemType> linkList) {
        if (linkList.head == null) {
            return;
        }
        if (head == null) {
            head = linkList.head;
            length = linkList.length;
            return;
        }
        LinkListNode<ElemType> p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = linkList.head;
        length += linkList.length;
    }
}
