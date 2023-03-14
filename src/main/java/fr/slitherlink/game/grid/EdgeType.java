package fr.slitherlink.game.grid;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public enum EdgeType {
    LINE, CROSS, EMPTY;

    public static int getValue(EdgeType type) {
        return switch (type) {
            case LINE -> 1;
            case CROSS -> 2;
            case EMPTY -> 0;
        };
    }

    public static EdgeType getType(int value) {
        return switch (value) {
            case 1 -> LINE;
            case 2 -> CROSS;
            case 0 -> EMPTY;
            default -> throw new IllegalArgumentException("Invalid value");
        };
    }


}
