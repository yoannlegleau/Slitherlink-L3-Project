package fr.slitherlink.save;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @pakage fr.slitherlink.save
 */
class LevelResourceManageurTest {

    @Test
    void loadLevel() {
        assertNull(PuzzleResourceManageur.LoadPuzzle(0));
        assertEquals(1, PuzzleResourceManageur.LoadPuzzle(1).getId());
    }

    @Test
    void saveLevel() {
        Puzzle level = new Puzzle(1,6);
        PuzzleResourceManageur.saveLevel(level);
    }
}