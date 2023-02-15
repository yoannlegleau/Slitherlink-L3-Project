package fr.slitherlink.save;

import fr.levelEditor.LevelEditor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 14/02/2023
 */

@XmlRootElement
@XmlType(propOrder={"size", "grid"})
public class Level {
    private int id;
    private int size;

    @XmlElement(name="number")
    private List<GridNumber> grid;

    public Level() {
        super();
        grid = new ArrayList<>();
    }

    public Level(int id, int size) {
        this();
        this.id = id;
        this.size = size;
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

    public void setGrid(int number, int row, int column){
        GridNumber target = null;
        for (GridNumber n :grid)
            if (n.row == row && n.column == column) {
                target = n;
                break;
            }
        if (target == null)
            grid.add(new GridNumber(number,row,column));
        else if (number == -1)
            grid.remove(target);
        else target.number = number;

    }

    public int getGrid(int row, int column){
        for (GridNumber n :grid)
            if (n.row == row && n.column == column) return n.number;
        return -1;
    }

    @Override
    public String toString() {
        return this.getClass().toString()+"[id="+id+", name="+ size +"]";
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
