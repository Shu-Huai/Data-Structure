package shuhuai.datastructure.queue;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public interface Queue<ElemType> {
    int getLength();

    boolean isEmpty();

    void clear();

    void traverse();

    void traverse(Function<String, Void> output);

    ElemType deQueue() throws UnderFlowException;

    ElemType getHead() throws UnderFlowException;

    void enQueue(ElemType elem) throws OverFlowException;
}