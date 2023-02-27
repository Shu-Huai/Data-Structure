package shuhuai.datastructure.search.sequencesearch;

import shuhuai.datastructure.search.Search;

@SuppressWarnings({"unused"})
public class SequenceSearch<ElemType> implements Search<ElemType> {
    public SequenceSearch() {
    }

    public Integer search(ElemType[] elems, ElemType key) {
        for (int i = 0; i < elems.length; i++) {
            if (elems[i] == key) {
                return i;
            }
        }
        return -1;
    }
}