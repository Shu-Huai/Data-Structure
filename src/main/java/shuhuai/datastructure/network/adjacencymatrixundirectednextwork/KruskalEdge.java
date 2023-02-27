package shuhuai.datastructure.network.adjacencymatrixundirectednextwork;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "unchecked"})
public class KruskalEdge<ElemType, WeightType extends Comparable<WeightType>> implements Comparable<Object> {
    protected ElemType vertexA;
    protected ElemType vertexB;
    protected WeightType weight;

    public KruskalEdge() {
        vertexA = null;
        vertexB = null;
        weight = null;
    }

    public KruskalEdge(ElemType vertexA, ElemType vertexB, WeightType weight) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
    }

    public ElemType GetVertexA() {
        return vertexA;
    }

    public ElemType GetVertexB() {
        return vertexB;
    }

    public WeightType GetWeight() {
        return weight;
    }


    public void copy(KruskalEdge<ElemType, WeightType> edge) {
        if (edge == null) {
            return;
        }
        vertexA = edge.vertexA;
        vertexB = edge.vertexB;
        weight = edge.weight;
    }

    @Override
    public int compareTo(@NotNull Object edge) {
        return weight.compareTo(((KruskalEdge<ElemType, WeightType>) edge).weight);
    }
}