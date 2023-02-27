package shuhuai.datastructure.network.adjacencylistdirectednetwork;

import shuhuai.datastructure.array.Array;
import shuhuai.datastructure.exceptions.*;
import shuhuai.datastructure.network.Network;
import shuhuai.datastructure.queue.Queue;
import shuhuai.datastructure.queue.linkqueue.LinkQueue;
import shuhuai.datastructure.stack.Stack;
import shuhuai.datastructure.stack.linkstack.LinkStack;

import java.util.function.Function;

@SuppressWarnings({"unused", "unchecked"})
public class AdjacencyListDirectedNetwork<ElemType, WeightType extends Comparable<WeightType>> implements Network<ElemType, WeightType> {
    protected int vertexNum_;
    protected int edgeNum_;
    protected Array<Vertex<ElemType, WeightType>> vertexes_;
    protected boolean[] isVisited_;
    protected WeightType infinity_;

    public AdjacencyListDirectedNetwork(int maxVertexNum, WeightType infinity) {
        vertexNum_ = 0;
        edgeNum_ = 0;
        infinity_ = infinity;
        vertexes_ = new Array<>(maxVertexNum);
        isVisited_ = new boolean[maxVertexNum];
    }

    public AdjacencyListDirectedNetwork(ElemType[] vertexes, int maxVertexNum, WeightType infinity) {
        vertexNum_ = vertexes.length;
        edgeNum_ = 0;
        infinity_ = infinity;
        vertexes_ = new Array<>(maxVertexNum);
        isVisited_ = new boolean[maxVertexNum];
    }

    public AdjacencyListDirectedNetwork(AdjacencyListDirectedNetwork<ElemType, WeightType> network) {
        vertexNum_ = network.vertexNum_;
        edgeNum_ = network.edgeNum_;
        infinity_ = network.infinity_;
        vertexes_ = new Array<>(network.vertexes_.getLength());
        isVisited_ = new boolean[network.isVisited_.length];
        for (int i = 0; i < vertexNum_; i++) {
            vertexes_.set(i, new Vertex<>(network.vertexes_.get(i).elem, null));
            Edge<WeightType> p = network.vertexes_.get(i).firstEdge;
            Edge<WeightType> q = new Edge<>();
            while (p != null) {
                if (vertexes_.get(i).firstEdge == null) {
                    vertexes_.get(i).firstEdge = new Edge<>(p.vertex, p.weight);
                    q = vertexes_.get(i).firstEdge;
                } else {
                    q.nextEdge = new Edge<>(p.vertex, p.weight);
                    q = q.nextEdge;
                }
                p = p.nextEdge;
            }
            isVisited_[i] = network.isVisited_[i];
        }
    }

    @Override
    public void clear() {
        Edge<WeightType> p;
        for (int i = 0; i < vertexNum_; i++) {
            p = vertexes_.get(i).firstEdge;
            while (p != null) {
                vertexes_.get(i).firstEdge = p.nextEdge;
                p = vertexes_.get(i).firstEdge;
            }
        }
        vertexNum_ = 0;
        edgeNum_ = 0;
    }

    @Override
    public boolean isEmpty() {
        return vertexNum_ == 0;
    }

    @Override
    public void traverse() {
        traverse(value -> {
            System.out.print(value);
            return null;
        });
    }

