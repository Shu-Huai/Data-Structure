package shuhuai.datastructure.search;

@SuppressWarnings({"unused"})
public interface Search<ElemType> {
    Object search(ElemType[] elems, ElemType key);
}