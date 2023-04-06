package fr.slitherlink.save;

import fr.slitherlink.game.grid.Grid;
import fr.slitherlink.save.puzzle.PuzzleResourceManageur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @version 2, 23/02/2023
 */
class LevelResourceManageurTest {

    public static Grid gridSolution;
    public static Integer gridNumber;

    @BeforeAll
    public static void init(){
        //TODO
    }


    @Test
    void loadLevel() {
        assertNull(PuzzleResourceManageur.LoadPuzzle(0));
        assertEquals(1, PuzzleResourceManageur.LoadPuzzle(1).getId());
    }

    @Test
    void saveLevel() {
        //TODO
    }
}