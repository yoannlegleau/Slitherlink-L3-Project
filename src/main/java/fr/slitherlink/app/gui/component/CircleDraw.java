package fr.slitherlink.app.gui.component;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CircleDraw extends Circle implements ActionListener {
    private List<DrawingEdge> neighbors;

    private GameColor gameColor;

    public CircleDraw(int x, int y, double radius) {
        super(radius,x,y);
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(DrawingEdge edge) {
        edge.subscribe(this);
        neighbors.add(edge);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //On regarde l'état des EdgeDraws qui sont autour du CircleDraw


        changeColor();
    }

    //On regarde l'état des EdgeDraws qui sont autour du CircleDraw
    private void changeColor() {
        switch (gameColor) {
            case WIN:
                setFill(Color.WHITE);
            case EMPTY:
                setFill( Color.GRAY);
            case LINE:
                setFill(Color.valueOf("66F4FF"));
            case CROSS:
                setFill(Color.valueOf("E39351"));
            case ASSUMPTION_LINE:
                setFill(Color.valueOf("41B641"));
            case ASSUMPTION_CROSS:
                setFill(Color.valueOf("E35151"));
        }
    }
}
