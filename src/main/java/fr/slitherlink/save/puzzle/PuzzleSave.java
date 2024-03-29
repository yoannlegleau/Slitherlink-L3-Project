package fr.slitherlink.save.puzzle;

import fr.slitherlink.game.grid.EdgeType;
import fr.slitherlink.game.grid.Grid;
import fr.slitherlink.game.grid.GridCell;
import fr.slitherlink.save.puzzle.Difficulty;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Object représentant les informations stoker d'un puzzle
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 * @version 2, 23/02/2023
 */

@XmlRootElement
@XmlType(propOrder={"size","difficulty", "grid", "solution"})
public class PuzzleSave {
    private int id;
    private int size;
    private Difficulty difficulty;

    @XmlElement
    private GridSave grid;

    @XmlElement
    private Solution solution;

    public PuzzleSave() {
        super();
        grid = new GridSave();
    }

    public PuzzleSave(int id){
        this();
        this.id = id;
    }

    public PuzzleSave(int id, Grid grid, Integer[][] numbers) {
        this(id);
        this.size = grid.getSize();
        this.difficulty = Difficulty.UNKNOWN;
        setGameGrid(grid, numbers);
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "PuzzleSave{" +
                "id=" + id +
                ", size=" + size +
                ", difficulty=" + difficulty +
                ", grid=" + grid +
                ", solution=" + solution +
                '}';
    }
    public void setGrid(Integer[][] grid) {
        this.size = grid.length;
        this.grid = new GridSave(grid);
    }

    public Integer[][] getGridNumbers() {
        Integer[][] returnGrid = new Integer[size][size];
        for (GridSave.GridNumber number : grid.getNumberList()) {
            returnGrid[number.getRow()][number.getColumn()] = number.getNumber();
        }
        return returnGrid;
    }
    public Grid getSolution() {
        Grid g = new Grid(size);
        if (solution != null)
            for (Solution.SaveEdge edge : solution.getEdgeList()) {
                switch (edge.direction) {
                    case "T" -> g.getCell(edge.row, edge.column).getTop().setType(EdgeType.LINE);
                    case "B" -> g.getCell(edge.row, edge.column).getBottom().setType(EdgeType.LINE);
                    case "L" -> g.getCell(edge.row, edge.column).getLeft().setType(EdgeType.LINE);
                    case "R" -> g.getCell(edge.row, edge.column).getRight().setType(EdgeType.LINE);
                }
            }
        return g;
    }

    public void setGameGrid(Grid newGrid, Integer[][] gridNumbers) {
        grid = new GridSave(gridNumbers);
        solution = new Solution(newGrid);
    }




    @XmlRootElement(name = "grid")
    private static class GridSave {
        @XmlElement(name="number")
        private List<GridNumber> numberList;

        public GridSave() {
            super();
            numberList = new ArrayList<>();
        }



        public GridSave(Integer[][] gridNumbers) {
            this();
            for (int x = 0; x < gridNumbers.length; x++) {
                for (int y = 0; y < gridNumbers.length; y++) {
                    if (gridNumbers[x][y] != null)
                        numberList.add(new GridNumber(gridNumbers[x][y], x, y));
                }
            }
        }

        public List<GridNumber> getNumberList() {
            return numberList;
        }

        public void initGrid(List<GridNumber> grid) {
            this.numberList = grid;
        }

        @XmlRootElement(name = "number")
        private static class GridNumber {

            int number;

            int row ;

            int column;

            public GridNumber() {
                super();
            }

            public GridNumber(int number, int row, int c) {
                this();
                this.number = number;
                this.row = row;
                this.column = c;
            }

            @XmlValue
            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            @XmlAttribute
            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            @XmlAttribute
            public int getColumn() {
                return column;
            }

            public void setColumn(int column) {
                this.column = column;
            }
        }

    }

    @XmlRootElement
    private static class Solution {

        @XmlElement(name="edge")
        private List<SaveEdge> edgeList;

        public Solution() {
            super();
            edgeList = new ArrayList<>();
        }

        public Solution(Grid gameGrid) {
            this();
                for (int x = 0; x < gameGrid.getSize(); x++) {
                    for (int y = 0; y < gameGrid.getSize(); y++) {
                    GridCell cell = gameGrid.getCell(y, x);
                    if (x == 0 && cell.getTop().getType().equals(EdgeType.LINE))
                        edgeList.add(new SaveEdge(x, y, "T"));
                    if (y == 0 && cell.getLeft().getType().equals(EdgeType.LINE))
                        edgeList.add(new SaveEdge(x, y, "L"));
                    if (cell.getRight().getType().equals(EdgeType.LINE))
                        edgeList.add(new SaveEdge(x, y, "R"));
                    if (cell.getBottom().getType().equals(EdgeType.LINE))
                        edgeList.add(new SaveEdge(x, y, "B"));
                }
            }
        }

        public List<SaveEdge> getEdgeList() {
            return edgeList;
        }

        @XmlRootElement(name = "edge")
        private static class SaveEdge {

            private int row;
            private int column;
            private String direction;


            public SaveEdge() {
                super();
            }
            public SaveEdge(int row, int column, String direction) {
                this();
                this.row = row;
                this.column = column;
                this.direction = direction;
            }

            @XmlAttribute
            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            @XmlAttribute
            public int getColumn() {
                return column;
            }


            public void setColumn(int column) {
                this.column = column;
            }

            @XmlValue
            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }
        }
    }


}
