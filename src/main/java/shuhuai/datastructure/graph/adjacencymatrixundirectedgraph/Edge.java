package shuhuai.datastructure.graph.adjacencymatrixundirectedgraph;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "unchecked"})
public class Edge<ElemType, WeightType extends Comparable<WeightType>> implements Comparable<Object> {
    protected ElemType vertexA, vertexB;
    protected WeightType weight;

    public Edge() {
        vertexA = null;
        vertexB = null;
        weight = null;
    }

    public Edge(ElemType vertexA, ElemType vertexB, WeightType weight) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
    }

    public void setElem(ElemType vertexA, ElemType vertexB, WeightType weight) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
    }

    @Override
    public int compareTo(@NotNull Object edge) {
        return weight.compareTo(((Edge<ElemType, WeightType>) edge).weight);
    }
}