package shuhuai.datastructure.polynomial;

import shuhuai.datastructure.exceptions.BaseException;
import shuhuai.datastructure.exceptions.RangeException;
import shuhuai.datastructure.linearlist.LinearList;
import shuhuai.datastructure.linearlist.linklist.LinkList;

import java.util.function.Function;

import static java.lang.Math.pow;

@SuppressWarnings({"unused"})
public class Polynomial {
    protected LinearList<PolyItem> polyList;

    public Polynomial() {
        polyList = new LinkList<>();
    }

    public Polynomial(Polynomial copy) {
        if (copy == null) {
            return;
        }
        polyList = new LinkList<>((LinkList<PolyItem>) copy.polyList);
    }

    public Polynomial(LinkList<PolyItem> copyLinkList) {
        polyList = new LinkList<>(copyLinkList);
    }

    public int getLength() {
        return polyList.getLength();
    }

    public boolean isZero() {
        return polyList.isEmpty();
    }

    public void SetZero() {
        polyList.clear();
    }

    public void traverse() {
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    public void traverse(Function<String, Void> output) {
        int pos = 0;
        PolyItem it = null;
        boolean status = true;
        try {
            it = polyList.getElem(pos);
        } catch (RangeException e) {
            status = false;
        }
        while (status) {
            if (pos > 1 && it.coefficient > 0) {
                output.apply("+");
            }
            if (it.coefficient != 1)
                if (it.coefficient != -1) {
                    output.apply(String.valueOf(it.coefficient));
                } else {
                    output.apply("-");
                }
            if (it.index > 1) {
                output.apply("x^" + it.index);
            } else if (it.index == 1) {
                output.apply("x");
            }
            try {
                it = polyList.getElem(++pos);
            } catch (RangeException e) {
                status = false;
            }
        }
    }

    public void insertElem(PolyItem item) {
        try {
            PolyItem it = polyList.getElem(0);
            int pos = 0;
            boolean status = true;
            while (status && it.index > item.index) {
                try {
                    it = polyList.getElem(++pos);
                } catch (RangeException exception) {
                    status = false;
                }
            }
            polyList.insertElem(item, pos);
        } catch (BaseException ignored) {
        }
    }

    public void add(Polynomial p) {
        if (p == null) {
            return;
        }
        LinkList<PolyItem> la = (LinkList<PolyItem>) polyList;
        LinkList<PolyItem> lb = (LinkList<PolyItem>) p.polyList;
        LinkList<PolyItem> lc = new LinkList<>();
        int aPos = 0;
        int bPos = 0;
        PolyItem aItem = new PolyItem();
        PolyItem bItem = new PolyItem();
        boolean aStatus = true;
        boolean bStatus = true;
        try {
            aItem = la.getElem(aPos++);
        } catch (RangeException exception) {
            aStatus = false;
        }
        try {
            bItem = lb.getElem(bPos++);
        } catch (RangeException exception) {
            bStatus = false;
        }
        while (aStatus && bStatus) {
            if (aItem.index > bItem.index) {
                lc.appendElem(aItem);
                try {
                    aItem = la.getElem(aPos++);
                } catch (RangeException e) {
                    aStatus = false;
                }
            } else if (aItem.index < bItem.index) {
                lc.appendElem(bItem);
                try {
                    bItem = lb.getElem(bPos++);
                } catch (RangeException e) {
                    bStatus = false;
                }
            } else {
                PolyItem sumItem = new PolyItem(aItem.coefficient + bItem.coefficient, aItem.index);
                if (sumItem.coefficient != 0)
                    lc.appendElem(sumItem);
                try {
                    aItem = la.getElem(aPos++);
                } catch (RangeException e) {
                    aStatus = false;
                }
                try {
                    bItem = lb.getElem(bPos++);
                } catch (RangeException e) {
                    bStatus = false;
                }
            }
        }
        while (aStatus) {
            lc.appendElem(aItem);
            try {
                aItem = la.getElem(aPos++);
            } catch (RangeException e) {
                aStatus = false;
            }
        }
        while (bStatus) {
            lc.appendElem(bItem);
            try {
                bItem = lb.getElem(bPos++);
            } catch (RangeException e) {
                bStatus = false;
            }
        }
        polyList = new LinkList<>(lc);
    }

    public void copy(Polynomial copy) {
        if (copy == null) {
            return;
        }
        polyList.copy(copy.polyList);
    }

    public void copy(LinkList<PolyItem> copyLinkList) {
        polyList.copy(copyLinkList);
    }

    public double calc(double x) {
        double result = 0;
        try {
            for (int i = 0; i < polyList.getLength(); i++) {
                PolyItem t = polyList.getElem(i);
                result += t.coefficient * pow(x, t.index);
            }
        } catch (BaseException ignored) {
        }
        return result;
    }
}