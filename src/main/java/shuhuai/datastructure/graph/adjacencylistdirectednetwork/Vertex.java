package shuhuai.datastructure.graph.adjacencylistdirectednetwork;

@SuppressWarnings({"unused"})
public class Vertex<ElemType, WeightType> {
    protected ElemType elem;
    protected Edge<WeightType> firstEdge;

    public Vertex() {
        elem = null;
        firstEdge = null;
    }

    public Vertex(ElemType elem, Edge<WeightType> firstEdge) {
        this.elem = elem;
        this.firstEdge = firstEdge;
    }

    public Vertex(Vertex<ElemType, WeightType> vertex) {
        elem = vertex.elem;
        firstEdge = vertex.firstEdge;
    }
}