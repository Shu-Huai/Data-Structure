package shuhuai.datastructure.graph.adjacencymatrixundirectedgraph;

import shuhuai.datastructure.exceptions.*;
import shuhuai.datastructure.queue.Queue;
import shuhuai.datastructure.queue.linkqueue.LinkQueue;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class AdjacencyMatrixUndirectedGraph<ElemType> {
    protected int vertexNum;
    protected int edgeNum;
    protected ElemType[] vertexes;
    protected boolean[] isVisited;
    protected boolean[][] adjacencyMatrix;

    public AdjacencyMatrixUndirectedGraph() {
        vertexNum = 0;
        edgeNum = 0;
        vertexes = (ElemType[]) new Object[1000];
        isVisited = new boolean[1000];
        adjacencyMatrix = new boolean[1000][1000];
        for (int i = 0; i < 1000; i++) {
            adjacencyMatrix[i] = new boolean[1000];
            isVisited[i] = false;
        }
    }

    public AdjacencyMatrixUndirectedGraph(int maxVertexNum) {
        vertexNum = 0;
        edgeNum = 0;
        vertexes = (ElemType[]) new Object[maxVertexNum];
        isVisited = new boolean[maxVertexNum];
        adjacencyMatrix = new boolean[maxVertexNum][maxVertexNum];
        for (int i = 0; i < maxVertexNum; i++) {
            adjacencyMatrix[i] = new boolean[maxVertexNum];
            isVisited[i] = false;
        }
    }

    public AdjacencyMatrixUndirectedGraph(ElemType[] vertexes, int vertexNum, int maxVertexNum) {
        this.vertexNum = vertexNum;
        edgeNum = 0;
        this.vertexes = (ElemType[]) new Object[maxVertexNum];
        isVisited = new boolean[maxVertexNum];
        for (int i = 0; i < this.vertexNum; i++) {
            this.vertexes[i] = vertexes[i];
            isVisited[i] = false;
        }
        adjacencyMatrix = new boolean[maxVertexNum][maxVertexNum];
        for (int i = 0; i < maxVertexNum; i++) {
            adjacencyMatrix[i] = new boolean[maxVertexNum];
        }
        for (int i = 0; i < this.vertexNum; i++) {
            for (int j = 0; j < this.vertexNum; j++) {
                adjacencyMatrix[i][j] = false;
            }
        }
    }

    public AdjacencyMatrixUndirectedGraph(AdjacencyMatrixUndirectedGraph<ElemType> graph) {
        if (graph == null) {
            return;
        }
        vertexNum = graph.vertexNum;
        edgeNum = graph.edgeNum;
        int maxVertexNum = graph.vertexes.length;
        vertexes = (ElemType[]) new Object[maxVertexNum];
        isVisited = new boolean[maxVertexNum];
        for (int i = 0; i < vertexNum; i++) {
            vertexes[i] = graph.vertexes[i];
            isVisited[i] = graph.isVisited[i];
        }
        adjacencyMatrix = new boolean[maxVertexNum][maxVertexNum];
        for (int i = 0; i < maxVertexNum; i++) {
            adjacencyMatrix[i] = new boolean[maxVertexNum];
        }
        for (int i = 0; i < vertexNum; i++) {
            System.arraycopy(graph.adjacencyMatrix[i], 0, adjacencyMatrix[i], 0, vertexNum);
        }
    }

    public void clear() {
        vertexNum = 0;
        edgeNum = 0;
    }

    public boolean isEmpty() {
        return vertexNum == 0;
    }

    public void traverse() {
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    public void traverse(Function<String, Void> output) {
        for (int i = 0; i < vertexNum; i++) {
            output.apply("\t" + vertexes[i]);
        }
        output.apply("\n");
        for (int i = 0; i < vertexNum; i++) {
            output.apply(vertexes[i].toString());
            for (int j = 0; j < vertexNum; j++) {
                output.apply("\t" + adjacencyMatrix[i][j]);
            }
            output.apply("\n");
        }
    }

    public void depthFirstTraverse() {
        depthFirstTraverse(value -> {
            System.out.print(value);
            return null;
        });
    }


    public void depthFirstTraverse(Function<String, Void> output) {
        for (int i = 0; i < vertexNum; i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < vertexNum; i++) {
            if (!isVisited[i]) {
                depthFirstSearch(i, output);
            }
        }
    }

    public void depthFirstSearch(int vertex, Function<String, Void> output) {
        output.apply(vertexes[vertex] + " ");
        isVisited[vertex] = true;
        for (int i = getFirstAdjacencyVertex(vertex); i != -1; i = getNextAdjacencyVertex(vertex, i)) {
            if (!isVisited[i]) {
                depthFirstSearch(i, output);
            }
        }
    }

    public void breadthFirstTraverse() {
        breadthFirstTraverse(value -> {
            System.out.print(value);
            return null;
        });
    }


    public void breadthFirstTraverse(Function<String, Void> output) {
        for (int i = 0; i < vertexNum; i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < vertexNum; i++) {
            if (!isVisited[i]) {
                breadthFirstSearch(i, output);
            }
        }
    }

    public void breadthFirstSearch(int vertex, Function<String, Void> output) {
        try {
            Queue<Integer> queue = new LinkQueue<>();
            queue.enQueue(vertex);
            while (!queue.isEmpty()) {
                int curVertex = queue.deQueue();
                output.apply(vertexes[vertex] + " ");
                isVisited[vertex] = true;
                for (int i = getFirstAdjacencyVertex(curVertex); i != -1; i = getNextAdjacencyVertex(curVertex, i)) {
                    queue.enQueue(i);
                }
            }
        } catch (BaseException ignored) {
        }
    }

    public void appendVertex(ElemType elem) throws OverFlowException {
        if (vertexNum == vertexes.length) {
            throw new OverFlowException("空间已满");
        }
        vertexes[vertexNum] = elem;
        isVisited[vertexNum] = false;
        for (int i = 0; i <= vertexNum; i++) {
            adjacencyMatrix[vertexNum][i] = false;
            adjacencyMatrix[i][vertexNum] = false;
        }
        vertexNum++;
    }

    public void insertEdge(int vertexA, int vertexB) throws RangeException {
        if (vertexA < 0 || vertexA >= vertexNum || vertexB < 0 || vertexB >= vertexNum || vertexA == vertexB) {
            throw new RangeException("范围错误");
        }
        if (!adjacencyMatrix[vertexA][vertexB]) {
            adjacencyMatrix[vertexA][vertexB] = true;
            adjacencyMatrix[vertexB][vertexA] = true;
            edgeNum++;
        }
    }

    public void deleteVertex(ElemType elem) throws UnderFlowException, NotExistException {
        if (vertexNum == 0) {
            throw new UnderFlowException("空间空");
        }
        int vertex = getIndex(elem);
        if (vertex == -1) {
            throw new NotExistException("节点不存在");
        }
        for (int i = 0; i < vertexNum; i++) {
            if (adjacencyMatrix[vertex][i]) {
                adjacencyMatrix[vertex][i] = false;
                adjacencyMatrix[i][vertex] = false;
                edgeNum--;
            }
        }
        vertexNum--;
        if (vertex < vertexNum) {
            vertexes[vertex] = vertexes[vertexNum];
            isVisited[vertex] = isVisited[vertexNum];
            System.arraycopy(adjacencyMatrix[vertexNum], 0, adjacencyMatrix[vertex], 0, vertexNum + 1);
            for (int i = 0; i <= vertexNum; i++) {
                adjacencyMatrix[i][vertex] = adjacencyMatrix[i][vertexNum];
            }
        }
    }

    public void deleteEdge(int vertexA, int vertexB) throws UnderFlowException, RangeException {
        if (vertexNum == 0) {
            throw new UnderFlowException("空间空");
        }
        if (vertexA < 0 || vertexA >= vertexNum || vertexB < 0 || vertexB >= vertexNum || vertexA == vertexB) {
            throw new RangeException("范围错误");
        }
        if (adjacencyMatrix[vertexA][vertexB]) {
            adjacencyMatrix[vertexA][vertexB] = false;
            adjacencyMatrix[vertexB][vertexA] = false;
            edgeNum--;
        }
    }

    public void setElem(int vertex, ElemType e) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("范围错误");
        }
        vertexes[vertex] = e;
    }


    public void setVisited(int vertex, boolean val) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("范围错误");
        }
        isVisited[vertex] = val;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public int getIndex(ElemType elem) {
        for (int i = 0; i < vertexNum; i++) {
            if (vertexes[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public ElemType getElem(int vertex) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("范围错误");
        }
        return vertexes[vertex];
    }

    public boolean isVisited(int vertex) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("范围错误");
        }
        return isVisited[vertex];
    }

    public int getFirstAdjacencyVertex(int vertex) {
        for (int i = 0; i < vertexNum; i++) {
            if (adjacencyMatrix[vertex][i]) {
                return i;
            }
        }
        return -1;
    }

    public int getNextAdjacencyVertex(int vertexA, int vertexB) {
        for (int i = vertexB + 1; i < vertexNum; i++) {
            if (adjacencyMatrix[vertexA][i]) {
                return i;
            }
        }
        return -1;
    }

    public void copy(AdjacencyMatrixUndirectedGraph<ElemType> graph) {
        if (graph == null) {
            return;
        }
        vertexNum = graph.vertexNum;
        edgeNum = graph.edgeNum;
        vertexes = (ElemType[]) new Object[graph.vertexes.length];
        isVisited = new boolean[graph.vertexes.length];
        for (int i = 0; i < vertexNum; i++) {
            vertexes[i] = graph.vertexes[i];
            isVisited[i] = graph.isVisited[i];
        }
        adjacencyMatrix = new boolean[graph.vertexes.length][graph.vertexes.length];
        for (int i = 0; i < graph.vertexes.length; i++) {
            adjacencyMatrix[i] = new boolean[graph.vertexes.length];
        }
        for (int i = 0; i < vertexNum; i++) {
            System.arraycopy(graph.adjacencyMatrix[i], 0, adjacencyMatrix[i], 0, vertexNum);
        }
    }
}