package fr.slitherlink.app.component;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.actions.AssumptionStart;
import fr.slitherlink.game.action.actions.AssumptionStop;
import fr.slitherlink.game.grid.EdgeType;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */
class DrawingEdge extends Rectangle implements ActionListener {

    private int coodonatX;
    private int coodonatY;
    private String direction;

    private EdgeType edgeType;

    private Boolean isGameAssumption;
    private boolean isAssumption;

    private boolean isWin;

    public DrawingEdge(double x, double y, double width, double height, int coodonatX, int coodonatY, String direction, Game game) {
        super(x, y, width, height);
        this.coodonatX = coodonatX;
        this.coodonatY = coodonatY;
        this.direction = direction;
        this.isGameAssumption = false;
        this.isAssumption = false;
        this.isWin = false;

        game.subscribe(this);
        switch (direction) {
            case "T" -> game.getCurrentGrid().getCell(coodonatX, coodonatY).getTop().subscribe(this);
            case "B" -> game.getCurrentGrid().getCell(coodonatX, coodonatY).getBottom().subscribe(this);
            case "L" -> game.getCurrentGrid().getCell(coodonatX, coodonatY).getLeft().subscribe(this);
            case "R" -> game.getCurrentGrid().getCell(coodonatX, coodonatY).getRight().subscribe(this);
        }

        // TODO Recupere la couleur dans le css
        edgeType = EdgeType.EMPTY;
        setFill(Color.GRAY);

        setOnMouseClicked(event -> {
            EdgeType newTipe = EdgeType.EMPTY;

            isAssumption = game.isAssumptionMode();

            if (event.getButton() == MouseButton.PRIMARY)
                if (edgeType.equals(EdgeType.LINE))
                    game.action(ActionFactory.setEdgeEmpty(coodonatX, coodonatY, direction));
                else
                    game.action(ActionFactory.setEdgeLine(coodonatX, coodonatY, direction));
            else if (event.getButton() == MouseButton.SECONDARY)
                if (edgeType.equals(EdgeType.CROSS))
                    game.action(ActionFactory.setEdgeEmpty(coodonatX, coodonatY, direction));
                else
                    game.action(ActionFactory.setEdgeCross(coodonatX, coodonatY, direction));
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "CROSS" -> {
                isAssumption = isGameAssumption;
                edgeType = EdgeType.CROSS;
                updateColor();
            }
            case "LINE" -> {
                isAssumption = isGameAssumption;
                edgeType = EdgeType.LINE;
                updateColor();
            }
            case "EMPTY"  -> {
                isAssumption = isGameAssumption;
                edgeType = EdgeType.EMPTY;
                updateColor();
            }
            case "ASSUMPTION_START" -> {
                isGameAssumption = true;
                updateColor();
            }
            case "ASSUMPTION_VALID", "ASSUMPTION_CANCEL" -> {
                isGameAssumption = false;
                isAssumption = false;
                updateColor();
            }
            case "RESET" -> {
                isGameAssumption = false;
                isWin = false;
                updateColor();
            }
            case "WIN" -> {
                isWin = true;
                updateColor();
            }

        }

    }


    private void updateColor() {
        //TODO recupere la couleur dans le css
        switch (edgeType){
            case EMPTY:
                setFill(Color.GRAY);
                break;
            case LINE:
                if (isWin)
                    setFill(Color.WHITE);
                else if ( isGameAssumption && isAssumption )
                    setFill(Color.valueOf("41B641"));
                else
                    setFill(Color.valueOf("66F4FF"));
                break;
            case CROSS:
                if (isWin)
                    setFill(Color.GRAY);
                else if ( isGameAssumption && isAssumption )
                    setFill(Color.valueOf("E39351"));
                else
                    setFill(Color.valueOf("E35151"));
                break;
        }
    }

}
