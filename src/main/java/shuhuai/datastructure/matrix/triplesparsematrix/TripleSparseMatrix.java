package shuhuai.datastructure.matrix.triplesparsematrix;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.RangeException;
import shuhuai.datastructure.matrix.Matrix;

public class TripleSparseMatrix<ElemType> implements Matrix<ElemType> {
    protected Triple<ElemType>[] elems;
    protected int capacity;
    protected int rowCount;
    protected int colCount;
    protected int elemCount;

    @SuppressWarnings("unchecked")
    public TripleSparseMatrix(int rowCount, int colCount, int capacity) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.capacity = capacity;
        elems = new Triple[this.capacity];
        this.elemCount = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        rowCount = 0;
        colCount = 0;
        elemCount = 0;
        elems = new Triple[capacity];
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColCount() {
        return colCount;
    }

    @Override
    public int getElemCount() {
        return elemCount;
    }

    @Override
    public void setElem(int row, int col, ElemType elem) throws RangeException, OverFlowException {
        if (elemCount == capacity) {
            throw new OverFlowException("存储空间已满。");
        }
        if (row > rowCount || row < 0 || col > colCount || col < 0) {
            throw new RangeException("范围错误。");
        }
        if ((boolean) elem) {
            for (Triple<ElemType> item : elems) {
                if (item.row == row && item.col == col) {
                    item.elem = elem;
                    return;
                }
            }
            elems[elemCount++] = new Triple<>(row, col, elem);
        } else {
            for (int i = 0; i < elemCount; i++) {
                if (elems[i].row == row && elems[i].col == col) {
                    for (int j = i; j < elemCount - 1; j++) {
                        elems[j] = elems[j + 1];
                    }
                    elemCount--;
                    return;
                }
            }
        }
    }

    @Override
    public ElemType getElem(int row, int col) {
        for (Triple<ElemType> item : elems) {
            if (item.row == row && item.col == col) {
                return item.elem;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public TripleSparseMatrix(TripleSparseMatrix<ElemType> matrix) {
        rowCount = matrix.rowCount;
        colCount = matrix.colCount;
        capacity = matrix.capacity;
        elems = new Triple[capacity];
        elemCount = matrix.elemCount;
        System.arraycopy(matrix.elems, 0, elems, 0, elemCount);
    }

    @Override
    public TripleSparseMatrix<ElemType> simpleTranspose() {
        TripleSparseMatrix<ElemType> result = new TripleSparseMatrix<>(colCount, rowCount, capacity);
        result.elemCount = elemCount;
        if (result.elemCount > 0) {
            int i = 0;
            for (int col = 0; col < colCount; col++) {
                for (int j = 0; j < elemCount; j++) {
                    if (elems[j].col == col) {
                        result.elems[i].row = elems[j].col;
                        result.elems[i].col = elems[j].col;
                        result.elems[i].elem = elems[j].elem;
                        i++;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public TripleSparseMatrix<ElemType> fastTranspose() {
        TripleSparseMatrix<ElemType> result = new TripleSparseMatrix<>(colCount, rowCount, capacity);
        result.elemCount = elemCount;
        int[] colCounts = new int[colCount + 1];
        int[] colIndexes = new int[colCount + 1];
        if (result.elemCount > 0) {
            for (int i = 0; i < colCount; i++) {
                colCounts[i] = 0;
            }
            for (int i = 0; i < elemCount; i++) {
                ++colCounts[elems[i].col];
            }
            colIndexes[0] = 0;
            for (int i = 1; i < colCount; i++) {
                colIndexes[i] = colIndexes[i - 1] + colCounts[i - 1];
            }
            for (int i = 0; i < elemCount; i++) {
                int j = colIndexes[elems[i].col];
                result.elems[j].row = elems[i].col;
                result.elems[j].col = elems[i].col;
                result.elems[j].elem = elems[i].elem;
                colIndexes[elems[i].col]++;
            }
        }
        return result;
    }
}