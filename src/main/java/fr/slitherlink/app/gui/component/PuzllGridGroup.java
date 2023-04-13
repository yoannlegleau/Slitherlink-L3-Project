package fr.slitherlink.app.gui.component;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.grid.EdgeType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 * @pakage fr.slitherlink.app
 */
public class PuzllGridGroup extends Group implements ActionListener {

    private static final double LINE_THICKNESS_FACTOR = 0.03;
    private static final double FONT_SIZE_FACTOR = 0.7;

    private Game game;

    private int pxSize;

    private List<DrawingEdge> drawingEdges;


    public PuzllGridGroup(Game game , int pxSize){
        this.game = game;
        this.pxSize = pxSize;
        drawingEdges = new ArrayList<>();
        createGrid();
    }

    public void setPxSize(int pxSize) {
        this.pxSize = pxSize;
        createGrid();
    }

    public int getPxSize() {
        return pxSize;
    }

    private void createGrid() {

        getChildren().clear();
        double thickness = (int) (pxSize * LINE_THICKNESS_FACTOR);
        double marging = thickness / 2;
        double length = (pxSize - thickness) / game.getSolution().getSize() ;
        double fontSize = length * FONT_SIZE_FACTOR;
        double sizeText = fontSize * 0.7;

        /*for (int x = 0; x <= game.getSolution().getSize(); x++) {
            for (int y = 0; y <= game.getSolution().getSize(); y++) {
                getChildren().add(new CircleDraw(x, y, thickness));
            }
        }*/


        for (int x = 0; x < game.getSolution().getSize(); x++) {
            for (int y = 0; y < game.getSolution().getSize(); y++) {
                if (game.getNumbers()[x][y] != null){
                    Node text = createText(marging, length, fontSize, sizeText, x, y, game);
                    text.setStyle("-fx-fill: white; -fx-font-size: "+sizeText+"px;");


                    getChildren().add(text);
                }


                DrawingEdge drawingEdgeT = null;
                DrawingEdge drawingEdgeL = null;
                DrawingEdge drawingEdgeB = null;
                DrawingEdge drawingEdgeR = null;

                if (y == 0) {
                    drawingEdgeT = new DrawingEdge(
                            marging + x * length,
                            y * length,
                            length,
                            thickness,
                            x,y,"T",
                            game);
                    getChildren().add(drawingEdgeT);
                    drawingEdges.add(drawingEdgeT);
                }
                if (x == 0) {
                    drawingEdgeL = new DrawingEdge(
                            x * length,
                            marging + y * length,
                            thickness,
                            length,
                            x,y,"L",
                            game);
                    getChildren().add(drawingEdgeL);
                    drawingEdges.add(drawingEdgeL);
                }
                drawingEdgeB = new DrawingEdge(
                        marging + x * length,
                        y * length + length,
                        length,
                        thickness,
                        x,y,"B",
                        game);
                getChildren().add(drawingEdgeB);
                drawingEdges.add(drawingEdgeB);

                drawingEdgeR = new DrawingEdge(
                        x * length + length,
                        marging + y * length,
                        thickness,
                        length,
                        x,y,"R",
                        game);
                getChildren().add(drawingEdgeR);
                drawingEdges.add(drawingEdgeR);

                //TODO luca

            }
        }
    }

    protected Node createText(double marging, double length, double fontSize, double sizeText, int x, int y, Game game) {
        Text text = new Text(String.valueOf(game.getNumbers()[x][y]));
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setX( 3 * marging + x * length + length /2 - fontSize /2);
        text.setY( -1 * marging + y * length + length /2 + fontSize /2);
        text.setStyle("-fx-fill: white; -fx-font-size: "+ sizeText +"px;");
        return text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
