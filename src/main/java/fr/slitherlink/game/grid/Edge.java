package fr.slitherlink.game.grid;

import javafx.beans.value.ChangeListener;

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

    /**
     * On compare les types des deux edges et on ne prand en compte que les lignes
     * @param obj Edge a comparer
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Edge edge = (Edge) obj;
        if (type == EdgeType.LINE && edge.type != EdgeType.LINE)
            return false;
        if (type != EdgeType.LINE && edge.type == EdgeType.LINE)
            return false;
        return true;
    }

}
