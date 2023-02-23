package shuhuai.datastructure.stack;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;

@SuppressWarnings({"unused"})
public interface Stack<ElemType extends Comparable<? super ElemType>> {
    int getLength();

    boolean isEmpty();

    void clear();

    void traverse();

    void push(ElemType elem) throws OverFlowException;

    ElemType getTop() throws UnderFlowException;

    ElemType pop() throws UnderFlowException;
}
