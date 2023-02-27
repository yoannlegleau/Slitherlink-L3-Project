package fr.slitherlink.save;

import fr.slitherlink.Grid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static fr.slitherlink.EdgeType.LINE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @version 2, 23/02/2023
 */
class LevelResourceManageurTest {

    public static Grid grid;

    @BeforeAll
    public static void init(){
        grid = new Grid(4);
        grid.getCell(0,0).setNumber(1);
        grid.getCell(0,2).setNumber(2);
        grid.getCell(1,2).setNumber(0);
        grid.getCell(1,3).setNumber(2);
        grid.getCell(2,1).setNumber(0);
        grid.getCell(2,2).setNumber(1);
        grid.getCell(3,1).setNumber(3);
        grid.getCell(3,3).setNumber(1);

        grid.getCell(0,0).getRight().setType(LINE);
    }


    @Test
    void loadLevel() {
        assertNull(PuzzleResourceManageur.LoadPuzzle(0));
        assertEquals(1, PuzzleResourceManageur.LoadPuzzle(1).getId());
    }

    @Test
    void saveLevel() {
        PuzzleSave level = new PuzzleSave(1,grid);
        PuzzleResourceManageur.saveLevel(level);
    }
}