    @Override
    public void traverse(Function<String, Void> output) {
        for (int i = 0; i < vertexNum_; i++) {
            output.apply(i + ":\t" + vertexes_.get(i).elem);
            Edge<WeightType> p = vertexes_.get(i).firstEdge;
            while (p != null) {
                output.apply(" ->( " + p.vertex + ", " + p.weight + " )");
                p = p.nextEdge;
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
        for (int i = 0; i < vertexNum_; i++) {
            isVisited_[i] = false;
        }
        for (int i = 0; i < vertexNum_; i++) {
            if (!isVisited_[i]) {
                depthFirstSearch(i, output);
            }
        }
    }

    @Override
    public void depthFirstSearch(int vertex, Function<String, Void> output) {
        output.apply(vertexes_.get(vertex).elem + " ");
        isVisited_[vertex] = true;
        for (int i = getFirstAdjacencyVertex(vertex); i != -1; i = getNextAdjacencyVertex(vertex, i)) {
            if (!isVisited_[i]) {
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
        for (int i = 0; i < vertexNum_; i++) {
            isVisited_[i] = false;
        }
        for (int i = 0; i < vertexNum_; i++) {
            if (!isVisited_[i]) {
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
                output.apply(vertexes_.get(vertex).elem + " ");
                isVisited_[vertex] = true;
                for (int i = getFirstAdjacencyVertex(curVertex); i != -1; i = getNextAdjacencyVertex(curVertex, i)) {
                    queue.enQueue(i);
                }
            }
        } catch (BaseException ignored) {
        }
    }

    @Override
    public void appendVertex(ElemType elem) throws OverFlowException {
        if (vertexNum_ == vertexes_.getCapacity()) {
            throw new OverFlowException("空间已满");
        }
        vertexes_.set(vertexNum_, new Vertex<>(elem, null));
        isVisited_[vertexNum_] = false;
        vertexNum_++;
    }

    @Override
    public void insertEdge(int tailVertex, int headVertex, WeightType weight) throws RangeException {
        if (tailVertex < 0 || tailVertex >= vertexNum_ || headVertex < 0 || headVertex >= vertexNum_) {
            throw new RangeException("范围错误");
        }
        if (tailVertex == headVertex) {
            throw new RangeException("范围错误");
        }
        Edge<WeightType> p = vertexes_.get(tailVertex).firstEdge;
        vertexes_.get(tailVertex).firstEdge = new Edge<>(headVertex, weight, p);
        edgeNum_++;
    }

    @Override
    public void deleteVertex(ElemType elem) throws UnderFlowException, NotExistException {
        if (vertexNum_ == 0) {
            throw new UnderFlowException("空间空");
        }
        int vertex = getIndex(elem);
        if (vertex == -1) {
            throw new NotExistException("节点不存在");
        }
        for (int i = 0; i < vertexNum_; i++) {
            if (i != vertex) {
                try {
                    deleteEdge(i, vertex);
                } catch (BaseException ignored) {
                }
            }
        }
        Edge<WeightType> p = vertexes_.get(vertex).firstEdge;
        while (p != null) {
            vertexes_.get(vertex).firstEdge = p.nextEdge;
            p = vertexes_.get(vertex).firstEdge;
            edgeNum_--;
        }
        vertexes_.set(vertex, new Vertex<>(vertexes_.get(vertexNum_)));
        vertexes_.set(vertexNum_, new Vertex<>());
        isVisited_[vertex] = isVisited_[vertexNum_];
        for (int i = 0; i < vertexNum_; i++) {
            if (i != vertex) {
                p = vertexes_.get(i).firstEdge;
                while (p != null) {
                    if (p.vertex == vertexNum_) {
                        p.vertex = vertex;
                    }
                    p = p.nextEdge;
                }
            }
        }
        vertexNum_--;
    }

    @Override
    public void deleteEdge(int tailVertex, int headVertex) throws RangeException {
        if (tailVertex < 0 || tailVertex >= vertexNum_ || headVertex < 0 || headVertex >= vertexNum_) {
            throw new RangeException("范围错误");
        }
        if (tailVertex == headVertex) {
            throw new RangeException("范围错误");
        }
        Edge<WeightType> p = vertexes_.get(tailVertex).firstEdge;
        Edge<WeightType> q = new Edge<>();
        while (p != null && p.vertex != headVertex) {
            q = p;
            p = p.nextEdge;
        }
        if (p != null) {
            if (vertexes_.get(tailVertex).firstEdge.equals(p)) {
                vertexes_.get(tailVertex).firstEdge = p.nextEdge;
            } else {
                q.nextEdge = p.nextEdge;
            }
            edgeNum_--;
        }
    }

    @Override
    public void setElem(int vertex, ElemType elem) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum_) {
            throw new RangeException("范围错误");
        }
        vertexes_.get(vertex).elem = elem;
    }

    @Override
    public void setVisited(int vertex, boolean isVisited) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum_) {
            throw new RangeException("范围错误");
        }
        this.isVisited_[vertex] = isVisited;
    }

    @Override
    public void setWeight(int tailVertex, int headVertex, WeightType weight) throws RangeException {
        if (tailVertex < 0 || tailVertex >= vertexNum_ || headVertex < 0 || headVertex >= vertexNum_) {
            throw new RangeException("范围错误");
        }
        if (tailVertex == headVertex) {
            throw new RangeException("范围错误");
        }
        Edge<WeightType> p = vertexes_.get(tailVertex).firstEdge;
        while (p != null && p.vertex != headVertex) {
            p = p.nextEdge;
        }
        if (p != null) {
            p.weight = weight;
        }
    }

    @Override
    public int getVertexNum() {
        return vertexNum_;
    }

    @Override
    public int getEdgeNum() {
        return edgeNum_;
    }

