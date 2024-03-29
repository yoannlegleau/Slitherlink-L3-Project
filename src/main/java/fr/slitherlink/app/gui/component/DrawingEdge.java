package fr.slitherlink.app.gui.component;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.action.GameAction;
import fr.slitherlink.game.action.actions.AssumptionStart;
import fr.slitherlink.game.action.actions.AssumptionStop;
import fr.slitherlink.game.grid.Edge;
import fr.slitherlink.game.grid.EdgeType;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 15/03/2023
 */
class DrawingEdge extends Group implements ActionListener {

    //Composent Grafiques
    private Line line;

    private Polygon clickZone;

    private Edge edge;
    private int coodonatX;
    private int coodonatY;
    private String direction;

    private EdgeType edgeType;

    private Boolean isGameAssumption;
    private boolean isAssumption;

    private boolean isWin;

    private GameColor gameColor;
    private List<ActionListener> subscribeur;

    public DrawingEdge(double x, double y, double width, double height, int coodonatX, int coodonatY, String direction, Game game) {
        super();

        //Initialisation de la ligne
        double len;
        line = new Line();
        if (width < height) {
            len = height;
            Double shift = width / 2;
            line.setStartX(x + shift);
            line.setStartY(y );
            line.setEndX(x + shift);
            line.setEndY(y + height);
            line.setStrokeWidth(width);
        } else {
            len = width;
            Double shift = height / 2;
            line.setStartX(x);
            line.setStartY(y + shift);
            line.setEndX(x + width);
            line.setEndY(y + shift);
            line.setStrokeWidth(height);
        }
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        getChildren().add(line);

        double[] diamondPoints =null;
        if (width > height)
            diamondPoints = new double[]{
                    line.getStartX(), line.getStartY(),
                    line.getStartX() + len / 2, line.getStartY() - len / 2,
                    line.getEndX(), line.getEndY(),
                    line.getStartX() + len / 2, line.getStartY() + len / 2
            };
        else diamondPoints = new double[]{
                    line.getStartX(), line.getStartY(),
                    line.getStartX() - len / 2, line.getStartY() + len / 2,
                    line.getEndX(), line.getEndY(),
                    line.getStartX() + len / 2, line.getStartY() + len / 2
            };

        clickZone = new Polygon(diamondPoints);
        clickZone.setFill(Color.TRANSPARENT);
        getChildren().add(clickZone);

        this.coodonatX = coodonatX;
        this.coodonatY = coodonatY;
        this.direction = direction;
        this.isGameAssumption = false;
        this.isAssumption = false;
        this.isWin = false;
        subscribeur = new ArrayList<>();

        game.subscribe(this);
        switch (direction) {
            case "T" -> this.edge = game.getCurrentGrid().getCell(coodonatY, coodonatX).getTop();
            case "B" -> this.edge = game.getCurrentGrid().getCell(coodonatY, coodonatX).getBottom();
            case "L" -> this.edge = game.getCurrentGrid().getCell(coodonatY, coodonatX).getLeft();
            case "R" -> this.edge = game.getCurrentGrid().getCell(coodonatY, coodonatX).getRight();
        }
        edge.subscribe(this);

        edgeType = edge.getType();
        updateColor();

        clickZone.setOnMouseClicked(event -> {
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

    public Edge getEdge() {
        return edge;
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
                gameColor = GameColor.EMPTY;
                break;
            case LINE:
                if (isWin)
                    gameColor = GameColor.WIN;
                else if ( isGameAssumption && isAssumption )
                    gameColor = GameColor.ASSUMPTION_LINE;
                else
                    gameColor = GameColor.LINE;
                break;
            case CROSS:
                if (isWin)
                    gameColor = GameColor.EMPTY;
                else if ( isGameAssumption && isAssumption )
                    gameColor = GameColor.ASSUMPTION_CROSS;
                else
                    gameColor = GameColor.CROSS;
                break;
        }
        notyfy();
        changeColor();
    }

    public GameColor getGameColor() {
        return gameColor;
    }


    private void changeColor() {
        // TODO Recupere la couleur dans le css
        switch (gameColor) {
            case WIN -> line.setStroke(Color.WHITE);
            case EMPTY -> line.setStroke( Color.GRAY);
            case LINE -> line.setStroke(Color.valueOf("66F4FF"));
            case CROSS -> line.setStroke(Color.valueOf("E35151"));
            case ASSUMPTION_LINE -> line.setStroke(Color.valueOf("41B641"));
            case ASSUMPTION_CROSS -> line.setStroke(Color.valueOf("E39351"));
        }
        switch (gameColor) {
            case WIN,LINE , ASSUMPTION_LINE -> toFront();
            case EMPTY, CROSS, ASSUMPTION_CROSS -> toBack();
        }
        switch (gameColor){
            case WIN, EMPTY, LINE , CROSS -> setDashedLine(false);
            case ASSUMPTION_LINE, ASSUMPTION_CROSS -> setDashedLine(true);
        }
    }

    public void setDashedLine(boolean isDashed) {
        if (isDashed) {
            double lineLength = Math.sqrt(Math.pow(line.getEndX() - line.getStartX(), 2) + Math.pow(line.getEndY() - line.getStartY(), 2));
            double sectionLength = (0.7 * lineLength) / 5;
            double spaceLength = (0.3 * lineLength) / 6;

        } else {
            line.getStrokeDashArray().clear();
        }
    }

    private  void notyfy() {
        for (ActionListener actionListener : subscribeur)
            actionListener.actionPerformed(new ActionEvent(this, 0, "UPDATE"));
    }
    public void subscribe(ActionListener circleDraw) {
        subscribeur.add(circleDraw);
    }
}
