package shuhuai.datastructure.threadbinarytree;

@SuppressWarnings("unused")
public interface ThreadBinaryTree<ElemType> {
    Node<ElemType> getRoot();

    void calcThread();

    Node<ElemType> getFirst();

    Node<ElemType> getLast();

    Node<ElemType> find(ElemType elem);
}