    @Override
    public int getIndex(ElemType data) {
        for (int i = 0; i < vertexNum_; i++) {
            if (vertexes_.get(i).elem.equals(data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ElemType getElem(int vertex) throws RangeException {
        if (vertex < 0 || vertex >= vertexNum_) {
            throw new RangeException("范围错误");
        }
        return vertexes_.get(vertex).elem;
    }

    @Override
    public boolean isVisited(int vertex) {
        return isVisited_[vertex];
    }

    @Override
    public int getFirstAdjacencyVertex(int vertex) {
        if (vertexes_.get(vertex).firstEdge == null) {
            return -1;
        }
        return vertexes_.get(vertex).firstEdge.vertex;
    }

    @Override
    public int getNextAdjacencyVertex(int tailVertex, int headVertex) {
        Edge<WeightType> p = vertexes_.get(tailVertex).firstEdge;
        while (p != null && p.vertex != headVertex) {
            p = p.nextEdge;
        }
        if (p == null || p.nextEdge == null) {
            return -1;
        }
        return p.nextEdge.vertex;
    }

    @Override
    public WeightType getInfinity() {
        return infinity_;
    }

    @Override
    public WeightType getWeight(int tailVertex, int headVertex) {
        Edge<WeightType> p = vertexes_.get(tailVertex).firstEdge;
        while (p != null && p.vertex != headVertex) {
            p = p.nextEdge;
        }
        if (p != null) {
            return p.weight;
        }
        return infinity_;
    }

    public void dijkstraShortestPath(int sourceVertex, Function<String, Void> output) {
        int[] path = new int[vertexNum_];
        WeightType[] distance = (WeightType[]) new Comparable[vertexNum_];
        for (int i = 0; i < vertexNum_; i++) {
            distance[i] = getWeight(sourceVertex, i);
            if (distance[i] == infinity_) {
                path[i] = -1;
            } else {
                path[i] = sourceVertex;
            }
            isVisited_[i] = false;
        }
        isVisited_[sourceVertex] = true;
        for (int i = 0; i < vertexNum_ - 1; i++) {
            WeightType minimumWeight = infinity_;
            int vertex = sourceVertex;
            for (int j = 0; j < vertexNum_; j++) {
                if (!isVisited_[j] && distance[j].compareTo(minimumWeight) < 0) {
                    vertex = j;
                    minimumWeight = distance[j];
                }
            }
            isVisited_[vertex] = true;
            for (int j = getFirstAdjacencyVertex(vertex); j != -1; j = getNextAdjacencyVertex(vertex, j)) {
                if (!isVisited_[j]) {
                    if (((WeightType) add(minimumWeight, getWeight(vertex, j))).compareTo(distance[j]) < 0) {
                        distance[j] = (WeightType) add(minimumWeight, getWeight(vertex, j));
                        path[j] = vertex;
                    }
                }
            }
        }
        output.apply("Path: ");
        for (int i = 0; i < vertexNum_; i++) {
            output.apply(path[i] + " ");
        }
        output.apply("\nDistance: ");
        for (int i = 0; i < vertexNum_; i++) {
            output.apply(distance[i] + " ");
        }
    }

    private Object add(WeightType weightA, WeightType weightB) {
        if (weightA instanceof Number && weightB instanceof Number) {
            return ((Number) weightA).doubleValue() + ((Number) weightB).doubleValue();
        }
        return 0;
    }

    private Object minus(WeightType weightA, WeightType weightB) {
        if (weightA instanceof Number && weightB instanceof Number) {
            return ((Number) weightA).doubleValue() - ((Number) weightB).doubleValue();
        }
        return 0;
    }

    public int getInDegree(int vertex) {
        int inDegree = 0;
        for (int i = 0; i < vertexNum_; i++) {
            Edge<WeightType> p = vertexes_.get(i).firstEdge;
            while (p != null) {
                if (p.vertex == vertex) {
                    inDegree++;
                }
                p = p.nextEdge;
            }
        }
        return inDegree;
    }

    public void topologicalSort() throws NotExistException {
        topologicalSort(value -> {
            System.out.print(value);
            return null;
        });
    }

    public void topologicalSort(Function<String, Void> output) throws NotExistException {
        try {
            int[] inDegrees = new int[vertexNum_];
            Stack<Integer> s = new LinkStack<>();
            for (int i = 0; i < vertexNum_; i++) {
                inDegrees[i] = getInDegree(i);
                if (inDegrees[i] == 0) {
                    s.push(i);
                }
            }
            int count = 0;
            while (!s.isEmpty()) {
                int vertex = s.pop();
                output.apply(vertexes_.get(vertex).elem + " ");
                count++;
                for (int i = getFirstAdjacencyVertex(vertex); i != -1; i = getNextAdjacencyVertex(vertex, i)) {
                    inDegrees[i]--;
                    if (inDegrees[i] == 0) {
                        s.push(i);
                    }
                }
            }
            if (count < vertexNum_) {
                throw new NotExistException("有环");
            }
        } catch (BaseException error) {
            if (error instanceof NotExistException) {
                throw (NotExistException) error;
            }
        }
    }

    public void criticalPath() throws NotExistException {
        criticalPath(value -> {
            System.out.print(value);
            return null;
        });
    }

    public void criticalPath(Function<String, Void> output) throws NotExistException {
        try {
            int[] inDegrees = new int[vertexNum_];
            Stack<Integer> s = new LinkStack<>();
            Queue<Integer> q = new LinkQueue<>();
            WeightType[] vertexEarly = (WeightType[]) new Comparable[vertexNum_];
            for (int i = 0; i < vertexNum_; i++) {
                inDegrees[i] = getInDegree(i);
                if (inDegrees[i] == 0) {
                    q.enQueue(i);
                }
                vertexEarly[i] = null;
            }
            int count = 0;
            int vertex;
            while (!q.isEmpty()) {
                vertex = q.deQueue();
                s.push(vertex);
                count++;
                for (int i = getFirstAdjacencyVertex(vertex); i != -1; i = getNextAdjacencyVertex(vertex, i)) {
                    inDegrees[i]--;
                    if (inDegrees[i] == 0) {
                        q.enQueue(i);
                    }
                    if (((WeightType) add(vertexEarly[vertex], getWeight(vertex, i))).compareTo(vertexEarly[i]) > 0) {
                        vertexEarly[i] = (WeightType) add(vertexEarly[vertex], getWeight(vertex, i));
                    }
                }
            }
            if (count < vertexNum_) {
                throw new NotExistException("有环");
            }
            vertex = s.pop();
            WeightType[] vertexLate = (WeightType[]) new Comparable[vertexNum_];
            for (int i = 0; i < vertexNum_; i++) {
                vertexLate[i] = vertexEarly[vertex];
            }
            while (!s.isEmpty()) {
                vertex = s.pop();
                for (int i = getFirstAdjacencyVertex(vertex); i != -1; i = getNextAdjacencyVertex(vertex, i)) {
                    if (((WeightType) minus(vertexLate[i], getWeight(vertex, i))).compareTo(vertexLate[vertex]) < 0) {
                        vertexLate[vertex] = (WeightType) minus(vertexLate[i], getWeight(vertex, i));
                    }
                }
            }
            Array<WeightType> early = new Array<>(edgeNum_);
            Array<WeightType> late = new Array<>(edgeNum_);
            for (vertex = 0; vertex < vertexNum_; vertex++) {
                for (int i = getFirstAdjacencyVertex(vertex); i != -1; i = getNextAdjacencyVertex(vertex, i)) {
                    WeightType ee = vertexEarly[vertex];
                    WeightType el = (WeightType) minus(vertexLate[i], getWeight(vertex, i));
                    early.append(ee);
                    late.append(el);
                    if (ee == el) {
                        output.apply("<" + vertexes_.get(vertex).elem + vertexes_.get(i).elem + "> ");
                    }
                }
            }
            output.apply("\nve: ");
            for (int i = 0; i < vertexNum_; i++) {
                output.apply(vertexEarly[i] + " ");
            }
            output.apply("\nvl: ");
            for (int i = 0; i < vertexNum_; i++) {
                output.apply(vertexLate[i] + " ");
            }
            output.apply("\nelem: ");
            for (int i = 0; i < edgeNum_; i++) {
                output.apply(early.get(i) + " ");
            }
            output.apply("\nl: ");
            for (int i = 0; i < edgeNum_; i++) {
                output.apply(late.get(i) + " ");
            }
        } catch (BaseException error) {
            if (error instanceof NotExistException) {
                throw (NotExistException) error;
            }
        }
    }

    @Override
    public void copy(Network<ElemType, WeightType> network) {
        if (network instanceof AdjacencyListDirectedNetwork<ElemType, WeightType> co) {
            clear();
            vertexNum_ = co.vertexNum_;
            edgeNum_ = co.edgeNum_;
            infinity_ = co.infinity_;
            vertexes_ = new Array<>(co.vertexes_.getCapacity());
            isVisited_ = new boolean[co.isVisited_.length];
            for (int i = 0; i < vertexNum_; i++) {
                vertexes_.get(i).elem = co.vertexes_.get(i).elem;
                vertexes_.get(i).firstEdge = null;
                Edge<WeightType> p = co.vertexes_.get(i).firstEdge;
                Edge<WeightType> q = new Edge<>();
                while (p != null) {
                    if (vertexes_.get(i).firstEdge == null) {
                        vertexes_.get(i).firstEdge = new Edge<>(p.vertex, p.weight);
                        q = vertexes_.get(i).firstEdge;
                    } else {
                        q.nextEdge = new Edge<>(p.vertex, p.weight);
                        q = q.nextEdge;
                    }
                    p = p.nextEdge;
                }
                isVisited_[i] = co.isVisited_[i];
            }
        }
    }
}