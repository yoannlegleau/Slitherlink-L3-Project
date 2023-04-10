package fr.slitherlink.save.gamesave;

import fr.slitherlink.save.puzzle.PuzzleSave;

/**
 * @author LE GLEAU Yoann
 * @version 1, 10/04/2023
 */
public interface GameSaver {

    GameSave loadSave();
    PuzzleSave loadPuzzle();
    void save(GameSave gameSave);


}
