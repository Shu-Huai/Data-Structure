package shuhuai.datastructure.linearlist;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.RangeException;

@SuppressWarnings({"unused"})
public interface LinearList<ElemType> {
    int getLength();

    void clear();

    boolean isEmpty();

    void traverse();

    void display();

    void appendElem(ElemType elem) throws OverFlowException;

    void insertElem(ElemType elem, int index) throws OverFlowException, RangeException;

    void deleteElem(int index) throws RangeException;

    void setElem(ElemType elem, int index) throws RangeException;

    ElemType getElem(int index) throws RangeException;

    int findElem(ElemType elem);

    void copy(LinearList<ElemType> list);
}