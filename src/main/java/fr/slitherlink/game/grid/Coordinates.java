package fr.slitherlink.game.grid;

/**
 * Classe représentant les coordonnées d'une case
 * @author Bruneau Antoine
 * @version 1, 03/03/2023
 */
public class Coordinates {
    
    protected int x;
    protected int y;

    public Coordinates(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}