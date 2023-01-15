package shuhuai.datastructure.matrix;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.RangeException;

public interface Matrix<ElemType> {
    void clear();

    int getRowCount();

    int getColCount();

    int getElemCount();

    void setElem(int row, int col, ElemType elem) throws RangeException, OverFlowException;

    ElemType getElem(int row, int col) throws RangeException;

    Matrix<ElemType> simpleTranspose();

    Matrix<ElemType> fastTranspose();
}