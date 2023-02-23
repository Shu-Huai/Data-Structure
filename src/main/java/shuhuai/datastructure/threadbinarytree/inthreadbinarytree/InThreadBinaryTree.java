package shuhuai.datastructure.threadbinarytree.inthreadbinarytree;

import shuhuai.datastructure.binarytree.BinaryTree;
import shuhuai.datastructure.binarytree.LinkBinaryTree.LinkBinaryTree;
import shuhuai.datastructure.threadbinarytree.ThreadBinaryTree;

import java.util.function.Function;

@SuppressWarnings("unused")
public class InThreadBinaryTree<ElemType> implements ThreadBinaryTree<ElemType> {
    protected Node<ElemType> root;

    public InThreadBinaryTree(BinaryTree<ElemType> tree) {
        root = transBinaryTree(((LinkBinaryTree<ElemType>) tree).getRoot());
        calcThread();
    }

    public InThreadBinaryTree(InThreadBinaryTree<ElemType> tree) {
        root = copy(tree.root);
        calcThread();
    }

    public Node<ElemType> transBinaryTree(shuhuai.datastructure.binarytree.LinkBinaryTree.Node<ElemType> root) {
        if (root == null) {
            return null;
        }
        Node<ElemType> leftChild = transBinaryTree(root.getLeftChild());
        Node<ElemType> rightChild = transBinaryTree(root.getRightChild());
        return new Node<>(root.getElem(), leftChild, rightChild);
    }

    public void calcThread() {
        Node<ElemType> pre = new Node<>();
        calcThread(root, pre);
        pre.rightTag = Tag.Thread;
    }

    public void calcThread(Node<ElemType> p, Node<ElemType> pre) {
        if (p != null) {
            calcThread(p.leftChild, pre);
            if (p.leftChild == null) {
                p.leftChild = pre;
                p.leftTag = Tag.Thread;
            } else {
                p.leftTag = Tag.Data;
            }
            if (pre != null && pre.rightChild == null) {
                pre.rightChild = p;
                pre.rightTag = Tag.Thread;
            } else if (pre != null) {
                pre.rightTag = Tag.Data;
            }
            assert pre != null;
            pre.copy(p);
            calcThread(p.rightChild, pre);
        }
    }

    public Node<ElemType> getRoot() {
        return root;
    }

    public Node<ElemType> getFirst() {
        if (root == null) {
            return null;
        }
        Node<ElemType> p = root;
        while (p.leftTag == Tag.Data) {
            p = p.leftChild;
        }
        return p;
    }

    public Node<ElemType> getLast() {
        if (root == null) {
            return null;
        }
        Node<ElemType> p = root;
        while (p.rightTag == Tag.Data) {
            p = p.rightChild;
        }
        return p;
    }

    public Node<ElemType> getNext(Node<ElemType> node) {
        if (node.rightTag == Tag.Thread) {
            node = node.rightChild;
        } else {
            node = node.rightChild;
            while (node.leftTag == Tag.Data) {
                node = node.leftChild;
            }
        }
        return node;
    }

    public Node<ElemType> find(final ElemType elem) {
        Node<ElemType> p = getFirst();
        while (p != null && p.elem != elem) {
            p = getNext(p);
        }
        return p;
    }

    public void insertRightChild(Node<ElemType> node, ElemType elem) {
        if (node == null) {
            return;
        }
        Node<ElemType> x = new Node<>(elem, node, node.rightChild, Tag.Thread, node.rightTag);
        if (node.rightTag == Tag.Data) {
            Node<ElemType> q = node.rightChild;
            while (q.leftTag == Tag.Data) {
                q = q.leftChild;
            }
            q.leftChild = x;
        }
        node.rightChild = x;
        node.rightTag = Tag.Data;
    }

    public void deleteLeftChild(Node<ElemType> p) {
        if (p == null || p.leftTag != Tag.Data) {
            return;
        }
        Node<ElemType> q = p.leftChild;
        while (q.leftTag == Tag.Data) {
            q = q.leftChild;
        }
        destroy(p.leftChild);
        p.leftChild = q.leftChild;
        p.leftTag = Tag.Thread;
    }

    void destroy(Node<ElemType> r) {
        if (r != null) {
            if (r.leftTag == Tag.Data) {
                destroy(r.leftChild);
            }
            if (r.rightTag == Tag.Data) {
                destroy(r.rightChild);
            }
        }
    }

    void inOrderTraverse() {
        inOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    void inOrderTraverse(Function<String, Void> output) {
        for (Node<ElemType> p = getFirst(); p != null; p = getNext(p)) {
            output.apply(p.elem + " ");
            if (p.leftChild != null) {
                output.apply(p.leftChild.elem + " ");
            } else {
                output.apply(null + " ");
            }
            if (p.rightChild != null) {
                output.apply(p.rightChild.elem + " ");
            } else {
                output.apply(null + " ");
            }
        }
    }

    public Node<ElemType> copy(Node<ElemType> node) {
        if (node == null) {
            return null;
        }
        Node<ElemType> leftChild;
        Node<ElemType> rightChild;
        if (node.leftTag == Tag.Data) {
            leftChild = copy(node.leftChild);
        } else {
            leftChild = null;
        }
        if (node.rightTag == Tag.Data) {
            rightChild = copy(node.rightChild);
        } else {
            rightChild = null;
        }
        return new Node<>(node.elem, leftChild, rightChild);
    }
}