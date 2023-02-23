package shuhuai.datastructure.binarytree.LinkBinaryTree;

import shuhuai.datastructure.binarytree.BinaryTree;
import shuhuai.datastructure.exceptions.BaseException;
import shuhuai.datastructure.exceptions.NotExistException;
import shuhuai.datastructure.queue.Queue;
import shuhuai.datastructure.queue.linkqueue.LinkQueue;

import java.util.function.Function;

@SuppressWarnings({"unused"})
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
        return isNodeEmpty(root);
    }

    public boolean isNodeEmpty(Node<ElemType> node) {
        return node == null;
    }

    public Node<ElemType> copy(Node<ElemType> node) {
        if (isNodeEmpty(node)) {
            return null;
        }
        Node<ElemType> result = new Node<>(node.elem);
        result.leftChild = copy(node.leftChild);
        result.rightChild = copy(node.rightChild);
        return result;
    }

    public ElemType getElem(Node<ElemType> node) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        return node.elem;
    }

    public void setElem(Node<ElemType> node, ElemType elem) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        node.elem = elem;
    }

    public Node<ElemType> find(ElemType elem) {
        return find(root, elem);
    }

    public Node<ElemType> find(Node<ElemType> root, ElemType elem) {
        if (isNodeEmpty(root)) {
            return null;
        }
        if (root.elem == elem) {
            return root;
        }
        Node<ElemType> p = find(root.leftChild, elem);
        if (!isNodeEmpty(p)) {
            return p;
        }
        p = find(root.rightChild, elem);
        return p;
    }

    @Override
    public void preOrderTraverse() {
        preOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    @Override
    public void preOrderTraverse(Function<String, Void> output) {
        preOrderTraverse(root, output);
        output.apply("\n");
    }

    public void preOrderTraverse(Node<ElemType> root, Function<String, Void> output) {
        if (isNodeEmpty(root)) {
            return;
        }
        output.apply(root.elem + " ");
        preOrderTraverse(root.leftChild, output);
        preOrderTraverse(root.rightChild, output);
    }

    @Override
    public void inOrderTraverse() {
        inOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    @Override
    public void inOrderTraverse(Function<String, Void> output) {
        inOrderTraverse(root, output);
        output.apply("\n");
    }

    public void inOrderTraverse(Node<ElemType> root, Function<String, Void> output) {
        if (isNodeEmpty(root)) {
            return;
        }
        preOrderTraverse(root.leftChild, output);
        output.apply(root.elem + " ");
        preOrderTraverse(root.rightChild, output);
    }

    @Override
    public void postOrderTraverse() {
        postOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    @Override
    public void postOrderTraverse(Function<String, Void> output) {
        postOrderTraverse(root, output);
        output.apply("\n");
    }

    public void postOrderTraverse(Node<ElemType> root, Function<String, Void> output) {
        if (isNodeEmpty(root)) {
            return;
        }
        postOrderTraverse(root.leftChild, output);
        postOrderTraverse(root.rightChild, output);
        output.apply(root.elem + " ");
    }

    @Override
    public void levelOrderTraverse() {
        levelOrderTraverse(value -> {
            System.out.print(value);
            return null;
        });
        System.out.print("\n");
    }

    @Override
    public void levelOrderTraverse(Function<String, Void> output) {
        if (isNodeEmpty(root)) {
            return;
        }
        try {
            Queue<Node<ElemType>> queue = new LinkQueue<>();
            queue.enQueue(root);
            while (!queue.isEmpty()) {
                Node<ElemType> p = queue.deQueue();
                output.apply(p.elem + " ");
                if (!isNodeEmpty(p.leftChild)) {
                    queue.enQueue(p.leftChild);
                }
                if (!isNodeEmpty(p.rightChild)) {
                    queue.enQueue(p.rightChild);
                }
            }
            output.apply("\n");
        } catch (BaseException ignored) {
        }
    }

    @Override
    public int getNodeNumber() {
        return getNodeNumber(root);
    }

    public int getNodeNumber(Node<ElemType> root) {
        if (isNodeEmpty(root)) {
            return 0;
        }
        return 1 + getNodeNumber(root.leftChild) + getNodeNumber(root.rightChild);
    }

    public Node<ElemType> getLeftChild(Node<ElemType> node) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        return node.leftChild;
    }

    public Node<ElemType> getRightChild(Node<ElemType> node) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        return node.rightChild;
    }

    public Node<ElemType> getLeftSibling(Node<ElemType> node) {
        Node<ElemType> root = getParent(node);
        if (isNodeEmpty(node)) {
            return null;
        }
        if (root.rightChild == node) {
            return root.leftChild;
        }
        return null;
    }

    public Node<ElemType> getRightSibling(Node<ElemType> node) {
        Node<ElemType> root = getParent(node);
        if (isNodeEmpty(node)) {
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
        if (isNodeEmpty(root)) {
            return null;
        }
        if (root.leftChild == node || root.rightChild == node) {
            return root;
        }
        Node<ElemType> temp = getParent(root.leftChild, node);
        if (!isNodeEmpty(temp)) {
            return temp;
        }
        temp = getParent(root.rightChild, node);
        return temp;
    }

    public void insertLeftChild(Node<ElemType> node, ElemType elem) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        Node<ElemType> p = new Node<>(elem);
        p.leftChild = node.leftChild;
        node.leftChild = p;
    }

    public void insertRightChild(Node<ElemType> node, ElemType elem) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        Node<ElemType> p = new Node<>(elem);
        p.rightChild = node.rightChild;
        node.rightChild = p;
    }

    @Override
    public void clear() {
        clear(root);
    }

    public void clear(Node<ElemType> root) {
        if (isNodeEmpty(root)) {
            return;
        }
        clear(root.leftChild);
        clear(root.rightChild);
    }

    public void deleteLeftChild(Node<ElemType> node) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        clear(node.leftChild);
    }

    public void deleteRightChild(Node<ElemType> node) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("结点不存在");
        }
        clear(node.rightChild);
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    public int getHeight(Node<ElemType> root) {
        if (isNodeEmpty(root)) {
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
            if (!isNodeEmpty(root)) {
                queue.enQueue(root);
                maxWidth = 1;
                number = 1;
            }
            while (!queue.isEmpty()) {
                int nextNumber = 0;
                for (int i = 0; i < number; i++) {
                    Node<ElemType> temp = queue.getHead();
                    queue.deQueue();
                    if (!isNodeEmpty(temp.leftChild)) {
                        queue.enQueue(temp.leftChild);
                        nextNumber++;
                    }
                    if (!isNodeEmpty(temp.rightChild)) {
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
        if (isNodeEmpty(root) || (isNodeEmpty(root.leftChild) && isNodeEmpty(root.rightChild))) {
            return;
        }
        Node<ElemType> temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
        if (!isNodeEmpty(root.leftChild)) {
            getMirror(root.leftChild);
        }
        if (!isNodeEmpty(root.rightChild)) {
            getMirror(root.rightChild);
        }
    }
}