package fr.slitherlink.game.grid;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class GridCell {

    private Edge[] edges;

    public GridCell(Integer number) {
        edges = new Edge[4];
        for (int i = 0; i < 4; i++) {
            edges[i] = new Edge();
        }
    }

    public Edge getTop() {
        return edges[0];
    }

    public void setTop(Edge top) {
        edges[0] = top;
    }

    public Edge getRight() {
        return edges[1];
    }

    public void setRight(Edge right) {
        edges[1] = right;
    }

    public Edge getBottom() {
        return edges[2];
    }

    public void setBottom(Edge bottom) {
        edges[2] = bottom;
    }

    public Edge getLeft() {
        return edges[3];
    }

    public void setLeft(Edge left) {
        edges[3] = left;
    }

    /**
     * compare tous les cotter de la cellule
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        GridCell cell = (GridCell) obj;
        for (int i = 0; i < 4; i++) {
                if (!edges[i].equals(cell.edges[i]))
                    return false;
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < 4; i++) {
            edges[i].setType(EdgeType.EMPTY);
        }
    }
}
