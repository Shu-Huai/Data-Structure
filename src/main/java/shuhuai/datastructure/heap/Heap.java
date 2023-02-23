package shuhuai.datastructure.heap;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;

import java.util.function.Function;

@SuppressWarnings("unused")
public interface Heap<ElemType> {
    void filterDown(final int start);

    void filterUp(final int end);

    void clear();

    boolean isEmpty();

    boolean isFull();

    void traverse();

    void traverse(Function<String, Void> output);

    void insertElem(final ElemType elem) throws OverFlowException;

    void deleteTop() throws UnderFlowException;

    int getLength();

    ElemType getTop() throws UnderFlowException;
}