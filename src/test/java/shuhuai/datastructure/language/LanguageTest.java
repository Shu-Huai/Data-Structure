package shuhuai.datastructure.language;

import org.junit.Test;

@SuppressWarnings("unused")
public class LanguageTest {
/*    public void changeValue(final Node node) {
        node.copy(new Node(2));
    }*/

    @Test
    public void languageTest() {
/*        Node node = new Node(1);
        System.out.println(node);
        changeValue(node);
        System.out.println(node);*/
        Node node = new Node(new Sub(10001));
        node.getA().b = 10002;
        System.out.println(node.a.b);
    }
}

@SuppressWarnings("unused")
class Node {
    protected Sub a;

    public Node(Sub a) {
        this.a = a;
    }

    public Node(Node node) {
        a = node.a;
    }

    public Sub getA() {
        return a;
    }

    public void copy(Node node) {
        a = node.a;
    }

    public String toString() {
        return a.toString();
    }
}

class Sub {
    protected Integer b;

    public Sub(Integer b) {
        this.b = b;
    }
}
