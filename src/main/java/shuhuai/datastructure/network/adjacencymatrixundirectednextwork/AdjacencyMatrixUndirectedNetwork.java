package shuhuai.datastructure.network.adjacencymatrixundirectednextwork;

import shuhuai.datastructure.array.Array;
import shuhuai.datastructure.exceptions.*;
import shuhuai.datastructure.heap.maxheap.MaxHeap;
import shuhuai.datastructure.heap.minheap.MinHeap;
import shuhuai.datastructure.network.Network;
import shuhuai.datastructure.queue.Queue;
import shuhuai.datastructure.queue.linkqueue.LinkQueue;
import shuhuai.datastructure.unionfindsets.UnionFindSets;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class AdjacencyMatrixUndirectedNetwork<ElemType, WeightType extends Comparable<WeightType>> implements Network<ElemType, WeightType> {
    protected int vertexNum;
    protected int edgeNum;
    protected ElemType[] vertexes;
    protected boolean[] isVisited;
    protected WeightType[][] adjacencyMatrix;
    protected WeightType infinity_;

    public AdjacencyMatrixUndirectedNetwork(int maxVertexNum, WeightType infinity) {
        vertexNum = 0;
        this.infinity_ = infinity;
        edgeNum = 0;
        vertexes = (ElemType[]) new Comparable[maxVertexNum];
        isVisited = new boolean[maxVertexNum];
        adjacencyMatrix = (WeightType[][]) new Comparable[maxVertexNum][maxVertexNum];
        for (int i = 0; i < maxVertexNum; i++) {
            adjacencyMatrix[i] = (WeightType[]) new Comparable[maxVertexNum];
            isVisited[i] = false;
        }
    }

    public AdjacencyMatrixUndirectedNetwork(ElemType[] vertexes, int vertexNum, int maxVertexNum, WeightType infinity) {
        this.vertexNum = vertexNum;
        this.infinity_ = infinity;
        edgeNum = 0;
        this.vertexes = (ElemType[]) new Object[maxVertexNum];
        isVisited = new boolean[maxVertexNum];
        for (int i = 0; i < this.vertexNum; i++) {
            this.vertexes[i] = vertexes[i];
            isVisited[i] = false;
        }
        adjacencyMatrix = (WeightType[][]) new Comparable[maxVertexNum][maxVertexNum];
        for (int i = 0; i < maxVertexNum; i++) {
            adjacencyMatrix[i] = (WeightType[]) new Comparable[maxVertexNum];
        }
        for (int i = 0; i < this.vertexNum; i++) {
            for (int j = 0; j < this.vertexNum; j++) {
                adjacencyMatrix[i][j] = infinity_;
            }
        }
    }

    public AdjacencyMatrixUndirectedNetwork(AdjacencyMatrixUndirectedNetwork<ElemType, WeightType> graph) {
        if (graph == null) {
            return;
        }
        vertexNum = graph.vertexNum;
        infinity_ = graph.infinity_;
        edgeNum = graph.edgeNum;
        int maxVertexNum = graph.vertexes.length;
        vertexes = (ElemType[]) new Object[maxVertexNum];
        isVisited = new boolean[maxVertexNum];
        for (int i = 0; i < vertexNum; i++) {
            vertexes[i] = graph.vertexes[i];
            isVisited[i] = graph.isVisited[i];
        }
        adjacencyMatrix = (WeightType[][]) new Comparable[maxVertexNum][maxVertexNum];
        for (int i = 0; i < maxVertexNum; i++) {
            adjacencyMatrix[i] = (WeightType[]) new Comparable[maxVertexNum];
        }
        for (int i = 0; i < vertexNum; i++) {
            System.arraycopy(graph.adjacencyMatrix[i], 0, adjacencyMatrix[i], 0, vertexNum);
        }
    }


    @Override
    public void clear() {
        vertexNum = 0;
        edgeNum = 0;
    }

    @Override
    public boolean isEmpty() {
        return vertexNum == 0;
    }

    public void traverse() {
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    @Override
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

    @Override
    public void depthFirstTraverse() {
        depthFirstTraverse(value -> {
            System.out.print(value);
            return null;
        });
    }


    @Override
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

    @Override
    public void depthFirstSearch(int vertex, Function<String, Void> output) {
        output.apply(vertexes[vertex] + " ");
        isVisited[vertex] = true;
        for (int i = getFirstAdjacencyVertex(vertex); i != -1; i = getNextAdjacencyVertex(vertex, i)) {
            if (!isVisited[i]) {
                depthFirstSearch(i, output);
            }
        }
    }

    @Override
    public void breadthFirstTraverse() {
        breadthFirstTraverse(value -> {
            System.out.print(value);
            return null;
        });
    }


    @Override
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

    @Override
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

    @Override
    public void appendVertex(ElemType elem) throws OverFlowException {
        if (vertexNum == vertexes.length) {
            throw new OverFlowException("????????????");
        }
        vertexes[vertexNum] = elem;
        isVisited[vertexNum] = false;
        for (int i = 0; i <= vertexNum; i++) {
            adjacencyMatrix[vertexNum][i] = infinity_;
            adjacencyMatrix[i][vertexNum] = infinity_;
        }
        vertexNum++;
    }

    @Override
    public void insertEdge(int vertexA, int vertexB, WeightType weight) throws RangeException {
        if (vertexA < 0 || vertexA >= vertexNum || vertexB < 0 || vertexB >= vertexNum || vertexA == vertexB) {
            throw new RangeException("????????????");
        }
        if (adjacencyMatrix[vertexA][vertexB] == infinity_) {
            adjacencyMatrix[vertexA][vertexB] = weight;
            adjacencyMatrix[vertexB][vertexA] = weight;
            edgeNum++;
        }
    }

    @Override
    public void deleteVertex(ElemType elem) throws UnderFlowException, NotExistException {
        if (vertexNum == 0) {
            throw new UnderFlowException("?????????");
        }
        int vertex = getIndex(elem);
        if (vertex == -1) {
            throw new NotExistException("???????????????");
        }
        for (int i = 0; i < vertexNum; i++) {
            if (!adjacencyMatrix[vertex][i].equals(infinity_)) {
                adjacencyMatrix[vertex][i] = infinity_;
                adjacencyMatrix[i][vertex] = infinity_;
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

    @Override
    public void deleteEdge(int vertexA, int vertexB) throws UnderFlowException, RangeException {
        if (vertexNum == 0) {
            throw new UnderFlowException("?????????");
        }
        if (vertexA < 0 || vertexA >= vertexNum || vertexB < 0 || vertexB >= vertexNum || vertexA == vertexB) {
            throw new RangeException("????????????");
        }
        if (!adjacencyMatrix[vertexA][vertexB].equals(infinity_)) {
            adjacencyMatrix[vertexA][vertexB] = infinity_;
            adjacencyMatrix[vertexB][vertexA] = infinity_;
            edgeNum--;
        }
    }

    @Override
    public void setElem(int vertex, ElemType e) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("????????????");
        }
        vertexes[vertex] = e;
    }

    @Override
    public void setVisited(int vertex, boolean isVisited) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("????????????");
        }
        this.isVisited[vertex] = isVisited;
    }

    @Override
    public void setWeight(int tailVertex, int headVertex, WeightType weight) throws RangeException {
        if (tailVertex < 0 || tailVertex >= vertexNum ||
                headVertex < 0 || headVertex >= vertexNum || tailVertex == headVertex) {
            throw new RangeException("????????????");
        }
        adjacencyMatrix[headVertex][tailVertex] = weight;
        adjacencyMatrix[tailVertex][headVertex] = weight;
    }

    @Override
    public int getVertexNum() {
        return vertexNum;
    }

    @Override
    public int getEdgeNum() {
        return edgeNum;
    }

    @Override
    public int getIndex(ElemType elem) {
        for (int i = 0; i < vertexNum; i++) {
            if (vertexes[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ElemType getElem(int vertex) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("????????????");
        }
        return vertexes[vertex];
    }

    @Override
    public boolean isVisited(int vertex) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum) {
            throw new RangeException("????????????");
        }
        return isVisited[vertex];
    }

    @Override
    public WeightType getWeight(int vertexA, int vertexB) throws RangeException {
        if (vertexA < 0 || vertexA >= vertexNum || vertexB < 0 || vertexB >= vertexNum || vertexA == vertexB) {
            throw new RangeException("????????????");
        }
        return adjacencyMatrix[vertexA][vertexB];
    }

    @Override
    public WeightType getInfinity() {
        return infinity_;
    }

    @Override
    public int getFirstAdjacencyVertex(int vertex) {
        for (int i = 0; i < vertexNum; i++) {
            if (adjacencyMatrix[vertex][i] != infinity_) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getNextAdjacencyVertex(int vertexA, int vertexB) {
        for (int i = vertexB + 1; i < vertexNum; i++) {
            if (adjacencyMatrix[vertexA][i] != infinity_) {
                return i;
            }
        }
        return -1;
    }

    public void kruskalMinimumSpanningTree() {
        kruskalMinimumSpanningTree(value -> {
            System.out.print(value);
            return null;
        });
    }

    public void kruskalMinimumSpanningTree(Function<String, Void> output) {
        try {
            MinHeap<KruskalEdge<ElemType, WeightType>> heap = new MinHeap<>(edgeNum);
            for (int i = 0; i < vertexNum; i++) {
                for (int j = getFirstAdjacencyVertex(i); j >= 0; j = getNextAdjacencyVertex(i, j)) {
                    if (i < j) {
                        KruskalEdge<ElemType, WeightType> KE = new KruskalEdge<>(vertexes[i], vertexes[j], adjacencyMatrix[i][j]);
                        heap.insertElem(KE);
                    }
                }
            }
            int count = 0;
            UnionFindSets<ElemType> UFS = new UnionFindSets<>(vertexes, vertexNum);
            while (count < vertexNum - 1) {
                KruskalEdge<ElemType, WeightType> edge;
                edge = heap.getTop();
                heap.deleteTop();
                ElemType v1 = edge.GetVertexA();
                ElemType v2 = edge.GetVertexB();
                if (UFS.differ(v1, v2)) {
                    output.apply("Edge: (" + v1 + ", " + v2 + ") Weight: " + edge.GetWeight() + ".\n");
                    UFS.unionByNodeNumber(v1, v2);
                    count++;
                }
            }
            UFS.traverse();
        } catch (BaseException ignored) {
        }
    }

    public void primMinimumSpanningTree(int firstVertex) {
        primMinimumSpanningTree(firstVertex, value -> {
            System.out.print(value);
            return null;
        });
    }

    public void primMinimumSpanningTree(int firstVertex, Function<String, Void> output) {
        Array<PrimEdge<WeightType>> minWeightEdge = new Array<>(vertexNum);
        for (int i = 0; i < vertexNum; i++) {
            if (i == firstVertex) {
                minWeightEdge.set(i, (PrimEdge<WeightType>) new PrimEdge<>(0, -1));
            } else {
                minWeightEdge.set(i, new PrimEdge<>(adjacencyMatrix[firstVertex][i], firstVertex));
            }
        }
        for (int i = 1; i < vertexNum; i++) {
            WeightType minWeight = infinity_;
            int vertex = firstVertex;
            for (int j = 0; j < vertex; j++) {
                if (minWeightEdge.get(j).minWeight != null && minWeightEdge.get(j).minWeight.compareTo(minWeight) < 0) {
                    vertex = j;
                    minWeight = minWeightEdge.get(j).minWeight;
                }
            }
            if (vertex != firstVertex) {
                output.apply("Edge: (" + vertexes[minWeightEdge.get(vertex).adjacencyVertex] + ", " + vertexes[vertex] + ") Weight: " + minWeight + "\n");
                minWeightEdge.set(vertex, new PrimEdge<>(null, minWeightEdge.get(vertex).adjacencyVertex));
                for (int j = getFirstAdjacencyVertex(vertex); j != -1; j = getNextAdjacencyVertex(vertex, j)) {
                    if (minWeightEdge.get(j).minWeight != null && adjacencyMatrix[vertex][j].compareTo(minWeightEdge.get(j).minWeight) < 0) {
                        minWeightEdge.set(j, new PrimEdge<>(adjacencyMatrix[vertex][j], vertex));
                    }
                }
            }
        }
    }

    public boolean isConnected() {
        for (int i = 0; i < vertexes.length; i++) {
            isVisited[i] = false;
        }
        depthFirstSearch(0, value -> null);
        for (int i = 0; i < vertexes.length; i++) {
            if (!isVisited[i]) {
                return false;
            }
        }
        return true;
    }

    public void tearCycleMinimumSpanningTree() {
        tearCycleMinimumSpanningTree(value -> {
            System.out.print(value);
            return null;
        });
    }

    public void tearCycleMinimumSpanningTree(Function<String, Void> output) {
        try {
            MaxHeap<Edge<ElemType, WeightType>> heap = new MaxHeap<>(edgeNum);
            Edge<ElemType, WeightType> edge = new Edge<>();
            for (int row = 0; row < vertexNum; row++) {
                for (int col = 0; col < vertexNum; col++) {
                    if (row > col && adjacencyMatrix[row][col] != infinity_) {
                        ElemType v1 = getElem(row);
                        ElemType v2 = getElem(col);
                        edge.setElem(v1, v2, adjacencyMatrix[row][col]);
                        heap.insertElem(edge);
                    }
                }
            }
            int count = 0;
            while (edgeNum > vertexNum - 1) {
                edge = heap.getTop();
                heap.deleteTop();
                deleteEdge(getIndex(edge.vertexA), getIndex(edge.vertexB));
                if (isConnected()) {
                    output.apply("Deleted <" + edge.vertexA + ", " + edge.vertexB + "> , Weight: " + edge.weight + "\n");
                } else {
                    insertEdge(getIndex(edge.vertexA), getIndex(edge.vertexB), edge.weight);
                }
            }
        } catch (BaseException ignored) {
        }
    }

    @Override
    public void copy(Network<ElemType, WeightType> network) {
        if (network instanceof AdjacencyMatrixUndirectedNetwork<ElemType, WeightType> co) {
            vertexNum = co.vertexNum;
            edgeNum = co.edgeNum;
            vertexes = (ElemType[]) new Comparable[co.vertexes.length];
            isVisited = new boolean[co.vertexes.length];
            for (int i = 0; i < vertexNum; i++) {
                vertexes[i] = co.vertexes[i];
                isVisited[i] = co.isVisited[i];
            }
            adjacencyMatrix = (WeightType[][]) new Comparable[co.vertexes.length][co.vertexes.length];
            for (int i = 0; i < co.vertexes.length; i++) {
                adjacencyMatrix[i] = (WeightType[]) new Comparable[co.vertexes.length];
            }
            for (int i = 0; i < vertexNum; i++) {
                System.arraycopy(co.adjacencyMatrix[i], 0, adjacencyMatrix[i], 0, vertexNum);
            }
        }
    }
}