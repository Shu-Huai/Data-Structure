package shuhuai.datastructure.huffmantree;

@SuppressWarnings({"unused"})
public class Node {
    protected double weight;
    protected int parent;
    protected int leftChild;
    protected int rightChild;

    public Node() {
        weight = 0;
        parent = -1;
        leftChild = -1;
        rightChild = -1;
    }

    public Node(double weight) {
        this.weight = weight;
        parent = -1;
        leftChild = -1;
        rightChild = -1;
    }

    public Node(double weight, int parent, int leftChild, int rightChild) {
        this.weight = weight;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}