package shuhuai.datastructure.binarytree.sequencebinarytree;

import shuhuai.datastructure.binarytree.BinaryTree;
import shuhuai.datastructure.exceptions.BaseException;
import shuhuai.datastructure.exceptions.NotExistException;
import shuhuai.datastructure.queue.Queue;
import shuhuai.datastructure.queue.linkqueue.LinkQueue;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class SequenceBinaryTree<ElemType> implements BinaryTree<ElemType> {
    protected int maxSize;
    protected int[] tag;
    protected ElemType[] elems;

    public SequenceBinaryTree() {
        this.maxSize = 1000;
        elems = (ElemType[]) new Object[this.maxSize];
        tag = new int[this.maxSize];
        for (int i = 0; i < this.maxSize; i++) {
            tag[i] = 0;
        }
    }

    public SequenceBinaryTree(int maxSize) {
        this.maxSize = maxSize;
        elems = (ElemType[]) new Object[this.maxSize];
        tag = new int[this.maxSize];
        for (int i = 0; i < this.maxSize; i++) {
            tag[i] = 0;
        }
    }

    public SequenceBinaryTree(ElemType elem, int maxSize) {
        this.maxSize = maxSize;
        elems = (ElemType[]) new Object[this.maxSize];
        tag = new int[maxSize];
        for (int i = 1; i < maxSize; i++) {
            tag[i] = 0;
        }
        tag[0] = 1;
        elems[0] = elem;
    }

    public SequenceBinaryTree(SequenceBinaryTree<ElemType> tree) {
        maxSize = tree.maxSize;
        elems = (ElemType[]) new Object[maxSize];
        tag = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            elems[i] = tree.elems[i];
            tag[i] = tree.tag[i];
        }
    }

    public SequenceBinaryTree(ElemType[] elems, int[] tags, int maxSize) {
        this.maxSize = maxSize;
        this.elems = (ElemType[]) new Object[maxSize];
        this.tag = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            this.elems[i] = elems[i];
            this.tag[i] = tags[i];
        }
    }

    public int getRoot() {
        if (tag[0] == 1) {
            return 0;
        }
        return -1;
    }

    public ElemType getElem(int node) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("节点不存在");
        }
        return elems[node];
    }

    public void setElem(int node, ElemType elem) throws NotExistException {
        if (isNodeEmpty(node)) {
            throw new NotExistException("节点不存在");
        } else {
            elems[node] = elem;
        }
    }

    public boolean isEmpty() {
        return isNodeEmpty(0);
    }

    public boolean isNodeEmpty(int node) {
        return node < 0 || node >= maxSize || tag[node] == 0;
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
        preOrderTraverse(0, output);
        output.apply("\n");
    }

    public void preOrderTraverse(int root, Function<String, Void> output) {
        if (!isNodeEmpty(root)) {
            output.apply(elems[root] + " ");
            preOrderTraverse(getLeftChild(root), output);
            preOrderTraverse(getRightChild(root), output);
        }
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
        inOrderTraverse(0, output);
        output.apply("\n");
    }

    public void inOrderTraverse(int root, Function<String, Void> output) {
        if (!isNodeEmpty(root)) {
            inOrderTraverse(getLeftChild(root), output);
            output.apply(elems[root] + " ");
            inOrderTraverse(getRightChild(root), output);
        }
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
        postOrderTraverse(0, output);
        output.apply("\n");
    }

    public void postOrderTraverse(int r, Function<String, Void> output) {
        if (!isNodeEmpty(r)) {
            postOrderTraverse(getLeftChild(r), output);
            output.apply(elems[r] + " ");
            postOrderTraverse(getRightChild(r), output);
        }
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
        if (isNodeEmpty(0)) {
            return;
        }
        try {
            Queue<Integer> queue = new LinkQueue<>();
            queue.enQueue(0);
            while (!queue.isEmpty()) {
                int node = queue.deQueue();
                output.apply(elems[node] + " ");
                if (!isNodeEmpty(getLeftChild(node))) {
                    queue.enQueue(getLeftChild(node));
                }
                if (!isNodeEmpty(getRightChild(node))) {
                    queue.enQueue(getRightChild(node));
                }
            }
            output.apply("\n");
        } catch (BaseException ignored) {
        }
    }

    @Override
    public int getNodeNumber() {
        return getNodeNumber(0);
    }

    public int getNodeNumber(int root) {
        if (isNodeEmpty(root)) {
            return 0;
        }
        return 1 + getNodeNumber(getLeftChild(root)) + getNodeNumber(getRightChild(root));
    }

    public int getLeftChild(final int p) {
        if (2 * p + 1 < maxSize && tag[2 * p + 1] == 1) {
            return 2 * p + 1;
        }
        return -1;
    }

    public int getRightChild(final int p) {
        if (2 * p + 2 < maxSize && tag[2 * p + 2] == 1) {
            return 2 * p + 2;
        }
        return -1;
    }

    public int getLeftSibling(final int node) {
        if (node % 2 == 1 || node == 0 || tag[node - 1] == 0) {
            return -1;
        }
        return node - 1;
    }

    public int getRightSibling(final int node) {
        if (node % 2 == 0 || node + 1 >= maxSize || tag[node + 1] == 0) {
            return -1;
        }
        return node + 1;
    }

    public int getParent(final int node) {
        return node > 0 ? (node - 1) / 2 : -1;
    }

    public int find(final ElemType elem) {
        if (isNodeEmpty(0)) {
            return -1;
        }
        try {
            Queue<Integer> queue = new LinkQueue<>();
            queue.enQueue(0);
            while (!queue.isEmpty()) {
                int node = queue.deQueue();
                if (elems[node] == elem) {
                    return node;
                }
                if (!isNodeEmpty(getLeftChild(node)))
                    queue.enQueue(getLeftChild(node));
                if (!isNodeEmpty(getRightChild(node)))
                    queue.enQueue(getRightChild(node));
            }
        } catch (BaseException ignored) {
        }
        return -1;
    }

    public void insertLeftChild(int node, final ElemType elem) {
        if (isNodeEmpty(node)) {
            return;
        }
        if (2 * node + 1 < maxSize && 2 * node + 1 > 0 && tag[2 * node + 1] == 0) {
            elems[2 * node + 1] = elem;
            tag[2 * node + 1] = 1;
        }
    }

    public void insertRightChild(int node, final ElemType elem) {
        if (isNodeEmpty(node)) {
            return;
        }
        if (2 * node + 2 < maxSize && 2 * node + 2 > 0 && tag[2 * node + 2] == 0) {
            elems[2 * node + 2] = elem;
            tag[2 * node + 2] = 1;
        }
    }

    @Override
    public void clear() {
        clear(0);
    }

    public void clear(int node) {
        if (node >= 0 && node < maxSize && !isNodeEmpty(node)) {
            clear(getLeftChild(node));
            clear(getRightChild(node));
            tag[node] = 0;
        }
    }

    public void deleteLeftChild(int node) {
        if (isNodeEmpty(node)) {
            return;
        }
        clear(getLeftChild(node));
    }

    public void deleteRightChild(int node) {
        if (isNodeEmpty(node)) {
            return;
        }
        clear(getRightChild(node));
    }

    @Override
    public int getHeight() {
        return getHeight(0);
    }

    public int getHeight(int node) {
        if (isNodeEmpty(node)) {
            return 0;
        }
        int leftHeight, rightHeight;
        leftHeight = getHeight(getLeftChild(node));
        rightHeight = getHeight(getRightChild(node));
        return (leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1);
    }
}