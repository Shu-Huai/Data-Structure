package shuhuai.datastructure.string;

import shuhuai.datastructure.exceptions.RangeException;
import shuhuai.datastructure.linearlist.linklist.LinkList;

@SuppressWarnings({"unused"})
public class String {
    protected char[] elems;
    protected int length;

    public String() {
        length = 0;
        elems = null;
    }

    public String(String string) {
        length = string.length;
        elems = new char[length + 1];
        System.arraycopy(string.elems, 0, elems, 0, length);
        elems[length] = '\0';
    }

    public String(char[] chars) {
        length = chars.length;
        elems = new char[length + 1];
        System.arraycopy(chars, 0, elems, 0, length);
        elems[length] = '\0';
    }

    public String(LinkList<Character> linkList) {
        length = linkList.getLength();
        elems = new char[length + 1];
        for (int i = 0; i < length; i++) {
            try {
                elems[i] = linkList.getElem(i);
            } catch (RangeException e) {
                throw new RuntimeException(e);
            }
        }
        elems[length] = '\0';
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public char[] toChar() {
        char[] result = new char[length + 1];
        System.arraycopy(elems, 0, result, 0, length + 1);
        return result;
    }

    public char getChar(int index) {
        return elems[index];
    }

    public void traverse() {
        for (int i = 0; i < length; i++) {
            System.out.print(elems[i]);
        }
        System.out.println();
        System.out.println("长度是：" + getLength());
    }

    String copy() {
        return new String(this);
    }

    String copy(int length) throws RangeException {
        length = Math.min(length, this.length);
        char[] result = new char[length + 1];
        if (length >= 0) {
            System.arraycopy(elems, 0, result, 0, length);
        } else {
            throw new RangeException("范围错误");
        }
        result[length] = '\0';
        return new String(result);
    }

    String insert(String subString, int index) {
        char[] result = new char[length + subString.length + 1];
        System.arraycopy(elems, 0, result, 0, index);
        System.arraycopy(subString.elems, 0, result, index, subString.length);
        System.arraycopy(elems, index, result, index + subString.length, length - index);
        result[length + subString.length] = '\0';
        return new String(result);
    }

    String delete(int index, int length) throws RangeException {
        if (length < 0 || length > this.length) {
            throw new RangeException("范围错误");
        }
        if (index < 0 || index > this.length - length) {
            throw new RangeException("范围错误");
        }
        char[] result = new char[this.length - length + 1];
        System.arraycopy(elems, 0, result, 0, index);
        System.arraycopy(elems, index + length, result, index, this.length - index - length);
        result[this.length - length + 1] = '\0';
        return new String(result);
    }

    String subString(int index, int length) throws RangeException {
        if (length < 0 || length > this.length) {
            throw new RangeException("范围错误");
        }
        if (index < 0 || index > this.length - length) {
            throw new RangeException("范围错误");
        }
        char[] result = new char[length + 1];
        System.arraycopy(elems, index, result, 0, length);
        result[length] = '\0';
        return new String(result);
    }

    public int bfFind(String pattern) {
        return bfFind(pattern, 0);
    }

    public int bfFind(String pattern, int index) {
        int i = index;
        int j = 0;
        while (i < length && j < pattern.length && pattern.length - j <= length - i) {
            if (elems[i] == pattern.elems[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= pattern.length) {
            return i - j;
        }
        return -1;
    }

    protected int[] getFailure() {
        int j = 0;
        int k = -1;
        int[] failure = new int[length];
        failure[0] = -1;
        while (j < length - 1) {
            if (k == -1 || elems[k] == elems[j]) {
                failure[++j] = ++k;
            } else {
                k = failure[k];
            }
        }
        return failure;
    }

    public int kmpFind(String pattern) {
        return kmpFind(pattern, 0);
    }

    public int kmpFind(String pattern, int index) {
        int[] failure = pattern.getFailure();
        int i = index;
        int j = 0;
        while (i < length && j < pattern.length && pattern.length - j <= length - i) {
            if (elems[i] == pattern.elems[j]) {
                i++;
                j++;
            } else {
                j = failure[j];
            }
        }
        if (j >= pattern.length) {
            return i - j;
        }
        return -1;
    }
}