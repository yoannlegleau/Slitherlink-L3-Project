package fr.slitherlink.game.grid;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 */
class GridTest {

    static Grid grid , grid2;


    @BeforeAll
    static void setUp() {
        grid = new Grid(3);
        grid2 = new Grid(3); // equals to grid 1

        grid.getCell(0,0).getRight().setType(EdgeType.LINE);
        grid2.getCell(0,0).getRight().setType(EdgeType.LINE);

    }

    @Test
    void testEquals() {
        assertTrue(grid.equals(grid2));
        assertFalse(grid.equals(new Grid(4)));
    }
}