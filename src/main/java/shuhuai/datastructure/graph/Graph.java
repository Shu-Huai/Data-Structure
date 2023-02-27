package shuhuai.datastructure.graph;

import shuhuai.datastructure.exceptions.NotExistException;
import shuhuai.datastructure.exceptions.OverFlowException;
import shuhuai.datastructure.exceptions.RangeException;
import shuhuai.datastructure.exceptions.UnderFlowException;

import java.util.function.Function;

@SuppressWarnings({"unused"})
public interface Graph<ElemType> {
    void clear();

    boolean isEmpty();

    void traverse();

    void traverse(Function<String, Void> output);

    void depthFirstTraverse();

    void depthFirstTraverse(Function<String, Void> output);

    void depthFirstSearch(int vertex, Function<String, Void> output);

    void breadthFirstTraverse();

    void breadthFirstTraverse(Function<String, Void> output);

    void breadthFirstSearch(int vertex, Function<String, Void> output);

    void appendVertex(ElemType elem) throws OverFlowException;

    void insertEdge(int tailVertex, int headVertex) throws RangeException;

    void deleteVertex(ElemType elem) throws UnderFlowException, NotExistException;

    void deleteEdge(int tailVertex, int headVertex) throws RangeException, UnderFlowException;

    void setElem(int vertex, ElemType elem) throws RangeException;

    void setVisited(int vertex, boolean isVisited) throws RangeException;

    int getVertexNum();

    int getEdgeNum();

    int getIndex(ElemType data);

    ElemType getElem(int vertex) throws RangeException;

    boolean isVisited(int vertex) throws RangeException;

    int getFirstAdjacencyVertex(int vertex);

    int getNextAdjacencyVertex(int tailVertex, int headVertex);

    void copy(Graph<ElemType> network);
}