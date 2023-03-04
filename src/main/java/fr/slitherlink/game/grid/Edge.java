package fr.slitherlink.game.grid;

/**
 * @author LE GLEAU Yoann BRUNEAU Antoine
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

    public boolean isLine() {
        return type == EdgeType.LINE;
    }

    public boolean isCross() {
        return type == EdgeType.CROSS;
    }

    public boolean isEmpty() {
        return type == EdgeType.EMPTY;
    }

}
