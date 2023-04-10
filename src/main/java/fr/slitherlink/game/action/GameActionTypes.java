package fr.slitherlink.game.action;


/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */


public enum GameActionTypes {
    SET_LINE, SET_CROSS, SET_EMPTY,
    UNDO, REDO,
    HIGHLIGTH_HELP, NEW_HELP,
    ASSUMPTION_START, ASSUMPTION_VALID, ASSUMPTION_CANCEL,
    RESET, WIN;
}
