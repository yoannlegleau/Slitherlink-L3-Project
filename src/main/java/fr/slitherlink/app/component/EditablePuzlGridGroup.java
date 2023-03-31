package fr.slitherlink.app.component;

import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.grid.EdgeType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 * @pakage fr.slitherlink.app
 */
public class EditablePuzlGridGroup extends PuzlGridGroup {

    public EditablePuzlGridGroup(Game game, int pxSize) {
        super(game, pxSize);
    }

    @Override
    protected Node createText(double marging, double length, double fontSize, double sizeText, int x, int y) {
        class EditableNumber extends Label {

            int x;
            int y;

            public EditableNumber(double marging, double length, double fontSize, double sizeText, int x, int y) {
                this.x = x;
                this.y = y;
                setPrefSize(length-marging, length-marging);
                setLayoutX(marging*2 + x * length);
                setLayoutY(marging*2 + y * length);


                if (game.getNumbers()[x][y] == null)
                    setText("");
                else
                    setText(game.getNumbers()[x][y].toString());
                setTextFill(Color.WHITE);
                setStyle("-fx-font-size: "+ sizeText +"px;");
                setTextAlignment(TextAlignment.CENTER);
                setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        nextNumber();
                    }
                    if (event.getButton() == MouseButton.SECONDARY) {
                        previousNumber();
                    }
                });
            }

            private void nextNumber(){
                if (game.getNumbers()[x][y] == null)
                    setNumber(0);
                else if (game.getNumbers()[x][y] == 3)
                    setNumber(null);
                else
                    setNumber(game.getNumbers()[x][y] + 1);
            }

            private void previousNumber(){
                if (game.getNumbers()[x][y] == null)
                    setNumber(3);
                else if (game.getNumbers()[x][y] == 0)
                    setNumber(null);
                else
                    setNumber(game.getNumbers()[x][y] - 1);
            }

            private void setNumber(Integer number){
                game.getNumbers()[x][y] = number;
                if (number == null)
                    setText("");
                else
                    setText(String.valueOf(number));
                update();
            }

        }
        return new EditableNumber(marging, length, fontSize, sizeText, x, y);
    }
}
