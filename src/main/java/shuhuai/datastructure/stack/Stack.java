package shuhuai.datastructure.stack;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.UnderFlowException;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public interface Stack<ElemType> {
    int getLength();

    boolean isEmpty();

    void clear();

    void traverse();

    void traverse(Function<String, Void> output);

    void push(ElemType elem) throws OverFlowException;

    ElemType getTop() throws UnderFlowException;

    ElemType pop() throws UnderFlowException;
}
