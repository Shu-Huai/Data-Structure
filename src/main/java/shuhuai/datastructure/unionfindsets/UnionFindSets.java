package shuhuai.datastructure.unionfindsets;

import shuhuai.datastructure.array.Array;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public class UnionFindSets<ElemType> {
    protected Array<Node<ElemType>> sets;

    public UnionFindSets(int capacity) {
        sets = new Array<>(capacity);
    }

    public UnionFindSets(ElemType[] v, int capacity) {
        sets = new Array<>(capacity);
        for (int i = 0; i < capacity; i++) {
            sets.set(i, new Node<>(v[i], -1));
        }
    }

    public UnionFindSets(UnionFindSets<ElemType> UFS) {
        if (UFS == null) {
            return;
        }
        sets = new Array<>(UFS.sets.getCapacity());
        for (int i = 0; i < sets.getCapacity(); i++) {
            sets.set(i, UFS.sets.get(i));
        }
    }

    public void clear() {
        sets = new Array<>(sets.getCapacity());
    }

    public void traverse() {
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    public void traverse(Function<String, Void> output) {
        output.apply("Index:  ");
        for (int i = 0; i < sets.getCapacity(); i++) {
            output.apply(String.valueOf(i));
        }
        output.apply("\nParent: ");
        for (int i = 0; i < sets.getCapacity(); i++) {
            output.apply(String.valueOf(sets.get(i).parent));
        }
        output.apply("\nData: ");
        for (int i = 0; i < sets.getCapacity(); i++) {
            output.apply(String.valueOf(sets.get(i).elem));
        }
    }

    public ElemType getElem(int p) {
        return sets.get(p).elem;
    }

    public int getParent(int p) {
        return sets.get(p).parent;
    }

    public int getIndex(ElemType elem) {
        int p = 0;
        while (p < sets.getCapacity()) {
            if (!sets.get(p).elem.equals(elem)) {
                p++;
            } else {
                return p;
            }
        }
        return -1;
    }

    public void simpleUnion(ElemType a, ElemType b) {
        int r1 = simpleFind(a);
        int r2 = simpleFind(b);
        if (r1 != r2 && r1 != -1 && r2 != -1) {
            sets.set(r1, new Node<>(sets.get(r1).elem, sets.get(r1).parent + sets.get(r2).parent));
            sets.set(r2, new Node<>(sets.get(r2).elem, r1));
        }
    }

    public int simpleFind(ElemType elem) {
        int p = getIndex(elem);
        if (p == -1) {
            return -1;
        }
        while (sets.get(p).parent > -1) {
            p = sets.get(p).parent;
        }
        return p;
    }

    public int optimizedFind(ElemType e) {
        int p = getIndex(e);
        if (p == -1) {
            return -1;
        }
        int r = p;
        while (sets.get(r).parent > -1) {
            r = sets.get(r).parent;
        }
        while (r != sets.get(p).parent) {
            int temp = sets.get(p).parent;
            sets.set(p, new Node<>(sets.get(p).elem, r));
            p = temp;
        }
        return r;
    }

    public void unionByHeight(ElemType a, ElemType b) {
        int r1 = simpleFind(a);
        int r2 = simpleFind(b);
        if (r1 != r2 && r1 != -1 && r2 != -1) {
            if (sets.get(r2).parent < sets.get(r1).parent) {
                sets.set(r1, new Node<>(sets.get(r1).elem, r2));
            } else {
                if (sets.get(r1).parent == sets.get(r2).parent) {
                    sets.set(r1, new Node<>(sets.get(r1).elem, sets.get(r1).parent - 1));
                }
                sets.set(r2, new Node<>(sets.get(r2).elem, r1));
            }
        }
    }

    public void unionByNodeNumber(ElemType a, ElemType b) {
        int r1 = simpleFind(a);
        int r2 = simpleFind(b);
        if (r1 != r2 && r1 != -1 && r2 != -1) {
            if (sets.get(r1).parent <= sets.get(r2).parent) {
                sets.set(r1, new Node<>(sets.get(r1).elem, sets.get(r1).parent + sets.get(r2).parent));
                sets.set(r2, new Node<>(sets.get(r2)));
            } else {
                sets.set(r2, new Node<>(sets.get(r2).elem, sets.get(r2).parent + sets.get(r1).parent));
                sets.set(r1, new Node<>(sets.get(r1)));
            }
        }
    }

    public boolean differ(ElemType a, ElemType b) {
        return simpleFind(a) != simpleFind(b);
    }


    public void copy(UnionFindSets<ElemType> UFS) {
        if (UFS == null) {
            return;
        }
        sets = new Array<>(UFS.sets.getCapacity());
        for (int i = 0; i < sets.getCapacity(); i++) {
            sets.set(i, UFS.sets.get(i));
        }
    }
}