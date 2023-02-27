package shuhuai.datastructure.graph.adjacencylistdirectednetwork;

@SuppressWarnings({"unused"})
public class Edge<WeightType> {
    protected int vertex;
    protected WeightType weight;
    protected Edge<WeightType> nextEdge;

    public Edge() {
        vertex = -1;
        weight = null;
        nextEdge = null;
    }

    public Edge(int vertex, WeightType weight) {
        this.vertex = vertex;
        this.weight = weight;
        this.nextEdge = null;
    }

    public Edge(int vertex, WeightType weight, Edge<WeightType> nextEdge) {
        this.vertex = vertex;
        this.weight = weight;
        this.nextEdge = nextEdge;
    }

    public Edge(Edge<WeightType> edge) {
        vertex = edge.vertex;
        weight = edge.weight;
        nextEdge = edge.nextEdge;
    }
}