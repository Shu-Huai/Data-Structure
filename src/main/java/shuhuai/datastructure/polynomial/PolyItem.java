package shuhuai.datastructure.polynomial;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused"})
public class PolyItem implements Comparable<PolyItem> {
    protected double coefficient;
    protected int index;

    public PolyItem() {
        index = -1;
    }

    public PolyItem(double coefficient, int index) {
        this.coefficient = coefficient;
        this.index = index;
    }

    @Override
    public int compareTo(@NotNull PolyItem o) {
        return 0;
    }
}