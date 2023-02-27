package shuhuai.datastructure.graph.adjacencymatrixundirectednextwork;

@SuppressWarnings({"unused"})
public class PrimEdge<WeightType> {
    protected WeightType minWeight;
    protected int adjacencyVertex;

    public PrimEdge() {
        minWeight = null;
        adjacencyVertex = -1;
    }

    public PrimEdge(WeightType minWeight, int adjacencyVertex) {
        this.minWeight = minWeight;
        this.adjacencyVertex = adjacencyVertex;
    }
}