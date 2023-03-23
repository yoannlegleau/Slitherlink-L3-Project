package fr.slitherlink.game.grid;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class Edge {
    private EdgeType type;

    public Edge() {
        this.type = EdgeType.EMPTY;
    }

    public void setType(EdgeType newType) {
        type = newType;
    }
    public EdgeType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        Edge edge = (Edge) obj;
        return type == edge.type;
    }

}
