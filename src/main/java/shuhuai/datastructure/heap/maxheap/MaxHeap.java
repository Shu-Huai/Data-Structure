package shuhuai.datastructure.heap.maxheap;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;
import shuhuai.datastructure.heap.Heap;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class MaxHeap<ElemType extends Comparable<Object>> implements Heap<ElemType> {
    protected ElemType[] elems;
    protected int length;
    protected int capacity;

    public MaxHeap() {
        capacity = 1000;
        length = 0;
        elems = (ElemType[]) new Comparable[capacity];
    }

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        length = 0;
        elems = (ElemType[]) new Comparable[capacity];
    }

    public MaxHeap(ElemType[] elems, int length) {
        capacity = 1000;
        this.length = length;
        this.elems = (ElemType[]) new Comparable[capacity];
    }

    public MaxHeap(ElemType[] elems, int length, int capacity) {
        this.capacity = capacity;
        this.length = length;
        this.elems = (ElemType[]) new Comparable[capacity];
    }

    @Override
    public void filterDown(final int start) {
        int i = start;
        int j = i * 2 + 1;
        ElemType temp = elems[i];
        while (j <= length - 1) {
            if (j < length - 1 && elems[j].compareTo(elems[j + 1]) < 0) {
                j++;
            }
            if (temp.compareTo(elems[j]) >= 0) {
                break;
            } else {
                elems[i] = elems[j];
                i = j;
                j = 2 * j + 1;
            }
        }
        elems[i] = temp;
    }

    @Override
    public void filterUp(final int end) {
        int j = end;
        int i = (j - 1) / 2;
        ElemType temp = elems[j];
        while (j > 0) {
            if (elems[i].compareTo(temp) >= 0) {
                break;
            } else {
                elems[j] = elems[i];
                j = i;
                i = (j - 1) / 2;
            }
            elems[j] = temp;
        }
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean isFull() {
        return length == capacity;
    }

    @Override
    public void traverse() {
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    @Override
    public void traverse(Function<String, Void> output) {
        for (ElemType elem : elems) {
            output.apply(elem + " ");
        }
    }

    @Override
    public void insertElem(ElemType elem) throws OverFlowException {
        if (isFull()) {
            throw new OverFlowException("堆满");
        }
        elems[length] = elem;
        filterUp(length);
        length++;
    }

    @Override
    public void deleteTop() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("堆空");
        }
        elems[0] = elems[--length];
        filterDown(0);
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public ElemType getTop() throws UnderFlowException {
        if (isEmpty()) {
            throw new UnderFlowException("堆空");
        }
        return elems[0];
    }
}