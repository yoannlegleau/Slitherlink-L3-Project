package fr.slitherlink.app;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.grid.EdgeType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 * @pakage fr.slitherlink.app
 */
public class PuzlGridGroup extends Group {

    private static final double LINE_THICKNESS_FACTOR = 0.03;
    private static final double FONT_SIZE_FACTOR = 0.7;

    Game game;

    int pxSize;


    public PuzlGridGroup(Game game , int pxSize){
        this.game = game;
        this.pxSize = pxSize;
        createGrid();
    }

    public void update(){
        //TODO faire une methode mois brutale
        getChildren().clear();
        createGrid();
    }

    private void createGrid() {

        double thickness = (int) (pxSize * LINE_THICKNESS_FACTOR);
        double marging = thickness / 2;
        double length = (pxSize - thickness) / game.getSolution().getSize() ;
        double fontSize = length * FONT_SIZE_FACTOR;


        for (int x = 0; x < game.getSolution().getSize(); x++) {
            for (int y = 0; y < game.getSolution().getSize(); y++) {
                if (game.getNumbers()[x][y] != null){
                    Text text = new Text(String.valueOf(game.getNumbers()[x][y]));
                    text.setX( marging + x * length + length/2 - fontSize/2);
                    text.setY( marging + y * length + length/2 + fontSize/2);
                    text.setFont(new javafx.scene.text.Font(fontSize));
                    getChildren().add(text);
                }



                if (y == 0) {
                    getChildren().add(new DrawingEdge(
                            marging + x * length,
                            y * length,
                            length,
                            thickness,
                            x,y,"T",
                            game
                    ));
                }
                if (x == 0) {
                    getChildren().add(new DrawingEdge(
                            x * length,
                            marging + y * length,
                            thickness,
                            length,
                            x,y,"L",
                            game
                    ));
                }

                getChildren().add(new DrawingEdge(
                        marging + x * length,
                        y * length + length,
                        length,
                        thickness,
                        x,y,"B",
                        game
                ));
                getChildren().add(new DrawingEdge(
                        x * length + length,
                        marging + y * length,
                        thickness,
                        length,
                        x,y,"R"
                        ,game
                ));
            }
        }
    }

    class DrawingEdge extends Rectangle{

        private int coodonatX;
        private int coodonatY;
        private String direction;

        private Game game;

        public DrawingEdge(double x, double y, double width, double height, int coodonatX, int coodonatY, String direction, Game game) {
            super(x, y, width, height);
            this.coodonatX = coodonatX;
            this.coodonatY = coodonatY;
            this.direction = direction;
            this.game = game;
            setFill(Color.GRAY);
            setStroke(Color.BLACK);
            setOnMouseClicked(event -> {
                EdgeType newTipe = EdgeType.EMPTY;
                if (getEdgeType().equals(EdgeType.EMPTY)) newTipe = EdgeType.LINE;
                if (getEdgeType().equals(EdgeType.LINE)) newTipe = EdgeType.EMPTY;
                switch (direction){
                    case "T":
                        game.getCurrentGrid().getCell(coodonatX, coodonatY).getTop().setType(newTipe);
                        break;
                    case "B":
                        game.getCurrentGrid().getCell(coodonatX, coodonatY).getBottom().setType(newTipe);
                        break;
                    case "L":
                        game.getCurrentGrid().getCell(coodonatX, coodonatY).getLeft().setType(newTipe);
                        break;
                    case "R":
                        game.getCurrentGrid().getCell(coodonatX, coodonatY).getRight().setType(newTipe);
                        break;
                }
                updateColor();
            });
            updateColor();
        }

        private void updateColor() {
            switch (getEdgeType()){
                case EMPTY:
                    setFill(Color.GRAY);
                    break;
                case LINE:
                    setFill(Color.RED);
                    break;
                case CROSS:
                    setFill(Color.RED);
                    break;
            }
        }

        private EdgeType getEdgeType(){
            switch (direction){
                case "T":
                    return game.getCurrentGrid().getCell(coodonatX, coodonatY).getTop().getType();
                case "B":
                    return game.getCurrentGrid().getCell(coodonatX, coodonatY).getBottom().getType();
                case "L":
                    return game.getCurrentGrid().getCell(coodonatX, coodonatY).getLeft().getType();
                case "R":
                    return game.getCurrentGrid().getCell(coodonatX, coodonatY).getRight().getType();
                    default:
                        return null;
            }
        }


    }
}
