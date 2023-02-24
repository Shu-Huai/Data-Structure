package shuhuai.datastructure.huffmantree;

import shuhuai.datastructure.exceptions.NotExistException;
import shuhuai.datastructure.string.String;

@SuppressWarnings({"unused", "unchecked"})
public class HuffmanTree<ElemType> {
    protected Node[] nodes;
    protected ElemType[] leafChars;
    protected String[] leafCharCodes;

    public HuffmanTree(ElemType[] elems, double[] weights) {
        nodes = new Node[2 * elems.length - 1];
        leafChars = (ElemType[]) new Object[elems.length];
        leafCharCodes = new String[elems.length];
        for (int i = 0; i < leafCharCodes.length; i++) {
            leafCharCodes[i] = new String("");
        }
        for (int i = 0; i < elems.length; i++) {
            nodes[i].weight = weights[i];
            nodes[i].leftChild = -1;
            nodes[i].rightChild = -1;
            nodes[i].parent = -1;
            leafChars[i] = elems[i];
        }
        for (int i = elems.length; i < 2 * elems.length - 1; i++) {
            int[] result = select(i);
            int rootA = result[0];
            int rootB = result[1];
            if (nodes[rootA].weight < nodes[rootB].weight) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            nodes[rootA].parent = i;
            nodes[rootB].parent = i;
            nodes[i].leftChild = rootA;
            nodes[i].rightChild = rootB;
            nodes[i].parent = -1;
            nodes[i].weight = nodes[rootA].weight + nodes[rootB].weight;
        }
        for (int i = 0; i < elems.length; i++) {
            int current = i;
            int parent = nodes[current].parent;
            while (parent != -1) {
                if (nodes[parent].leftChild == current) {
                    leafCharCodes[i] = new String("0" + leafCharCodes[i]);
                } else {
                    leafCharCodes[i] = new String("1" + leafCharCodes[i]);
                }
                current = parent;
                parent = nodes[current].parent;
            }
        }
    }

    public int[] select(int number) {
        int rootA = -1;
        int rootB = -1;
        for (int i = 0; i < number; i++) {
            if (nodes[i].parent == -1) {
                if (rootA == -1) {
                    rootA = i;
                } else if (nodes[i].weight < nodes[rootA].weight) {
                    rootB = rootA;
                    rootA = i;
                } else if (rootB == -1 || nodes[i].weight < nodes[rootB].weight) {
                    rootB = i;
                }
            }
        }
        return new int[]{rootA, rootB};
    }

    public String encode(ElemType ch) throws NotExistException {
        for (int i = 0; i < leafChars.length; i++) {
            if (leafChars[i] == ch) {
                return leafCharCodes[i];
            }
        }
        throw new NotExistException("字符不存在");
    }

    public String decode(String strCode) throws NotExistException {
        String result = new String();
        int parent = 2 * leafChars.length - 2;
        for (int i = 0; i < strCode.getLength(); i++) {
            if (strCode.getChar(i) == '0') {
                parent = nodes[parent].leftChild;
            } else {
                parent = nodes[parent].rightChild;
            }
            if (nodes[parent].leftChild == -1 && nodes[parent].rightChild == -1) {
                result = result.append((String) leafChars[parent]);
                parent = 2 * leafChars.length - 2;
            }
        }
        if (parent != 2 * leafChars.length - 2) {
            throw new NotExistException("字符不存在");
        }
        return result;
    }
}
