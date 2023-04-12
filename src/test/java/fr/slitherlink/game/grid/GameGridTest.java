package fr.slitherlink.game.grid;

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
        grid.getCell(0, 0).getRight().setType(EdgeType.LINE);
        assertEquals(EdgeType.LINE, grid.getCell(1, 0).getLeft().getType());

        grid.getCell(0, 0).getBottom().setType(EdgeType.CROSS);
        assertEquals(EdgeType.CROSS, grid.getCell(0, 1).getTop().getType());
    }
}