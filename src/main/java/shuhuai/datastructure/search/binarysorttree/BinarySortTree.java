package shuhuai.datastructure.search.binarysorttree;

import shuhuai.datastructure.binarytree.LinkBinaryTree.LinkBinaryTree;
import shuhuai.datastructure.binarytree.LinkBinaryTree.Node;
import shuhuai.datastructure.exceptions.BaseException;
import shuhuai.datastructure.exceptions.ExistException;
import shuhuai.datastructure.exceptions.NotExistException;
import shuhuai.datastructure.linearlist.LinearList;
import shuhuai.datastructure.linearlist.linklist.LinkList;
import shuhuai.datastructure.search.Search;
import shuhuai.datastructure.stack.Stack;
import shuhuai.datastructure.stack.linkstack.LinkStack;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public class BinarySortTree<ElemType extends Comparable<ElemType>> extends LinkBinaryTree<ElemType> implements Search<ElemType> {
    public boolean isBinarySortTree() {
        try {
            if (root == null) {
                return true;
            }
            LinearList<ElemType> result = new LinkList<>();
            Stack<Node<ElemType>> stack = new LinkStack<>();
            Node<ElemType> p = root;
            while (p != null || !stack.isEmpty()) {
                if (p != null) {
                    stack.push(p);
                    p = p.getLeftChild();
                } else {
                    p = stack.getTop();
                    result.appendElem(p.getElem());
                    stack.pop();
                    p = p.getRightChild();
                }
            }
            for (int i = 1; i < result.getLength(); i++) {
                if (result.getElem(i).compareTo(result.getElem(i - 1)) < 0) {
                    return false;
                }
            }
        } catch (BaseException ignored) {
        }
        return false;
    }

    public void insertElem(ElemType elem) throws ExistException {
        Node<ElemType> parentNode = new Node<>();
        if (search(elem, parentNode) != null) {
            throw new ExistException("元素存在");
        }
        Node<ElemType> p = new Node<>(elem);
        if (isEmpty()) {
            root = p;
        } else if (elem.compareTo(parentNode.getElem()) < 0) {
            parentNode.setLeftChild(p);
        } else {
            parentNode.setLeftChild(p);
        }
    }

    public void findAndInsert(ElemType elem) throws ExistException {
        if (root == null) {
            root = new Node<>(elem);
            return;
        }
        Node<ElemType> p = root;
        Node<ElemType> parentNode = null;
        while (p != null) {
            if (elem == p.getElem()) {
                throw new ExistException("元素存在");
            }
            parentNode = p;
            if (elem.compareTo(p.getElem()) < 0) {
                p = p.getLeftChild();
            } else {
                p = p.getRightChild();
            }
        }
        p = new Node<>(elem);
        if (elem.compareTo(parentNode.getElem()) < 0) {
            parentNode.setLeftChild(p);
        } else {
            parentNode.setRightChild(p);
        }
    }

    public void deleteNode(Node<ElemType> node) {
        if (node.getLeftChild() == null) {
            node.copy(node.getRightChild());
        } else if (node.getRightChild() == null) {
            node.copy(node.getLeftChild());
        } else {
            Node<ElemType> tempParentNode = node;
            Node<ElemType> tempNode = node.getLeftChild();
            while (tempNode.getRightChild() != null) {
                tempParentNode = tempNode;
                tempNode = tempNode.getRightChild();
            }
            node.setElem(tempNode.getElem());
            if (tempParentNode.getRightChild().equals(tempNode)) {
                if (tempParentNode.getRightChild().getLeftChild() == null &&
                        tempParentNode.getRightChild().getRightChild() == null) {
                    tempParentNode.setRightChild(null);
                } else {
                    deleteNode(tempParentNode.getRightChild());
                }
            } else {
                if (tempParentNode.getLeftChild().getLeftChild() == null &&
                        tempParentNode.getLeftChild().getRightChild() == null) {
                    tempParentNode.setLeftChild(null);
                } else {
                    deleteNode(tempParentNode.getLeftChild());
                }
            }
        }
    }

    public void deleteElem(ElemType key) throws NotExistException {
        Node<ElemType> p = search(key, new Node<>());
        if (p == null) {
            throw new NotExistException("元素不存在");
        }
        deleteNode(p);
    }

    public Node<ElemType> search(ElemType key) {
        Node<ElemType> parentNode = new Node<>();
        return search(key, parentNode);
    }

    public Node<ElemType> search(ElemType[] elems, ElemType key) {
        try {
            for (ElemType elem : elems) {
                insertElem(elem);
            }
            return search(key);
        } catch (BaseException ignored) {
        }
        return null;
    }

    public Node<ElemType> search(ElemType key, Node<ElemType> parentNode) {
        Node<ElemType> p = root;
        while (p != null && p.getElem() != key) {
            if (key.compareTo(p.getElem()) < 0) {
                parentNode.copy(p);
                p = p.getLeftChild();
            } else {
                parentNode.copy(p);
                p = p.getRightChild();
            }
        }
        return p;
    }

    public void getElemsAbove(ElemType key, Function<String, Void> output) {
        try {
            if (root == null) {
                return;
            }
            LinearList<ElemType> result = new LinkList<>();
            Stack<Node<ElemType>> s = new LinkStack<>();
            Node<ElemType> p = root;
            while (p != null || !s.isEmpty()) {
                if (p != null) {
                    s.push(p);
                } else {
                    p = s.getTop();
                    result.appendElem(p.getElem());
                    s.pop();
                }
                p = p.getRightChild();
            }
            for (int i = result.getLength() - 1; i >= 0; i--) {
                if (result.getElem(i).compareTo(key) >= 0) {
                    output.apply(result.getElem(i) + " ");
                } else {
                    break;
                }
            }
        } catch (BaseException ignored) {
        }
    }
}