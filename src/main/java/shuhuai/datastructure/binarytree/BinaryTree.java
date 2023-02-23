package shuhuai.datastructure.binarytree;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public interface BinaryTree<ElemType> {
    void preOrderTraverse();

    void preOrderTraverse(Function<String, Void> output);

    void inOrderTraverse();

    void inOrderTraverse(Function<String, Void> output);

    void postOrderTraverse();

    void postOrderTraverse(Function<String, Void> output);

    void levelOrderTraverse();

    void levelOrderTraverse(Function<String, Void> output);

    int getNodeNumber();

    void clear();

    int getHeight();
}