package shuhuai.datastructure.array;

@SuppressWarnings({"unused", "unchecked"})
public class Array<ElemType> {
    protected ElemType[] elems;
    protected int length;

    public Array(int capacity) {
        elems = (ElemType[]) new Object[capacity];
        length = 0;
    }

    public ElemType get(int index) {
        return elems[index];
    }

    public void set(int index, ElemType elem) {
        elems[index] = elem;
    }

    public int getCapacity() {
        return elems.length;
    }

    public int getLength() {
        return length;
    }

    public void append(ElemType elem) {
        elems[length++] = elem;
    }
}