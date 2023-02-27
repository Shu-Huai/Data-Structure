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

    public ElemType getElem() {
        return elem;
    }

    public void setElem(ElemType elem) {
        this.elem = elem;
    }

    public Node<ElemType> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<ElemType> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<ElemType> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<ElemType> rightChild) {
        this.rightChild = rightChild;
    }

    public void copy(Node<ElemType> node) {
        if (node == null) {
            return;
        }
        elem = node.elem;
        leftChild = node.leftChild;
        rightChild = node.rightChild;
    }
}
