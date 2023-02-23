package shuhuai.datastructure.threadbinarytree.inthreadbinarytree;

@SuppressWarnings("unused")
public class Node<ElemType> implements shuhuai.datastructure.threadbinarytree.Node<ElemType> {
    protected ElemType elem;
    protected Node<ElemType> leftChild;
    protected Node<ElemType> rightChild;
    protected Tag leftTag;
    protected Tag rightTag;

    public Node() {
        elem = null;
        leftChild = null;
        rightChild = null;
        leftTag = Tag.Data;
        rightTag = Tag.Data;
    }

    public Node(final shuhuai.datastructure.threadbinarytree.Node<ElemType> node) {
        elem = null;
        leftChild = null;
        rightChild = null;
        leftTag = Tag.Data;
        rightTag = Tag.Data;
    }

    public Node(final ElemType elem) {
        this.elem = elem;
    }

    public Node(final ElemType elem, final Node<ElemType> leftChild, final Node<ElemType> rightChild) {
        this.elem = elem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(final ElemType elem, final Node<ElemType> leftChild, final Node<ElemType> rightChild,
                final Tag leftTag, final Tag rightTag) {
        this.elem = elem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.leftTag = leftTag;
        this.rightTag = rightTag;
    }

    public void copy(final shuhuai.datastructure.threadbinarytree.Node<ElemType> node) {
        elem = ((Node<ElemType>) node).elem;
        leftChild = ((Node<ElemType>) node).leftChild;
        rightChild = ((Node<ElemType>) node).rightChild;
        leftTag = ((Node<ElemType>) node).leftTag;
        rightTag = ((Node<ElemType>) node).rightTag;
    }
}