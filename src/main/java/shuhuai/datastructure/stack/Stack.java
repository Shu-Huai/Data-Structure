package shuhuai.datastructure.stack;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;

public interface Stack<ElemType extends Comparable<? super ElemType>> {
    int getLength();

    boolean isEmpty();

    void clear();

    void traverse();

    void push(ElemType elem) throws OverFlowException;

    int getTop();

    ElemType getTopElem() throws UnderFlowException;

    ElemType pop() throws UnderFlowException;
}
