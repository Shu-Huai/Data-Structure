package shuhuai.datastructure.language;

import org.junit.Test;

@SuppressWarnings("unused")
public class LanguageTest {
    public void changeValue(final Node node) {
        node.copy(new Node(2));
    }

    @Test
    public void languageTest() {
        Node node = new Node(1);
        System.out.println(node);
        changeValue(node);
        System.out.println(node);
    }
}

@SuppressWarnings("unused")
class Node {
    protected Integer a;

    public Node(Integer a) {
        this.a = a;
    }

    public Node(Node node) {
        a = node.a;
    }

    public void copy(Node node) {
        a = node.a;
    }

    public String toString() {
        return a.toString();
    }
}
