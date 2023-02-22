package shuhuai.datastructure.binarytree.LinkBinaryTree;

import shuhuai.datastructure.binarytree.BinaryTree;
import shuhuai.datastructure.exceptions.BaseException;
import shuhuai.datastructure.exceptions.NotExistException;
import shuhuai.datastructure.queue.Queue;
import shuhuai.datastructure.queue.linkqueue.LinkQueue;

import java.util.function.Function;

public class LinkBinaryTree<ElemType> implements BinaryTree<ElemType> {
    protected Node<ElemType> root;

    public LinkBinaryTree() {
        root = null;
    }

    public LinkBinaryTree(ElemType elem) {
        root = new Node<>(elem);
    }

    public LinkBinaryTree(Node<ElemType> root) {
        this.root = copy(root);
    }

    public LinkBinaryTree(LinkBinaryTree<ElemType> linkBinaryTree) {
        root = new Node<>();
        clear(root);
        root = copy(linkBinaryTree.root);
    }

    public Node<ElemType> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node<ElemType> copy(Node<ElemType> node) {
        if (node == null) {
            return null;
        }
        Node<ElemType> result = new Node<>(node.elem);
        result.leftChild = copy(node.leftChild);
        result.rightChild = copy(node.rightChild);
        return result;
    }

    public ElemType getElem(Node<ElemType> node) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        return node.elem;
    }

    public void setElem(Node<ElemType> node, ElemType elem) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        node.elem = elem;
    }

    public Node<ElemType> getNode(ElemType elem) {
        return getNode(root, elem);
    }

    public Node<ElemType> getNode(Node<ElemType> root, ElemType elem) {
        if (root == null) {
            return null;
        }
        if (root.elem == elem) {
            return root;
        }
        Node<ElemType> p = getNode(root.leftChild, elem);
        if (p != null) {
            return p;
        }
        p = getNode(root.rightChild, elem);
        return p;
    }

    public void preOrderTraverse() {
        preOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    public void preOrderTraverse(Function<String, Void> visit) {
        preOrderTraverse(root, visit);
        visit.apply("\n");
    }

    public void preOrderTraverse(Node<ElemType> root, Function<String, Void> visit) {
        if (root == null) {
            return;
        }
        visit.apply(root.elem + " ");
        preOrderTraverse(root.leftChild, visit);
        preOrderTraverse(root.rightChild, visit);
    }

    public void inOrderTraverse() {
        inOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    public void inOrderTraverse(Function<String, Void> visit) {
        inOrderTraverse(root, visit);
        visit.apply("\n");
    }

    public void inOrderTraverse(Node<ElemType> root, Function<String, Void> visit) {
        if (root == null) {
            return;
        }
        preOrderTraverse(root.leftChild, visit);
        visit.apply(root.elem + " ");
        preOrderTraverse(root.rightChild, visit);
    }

    public void postOrderTraverse() {
        postOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    public void postOrderTraverse(Function<String, Void> visit) {
        postOrderTraverse(root, visit);
        visit.apply("\n");
    }

    public void postOrderTraverse(Node<ElemType> root, Function<String, Void> visit) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.leftChild, visit);
        postOrderTraverse(root.rightChild, visit);
        visit.apply(root.elem + " ");
    }

    public void levelOderTraverse() {
        levelOderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    public void levelOderTraverse(Function<String, Void> visit) {
        levelOderTraverse(root, visit);
        visit.apply("\n");
    }

    public void levelOderTraverse(Node<ElemType> root, Function<String, Void> visit) {
        if (root == null) {
            return;
        }
        try {
            Queue<Node<ElemType>> queue = new LinkQueue<>();
            Node<ElemType> p;
            queue.enQueue(root);
            while (!queue.isEmpty()) {
                p = queue.getHead();
                queue.deQueue();
                visit.apply(p.elem + " ");
                if (p.leftChild != null) {
                    queue.enQueue(p.leftChild);
                }
                if (p.rightChild != null) {
                    queue.enQueue(p.rightChild);
                }
            }
        } catch (BaseException ignored) {
        }
    }

    public int getNodeNumber() {
        return getNodeNumber(root);
    }

    public int getNodeNumber(Node<ElemType> root) {
        if (root == null) {
            return 0;
        }
        return 1 + getNodeNumber(root.leftChild) + getNodeNumber(root.rightChild);
    }

    public Node<ElemType> getLeftChild(Node<ElemType> node) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        return node.leftChild;
    }

    public Node<ElemType> getRightChild(Node<ElemType> node) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        return node.rightChild;
    }

    public Node<ElemType> getLeftSibling(Node<ElemType> node) {
        Node<ElemType> root = getParent(node);
        if (root == null) {
            return null;
        }
        if (root.rightChild == node) {
            return root.leftChild;
        }
        return null;
    }

    public Node<ElemType> getRightSibling(Node<ElemType> node) {
        Node<ElemType> root = getParent(node);
        if (root == null) {
            return null;
        }
        if (root.leftChild == node) {
            return root.rightChild;
        }
        return null;
    }

    public Node<ElemType> getParent(Node<ElemType> node) {
        return getParent(root, node);
    }

    public Node<ElemType> getParent(Node<ElemType> root, Node<ElemType> node) {
        if (root == null) {
            return null;
        }
        if (root.leftChild == node || root.rightChild == node) {
            return root;
        }
        Node<ElemType> temp = getParent(root.leftChild, node);
        if (temp != null) {
            return temp;
        }
        temp = getParent(root.rightChild, node);
        return temp;
    }

    public void insertLeftChild(Node<ElemType> node, ElemType elem) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        Node<ElemType> p = new Node<>(elem);
        p.leftChild = node.leftChild;
        node.leftChild = p;
    }

    public void insertRightChild(Node<ElemType> node, ElemType elem) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        Node<ElemType> p = new Node<>(elem);
        p.rightChild = node.rightChild;
        node.rightChild = p;
    }

    public void clear() {
        clear(root);
    }

    public void clear(Node<ElemType> root) {
        if (root == null) {
            return;
        }
        clear(root.leftChild);
        clear(root.rightChild);
    }

    public void deleteLeftChild(Node<ElemType> node) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        clear(node.leftChild);
    }

    public void deleteRightChild(Node<ElemType> node) throws NotExistException {
        if (node == null) {
            throw new NotExistException("结点不存在");
        }
        clear(node.rightChild);
    }

    public int getHeight() {
        return getHeight(root);
    }

    public int getHeight(Node<ElemType> root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.leftChild);
        int rightHeight = getNodeNumber(root.rightChild);
        return 1 + (Math.max(leftHeight, rightHeight));
    }

    public int getWidth() {
        try {
            Queue<Node<ElemType>> queue = new LinkQueue<>();
            int maxWidth = 0;
            int number = 0;
            if (root != null) {
                queue.enQueue(root);
                maxWidth = 1;
                number = 1;
            }
            while (!queue.isEmpty()) {
                int nextNumber = 0;
                for (int i = 0; i < number; i++) {
                    Node<ElemType> temp = queue.getHead();
                    queue.deQueue();
                    if (temp.leftChild != null) {
                        queue.enQueue(temp.leftChild);
                        nextNumber++;
                    }
                    if (temp.rightChild != null) {
                        queue.enQueue(temp.rightChild);
                        nextNumber++;
                    }
                }
                number = nextNumber;
                if (nextNumber > maxWidth) {
                    maxWidth = nextNumber;
                }
            }
            return maxWidth;
        } catch (BaseException ignored) {
        }
        return 0;
    }

    public void getMirror() {
        getMirror(root);
    }

    public void getMirror(Node<ElemType> root) {
        if (root == null || (root.leftChild == null && root.rightChild == null)) {
            return;
        }
        Node<ElemType> temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
        if (root.leftChild != null) {
            getMirror(root.leftChild);
        }
        if (root.rightChild != null) {
            getMirror(root.rightChild);
        }
    }
}