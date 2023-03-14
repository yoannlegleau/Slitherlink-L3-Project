package fr.slitherlink.game.action;


/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */


public enum GameActionTypes {
    SET_LINE,
    SET_CROSS,
    SET_EMPTY,
    UNDO,
    REDO,
    RESET,
    WIN;


    public String value() {
        return name();
    }

    public static GameActionTypes fromValue(String v) {
        return valueOf(v);
    }
}
