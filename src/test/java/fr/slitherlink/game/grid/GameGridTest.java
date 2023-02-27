package fr.slitherlink.game.grid;

import fr.slitherlink.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
class GameGridTest {

    private static Grid grid;

    @BeforeEach
    void initGrid() {
        grid = new Grid(3);
    }

    @Test
    void getSetCell() {
        assertNotNull(grid.getCell(0, 0));
    }

    @Test
    void getSetEdgeValue() {
        assertEquals(grid.getCell(1, 0).getLeft() , grid.getCell(0, 0).getRight());
    }
}