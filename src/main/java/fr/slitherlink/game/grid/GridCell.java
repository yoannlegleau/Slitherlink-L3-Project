package fr.slitherlink.game.grid;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class GridCell {

    private Integer number;

    private Edge[] edges;

    public GridCell(Integer number) {
        this.number = number;
        edges = new Edge[4];
        for (int i = 0; i < 4; i++) {
            edges[i] = new Edge();
        }
    }

    public Integer getNumber() {
        return number;
    }

    /**
     * Modifie la valeur de la cellule. À utiliser uniquement dans le level editor
     * @param number
     */
    public void setNumber(Integer number) {
        this.number = number;
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
}
