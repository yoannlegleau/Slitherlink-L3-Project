package fr.slitherlink.app.component;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.grid.EdgeType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
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

    public void setPxSize(int pxSize) {
        this.pxSize = pxSize;
        update();
    }

    public int getPxSize() {
        return pxSize;
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
        double sizeText = fontSize * 0.7;


        for (int x = 0; x < game.getSolution().getSize(); x++) {
            for (int y = 0; y < game.getSolution().getSize(); y++) {
                if (game.getNumbers()[x][y] != null){
                    Node text = createText(marging, length, fontSize, sizeText, x, y);
                    text.setStyle("-fx-fill: white; -fx-font-size: "+sizeText+"px;");


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
                //TODO luca
//                getChildren().add(new DrawingCircle(
//                        marging + x * length + length/2,
//                        marging + y * length + length/2,
//                        length/2,
//                        game,
//                        x,y));
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

    protected Node createText(double marging, double length, double fontSize, double sizeText, int x, int y) {
        Text text = new Text(String.valueOf(game.getNumbers()[x][y]));
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setX( marging + x * length + length /2 - fontSize /2);
        text.setY( marging + y * length + length /2 + fontSize /2);
        text.setStyle("-fx-fill: white; -fx-font-size: "+ sizeText +"px;");
        return text;
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
            //setStroke(Color.BLACK);
            setOnMouseClicked(event -> {
                EdgeType newTipe = EdgeType.EMPTY;

                if (event.getButton() == MouseButton.PRIMARY)
                    if (getEdgeType().equals(EdgeType.LINE))
                        game.action(ActionFactory.setEdgeEmpty(coodonatX, coodonatY, direction));
                    else
                        game.action(ActionFactory.setEdgeLine(coodonatX, coodonatY, direction));
                else if (event.getButton() == MouseButton.SECONDARY)
                    if (getEdgeType().equals(EdgeType.CROSS))
                        game.action(ActionFactory.setEdgeEmpty(coodonatX, coodonatY, direction));
                    else
                        game.action(ActionFactory.setEdgeCross(coodonatX, coodonatY, direction));
                updateColor();
            });
            updateColor();
        }

        private void updateColor() {
            //TODO recupere la couleur dans le css
            switch (getEdgeType()){
                case EMPTY:
                    setFill(Color.GRAY);
                    break;
                case LINE:
                    setFill(Color.valueOf("66F4FF"));
                    break;
                case CROSS:
                    setFill(Color.valueOf("E35151"));
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
    //TODO luca
//    public DrawingCirlce ( double centerX, double centerY, double radius, Game game, int coodonatX, int coodonatY) {
//        super(centerX, centerY, radius);
//        this.coodonatX = coodonatX;
//        this.coodonatY = coodonatY;
//        this.game = game;
//        setFill(Color.GRAY);
//        //setStroke(Color.BLACK);
//        }
//    }
}
