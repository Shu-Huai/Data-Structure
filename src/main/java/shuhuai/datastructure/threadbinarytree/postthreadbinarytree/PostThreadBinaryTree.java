package shuhuai.datastructure.threadbinarytree.postthreadbinarytree;

import shuhuai.datastructure.binarytree.BinaryTree;
import shuhuai.datastructure.binarytree.LinkBinaryTree.LinkBinaryTree;
import shuhuai.datastructure.threadbinarytree.ThreadBinaryTree;

import java.util.function.Function;

@SuppressWarnings("unused")
public class PostThreadBinaryTree<ElemType> implements ThreadBinaryTree<ElemType> {
    protected Node<ElemType> root;

    public PostThreadBinaryTree(BinaryTree<ElemType> tree) {
        root = transBinaryTree(((LinkBinaryTree<ElemType>) tree).getRoot());
        calcThread();
    }

    public PostThreadBinaryTree(PostThreadBinaryTree<ElemType> tree) {
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
    }

    public void calcThread(Node<ElemType> p, Node<ElemType> pre) {
        if (p != null) {
            calcThread(p.leftChild, pre);
            calcThread(p.rightChild, pre);
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
        while (p.leftTag == Tag.Data || p.rightTag == Tag.Data) {
            if (p.leftTag == Tag.Data) {
                p = p.leftChild;
            } else {
                p = p.rightChild;
            }
        }
        return p;
    }

    public Node<ElemType> getLast() {
        return root;
    }

    public Node<ElemType> getNext(Node<ElemType> node) {
        if (node == root) {
            return null;
        }
        if (node.rightTag == Tag.Thread) {
            node = node.rightChild;
        } else {
            Node<ElemType> parent = getParent(node);
            if (parent.rightChild == node) {
                return parent;
            }
            Node<ElemType> p = parent;
            while (p.leftTag == Tag.Data || p.rightTag == Tag.Data) {
                if (p.leftTag == Tag.Data) {
                    p = p.leftChild;
                } else {
                    p = p.rightChild;
                }
            }
            return p;
        }
        return node;
    }

    Node<ElemType> getParent(final Node<ElemType> node) {
        return (root == null || root == node) ? null : getParent(root, node);
    }

    Node<ElemType> getParent(Node<ElemType> root, final Node<ElemType> node) {
        if (root == null) {
            return null;
        }
        if (this.root.leftTag == Tag.Data && this.root.leftChild == node ||
                this.root.rightTag == Tag.Data && this.root.rightChild == node) {
            return root;
        }
        Node<ElemType> temp = root.leftTag == Tag.Thread ? null : getParent(root.leftChild, node);
        if (temp != null) {
            return temp;
        }
        return root.rightTag == Tag.Thread ? null : getParent(root.rightChild, node);
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
        Node<ElemType> x;
        if (node.rightTag == Tag.Thread) {
            x = new Node<>(elem, node.leftChild, node, node.leftTag, Tag.Thread);
            if (node.leftChild.rightTag == Tag.Thread) {
                node.leftChild.rightChild = x;
            }
        } else {
            x = new Node<>(elem, node.rightChild, node.rightChild, Tag.Thread, Tag.Data);
            if (node.rightChild.rightTag == Tag.Thread) {
                node.rightChild.rightChild = x;
            }
            if (node.leftTag == Tag.Thread) {
                node.leftChild = x;
            }
        }
        node.rightChild = x;
        node.rightTag = Tag.Data;
    }

    public void deleteLeftChild(Node<ElemType> p) {
        if (p == null || p.leftTag == Tag.Thread) {
            return;
        }
        Node<ElemType> q = getParent(p);
        if (q.rightTag == Tag.Thread) {
            q.rightChild = p;
        }
        destroy(p.leftChild);
        p.leftChild = q.leftChild;
        p.leftTag = Tag.Thread;
    }

    void destroy(Node<ElemType> root) {
        if (root != null) {
            if (root.leftTag == Tag.Data) {
                destroy(root.leftChild);
            }
            if (root.rightTag == Tag.Data) {
                destroy(root.rightChild);
            }
        }
    }

    void postOrderTraverse() {
        postOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    void postOrderTraverse(Function<String, Void> output) {
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