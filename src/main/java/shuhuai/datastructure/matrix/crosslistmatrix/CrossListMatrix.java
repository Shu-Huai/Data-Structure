package shuhuai.datastructure.matrix.crosslistmatrix;

import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.RangeException;
import shuhuai.datastructure.matrix.Matrix;

@SuppressWarnings({"unused", "unchecked"})
public class CrossListMatrix<ElemType> implements Matrix<ElemType> {
    protected Node<ElemType>[] rowHead;
    protected Node<ElemType>[] colHead;
    protected int rowCount;
    protected int colCount;
    protected int elemCount;

    public CrossListMatrix(int rowCount, int colCount) throws RangeException {
        if (rowCount <= 0 || colCount <= 0) {
            throw new RangeException("范围错误");
        }
        this.rowCount = rowCount;
        this.colCount = colCount;
        elemCount = 0;
        rowHead = new Node[rowCount];
        colHead = new Node[colCount];
    }

    @Override
    public void clear() {
        rowCount = 0;
        colCount = 0;
        elemCount = 0;
        rowHead = new Node[rowCount];
        colHead = new Node[colCount];
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
        if (row >= rowCount || col >= colCount || row < 0 || col < 0) {
            throw new RangeException("范围错误");
        }
        Node<ElemType> pre = null;
        Node<ElemType> p = rowHead[row];
        while (p != null && p.elem.col < col) {
            pre = p;
            p = p.right;
        }
        if (!(boolean) elem) {
            if (p != null && p.elem.col == col) {
                if (rowHead[row] == p) {
                    rowHead[row] = p.right;
                } else {
                    pre.right = p.right;
                }
                if (colHead[col] == p) {
                    colHead[col] = p.down;
                } else {
                    pre = colHead[col];
                    while (pre.down != p) {
                        pre = pre.down;
                    }
                    pre.down = p.down;
                }
            }
            elemCount--;
        } else {
            if (p != null && p.elem.col == col) {
                p.elem.elem = elem;
            } else {
                Triple<ElemType> newElem = new Triple<>(row, col, elem);
                Node<ElemType> newP = new Node<>(newElem);
                if (rowHead[row] == p) {
                    rowHead[row] = newP;
                } else {
                    pre.right = newP;
                }
                newP.right = p;
                pre = null;
                p = colHead[col];
                while (p != null && p.elem.row < row) {
                    pre = p;
                    p = p.down;
                }
                if (colHead[col] == p) {
                    colHead[col] = newP;
                } else {
                    pre.down = newP;
                }
                newP.down = p;
                elemCount++;
            }
        }
    }

    @Override
    public ElemType getElem(int row, int col) throws RangeException {
        if (row >= rowCount || col >= colCount || row < 0 || col < 0) {
            throw new RangeException("范围错误");
        }
        Node<ElemType> p;
        for (p = rowHead[row]; p != null; p = p.right) {
            if (p.elem.col >= col) {
                break;
            }
        }
        if (p != null && p.elem.col == col) {
            return p.elem.elem;
        }
        return null;
    }

    @Override
    public Matrix<ElemType> simpleTranspose() {
        return null;
    }

    @Override
    public Matrix<ElemType> fastTranspose() {
        return null;
    }
}