package fr.slitherlink.game.help;

/**
 * Classe représentant les coordonnées d'une case
 * @author Bruneau Antoine
 * @version 1, 03/03/2023
 */
public class Coordinates {
    
    private int x;
    private int y;

    public Coordinates(int x, int y) {
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