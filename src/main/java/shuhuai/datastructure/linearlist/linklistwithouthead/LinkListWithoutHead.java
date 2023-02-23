package shuhuai.datastructure.linearlist.linklistwithouthead;

import shuhuai.datastructure.exceptions.RangeException;
import shuhuai.datastructure.linearlist.LinearList;

@SuppressWarnings({"unused"})
public class LinkListWithoutHead<ElemType extends Comparable<? super ElemType>> implements LinearList<ElemType> {
    protected LinkListWithoutHeadNode<ElemType> head;
    protected int length;

    public LinkListWithoutHead() {
        head = null;
        length = 0;
    }

    public LinkListWithoutHead(final ElemType[] elems) {
        head = new LinkListWithoutHeadNode<>(elems[0]);
        LinkListWithoutHeadNode<ElemType> p = head;
        for (int i = 1; i < elems.length; i++) {
            p.next = new LinkListWithoutHeadNode<>(elems[i]);
            p = p.next;
        }
        this.length = elems.length;
    }

    public LinkListWithoutHead(final LinkListWithoutHead<ElemType> linkList) {
        head = new LinkListWithoutHeadNode<>(linkList.head.elem);
        LinkListWithoutHeadNode<ElemType> p = head;
        LinkListWithoutHeadNode<ElemType> q = linkList.head;
        while (q.next != null) {
            p.next = new LinkListWithoutHeadNode<>(q.next.elem);
            p = p.next;
            q = q.next;
        }
        this.length = linkList.length;
    }

    public LinkListWithoutHeadNode<ElemType> getHead() {
        return head;
    }

    public void setHead(final LinkListWithoutHeadNode<ElemType> head) {
        this.head = head;
    }

    @Override
    public int getLength() {
        return length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    @Override
    public void clear() {
        head = null;
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void display() {
        traverse();
        System.out.println("长度是: " + length);
    }

    @Override
    public void traverse() {
        LinkListWithoutHeadNode<ElemType> p = head;
        while (p != null) {
            System.out.print(p.elem);
            if (p.next != null) {
                System.out.print(" -> ");
            }
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public void appendElem(final ElemType elem) {
        if (head == null) {
            head = new LinkListWithoutHeadNode<>(elem);
        } else {
            LinkListWithoutHeadNode<ElemType> p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new LinkListWithoutHeadNode<>(elem);
        }
        length++;
    }

    @Override
    public void insertElem(final ElemType elem, final int index) throws RangeException {
        if (index < 0 || index > length) {
            throw new RangeException("下标越界");
        }
        if (index == 0) {
            head = new LinkListWithoutHeadNode<>(elem, head);
        } else {
            LinkListWithoutHeadNode<ElemType> p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            p.next = new LinkListWithoutHeadNode<>(elem, p.next);
        }
        length++;
    }

    @Override
    public void deleteElem(final int index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        if (index == 0) {
            head = head.next;
        } else {
            LinkListWithoutHeadNode<ElemType> p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            p.next = p.next.next;
        }
        length--;
    }

    public LinkListWithoutHeadNode<ElemType> getNode(final int index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        LinkListWithoutHeadNode<ElemType> p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    @Override
    public ElemType getElem(final int index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        return getNode(index).elem;
    }

    @Override
    public void setElem(final ElemType elem, final int index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        getNode(index).elem = elem;
    }

    @Override
    public int findElem(final ElemType elem) {
        LinkListWithoutHeadNode<ElemType> p = head;
        for (int i = 0; i < length; i++) {
            if (p.elem.equals(elem)) {
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    public void reverse() {
        LinkListWithoutHeadNode<ElemType> p = head;
        LinkListWithoutHeadNode<ElemType> q = null;
        while (p != null) {
            LinkListWithoutHeadNode<ElemType> r = p.next;
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
        LinkListWithoutHeadNode<ElemType> p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = linkList.head;
        length += linkList.length;
    }
}
