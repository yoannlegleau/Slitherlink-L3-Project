package fr.slitherlink.app.component;

import fr.slitherlink.game.Game;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * @author LE GLEAU Yoann
 * @version 1, 07/03/2023
 * @pakage fr.slitherlink.app
 */
public class EditablePuzllGridGroup extends PuzllGridGroup {

    Game game;

    public EditablePuzllGridGroup(Game game, int pxSize) {
        super(game, pxSize);
    }

    @Override
    protected Node createText(double marging, double length, double fontSize, double sizeText, int x, int y , Game game) {
        class EditableNumber extends Label {


            int x;
            int y;

            public EditableNumber(double marging, double length, double fontSize, double sizeText, int x, int y, Game game) {
                this.x = x;
                this.y = y;
                setPrefSize(length-marging, length-marging);
                setLayoutX(marging*2 + x * length);
                setLayoutY(marging*2 + y * length);
                setText(game.getNumbers()[x][y] + "");
                setTextFill(Color.WHITE);
                setStyle("-fx-font-size: "+ sizeText +"px;");
                setTextAlignment(TextAlignment.CENTER);
                setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        setNumber(game.getNumbers()[x][y] + 1);
                    }
                    if (event.getButton() == MouseButton.SECONDARY) {
                        setNumber(game.getNumbers()[x][y] - 1);
                    }
                });
            }

            private void setNumber(int number){
                if (number == -1)
                    number = 3;
                number = number % 4;
                game.getNumbers()[x][y] = number;
                setText(String.valueOf(number));
            }

        }
        return new EditableNumber(marging, length, fontSize, sizeText, x, y ,game);
    }
}
