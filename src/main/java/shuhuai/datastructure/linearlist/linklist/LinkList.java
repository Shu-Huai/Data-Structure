package shuhuai.datastructure.linearlist.linklist;

public class LinkList<ElemType extends Comparable<? super ElemType>> {
    protected LinkListNode<ElemType> head;
    protected Integer length;

    public LinkList() {
        this.head = new LinkListNode<>();
        this.length = 0;
    }

    public LinkList(ElemType[] elems) {
        this.head = new LinkListNode<>();
        this.length = elems.length;
        LinkListNode<ElemType> p = this.head;
        for (ElemType elem : elems) {
            p.next = new LinkListNode<>(elem);
            p = p.next;
        }
    }

    public LinkList(LinkList<ElemType> linkList) {
        this.head = new LinkListNode<>();
        this.length = linkList.length;
        LinkListNode<ElemType> p = this.head;
        LinkListNode<ElemType> q = linkList.head;
        while (q.next != null) {
            p.next = new LinkListNode<>(q.elem);
            p = p.next;
            q = q.next;
        }
    }

    public LinkListNode<ElemType> getHead() {
        return head;
    }

    public void setHead(LinkListNode<ElemType> head) {
        this.head = head;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void clear() {
        this.head = new LinkListNode<>();
        this.length = 0;
    }

    public Boolean isEmpty() {
        return this.length == 0;
    }

    public void Display(){
        LinkListNode<ElemType> p = this.head.next;
        while(p != null){
            System.out.print(p.elem + " ");
            p = p.next;
        }
        System.out.println();
    }
}
