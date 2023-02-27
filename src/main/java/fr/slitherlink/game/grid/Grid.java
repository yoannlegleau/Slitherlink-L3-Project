package fr.slitherlink.game.grid;

/**
 * @author LE GLEAU Yoann
 * @version 1, 23/02/2023
 */
public class Grid {

    private GridCell[][] grid;

    public Grid(int size) {
        grid = new GridCell[size][size];
        initGrid(size);
    }

    private void initGrid(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new GridCell(null);
                if (x > 0)
                    grid[x][y].setLeft(grid[x - 1][y].getRight());
                if (y > 0)
                    grid[x][y].setTop(grid[x][y - 1].getBottom());
            }
        }
    }

    public GridCell getCell(int x, int y) {
        return grid[x][y];
    }

    public int getSize(){
        return grid.length;
    }

}
