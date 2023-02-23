package shuhuai.datastructure.queue;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;

@SuppressWarnings({"unused"})
public interface Queue<ElemType> {
    int getLength();

    boolean isEmpty();

    void clear();

    void traverse();

    ElemType deQueue() throws UnderFlowException;

    ElemType getHead() throws UnderFlowException;

    void enQueue(ElemType elem) throws OverFlowException;
}