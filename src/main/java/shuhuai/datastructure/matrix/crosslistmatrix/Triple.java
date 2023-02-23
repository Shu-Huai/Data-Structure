package shuhuai.datastructure.matrix.crosslistmatrix;

@SuppressWarnings({"unused"})
public class Triple<ElemType> {
    protected int row;
    protected int col;
    protected ElemType elem;

    public Triple() {
        row = 0;
        col = 0;
        elem = null;
    }

    public Triple(int row, int col, ElemType elem) {
        this.row = row;
        this.col = col;
        this.elem = elem;
    }
}