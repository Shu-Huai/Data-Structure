package shuhuai.datastructure.linearlist.sequencelist;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.RangeException;
import shuhuai.datastructure.linearlist.LinearList;
import shuhuai.datastructure.linearlist.linklist.Node;

import java.util.Objects;

public class SequenceList<ElemType extends Comparable<? super ElemType>> implements LinearList<ElemType> {
    protected ElemType[] elems;
    protected Integer length;
    protected Integer capacity;

    @SuppressWarnings("unchecked")
    public SequenceList() {
        this.capacity = 1000;
        this.elems = (ElemType[]) new Comparable[capacity];
        this.length = 0;
    }

    @SuppressWarnings("unchecked")
    public SequenceList(Integer capacity) {
        this.capacity = capacity;
        elems = (ElemType[]) new Comparable[this.capacity];
        length = 0;
    }

    public SequenceList(ElemType[] elems, Integer capacity) {
        this.elems = elems.clone();
        this.length = elems.length;
        this.capacity = capacity;
    }

    public SequenceList(SequenceList<ElemType> sequenceList) {
        this.elems = sequenceList.elems.clone();
        this.length = sequenceList.length;
        this.capacity = sequenceList.capacity;
    }

    public ElemType[] getElems() {
        return elems;
    }

    public void setElems(ElemType[] elems) {
        this.elems = elems;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
    public void traverse() {
        for (int i = 0; i < length; i++) {
            System.out.print(elems[i]);
            if (i < length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    @Override
    public void display() {
        for (int i = 0; i < length; i++) {
            System.out.print(elems[i]);
            if (i < length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println("长度是：" + length);
    }

    @Override
    public void appendElem(ElemType elem) throws OverFlowException {
        if (Objects.equals(length, capacity)) {
            throw new OverFlowException("空间已满");
        }
        elems[length++] = elem;
    }

    @Override
    public void insertElem(ElemType elem, int index) throws OverFlowException, RangeException {
        if (Objects.equals(length, capacity)) {
            throw new OverFlowException("空间已满");
        }
        if (index < 0 || index > length) {
            throw new RangeException("下标越界");
        }
        System.arraycopy(elems, index, elems, index + 1, length - index);
        elems[index] = elem;
        length++;
    }

    @Override
    public void deleteElem(int index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        System.arraycopy(elems, index + 1, elems, index, length - 1 - index);
        length--;
    }

    @Override
    public void setElem(ElemType elem, int index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        elems[index] = elem;
    }

    @Override
    public ElemType getElem(int index) throws RangeException {
        if (index < 0 || index >= length) {
            throw new RangeException("下标越界");
        }
        return elems[index];
    }


    @Override
    public int findElem(ElemType elem) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(elems[i], elem)) {
                return i;
            }
        }
        return -1;
    }

    public void swap(int firstIndex, int secondIndex) throws RangeException {
        if (firstIndex < 0 || firstIndex >= length || secondIndex < 0 || secondIndex >= length) {
            throw new RangeException("下标越界");
        }
        ElemType temp = elems[firstIndex];
        elems[firstIndex] = elems[secondIndex];
        elems[secondIndex] = temp;
    }

    public void reverse() {
        for (int i = 0; i < length / 2; i++) {
            try {
                swap(i, length - 1 - i);
            } catch (RangeException ignored) {
            }
        }
    }


    public void deleteBetween(ElemType minElem, ElemType maxElem) throws RangeException {
        if (minElem.compareTo(maxElem) > 0) {
            throw new RangeException("最小值不能大于最大值");
        }
        for (int i = 0; i < length; i++) {
            if (elems[i].compareTo(minElem) > 0 && elems[i].compareTo(maxElem) < 0) {
                elems[i] = getElem(i + 1);
                deleteElem(i + 1);
                i--;
            }
        }
    }

    public void deleteRepeat() {
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (Objects.equals(elems[i], elems[j])) {
                    try {
                        elems[j] = getElem(j + 1);
                        deleteElem(j + 1);
                    } catch (RangeException ignored) {
                    }
                    j--;
                }
            }
        }
    }

    public void sort() {
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (elems[i].compareTo(elems[j]) > 0) {
                    try {
                        swap(i, j);
                    } catch (RangeException ignored) {
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(elems[i]);
            if (i < length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}