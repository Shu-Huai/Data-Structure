package shuhuai.datastructure.linearlist.doublelinklist;

public class DoubleLinkList<ElemType extends Comparable<? super ElemType>> {
    protected Node<ElemType> head;
    protected int size;

    public DoubleLinkList() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(ElemType elem) {
        Node<ElemType> node = new Node<ElemType>(elem);
        if (this.head == null) {
            this.head = node;
        } else {
            Node<ElemType> cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
            node.prev = cur;
        }
        this.size++;
    }

    public void add(int index, ElemType elem) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            Node<ElemType> node = new Node<ElemType>(elem);
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        } else {
            Node<ElemType> cur = this.head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            Node<ElemType> node = new Node<ElemType>(elem);
            node.next = cur.next;
            node.prev = cur;
            cur.next.prev = node;
            cur.next = node;
        }
        this.size++;
    }


}
