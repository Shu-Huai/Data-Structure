package shuhuai.datastructure.binarytree.LinkBinaryTree;

@SuppressWarnings({"unused"})
public class Node<ElemType> {
    protected ElemType elem;
    protected Node<ElemType> leftChild;
    protected Node<ElemType> rightChild;

    public Node() {
        elem = null;
        leftChild = null;
        rightChild = null;
    }

    public Node(ElemType elem) {
        this.elem = elem;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(ElemType elem, Node<ElemType> leftChild) {
        this.elem = elem;
        this.leftChild = leftChild;
    }

    public Node(ElemType elem, Node<ElemType> leftChild, Node<ElemType> rightChild) {
        this.elem = elem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
