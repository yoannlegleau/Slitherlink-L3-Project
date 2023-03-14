package fr.slitherlink.app.fx_controlleur;

import fr.slitherlink.app.component.PuzlGridGroup;
import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.action.GameActionTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionListener;

/**
 * @author LE GLEAU Yoann
 * @version 1, 08/03/2023
 */
public class LevelPlaySceen implements ActionListener {

    private static final int levelid = 1;
    public Label winLabel;

    Game game;

    PuzlGridGroup puzlGridGroup;

    @FXML
    public BorderPane root;

    @FXML
    public Pane gamePane;

    @FXML
    public void initialize() {
        int pxSize = 500; //TODO trouver un moyen de le recuperer la taille de gamePane
        game = new Game(levelid);
        game.subscribe(this);
        puzlGridGroup = new PuzlGridGroup(game, pxSize);
        gamePane.getChildren().add(puzlGridGroup);

        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("Touche Echap appuyée !");
            }

            if (event.getCode() == KeyCode.Z && event.isControlDown()) {
                undo();
            }

            if (event.getCode() == KeyCode.Y && event.isControlDown()) {
                redo();
            }

        });

    }

    public void resetAction(ActionEvent actionEvent) {
        game.reset();
    }

    public void undo() {
        game.action(ActionFactory.undo());
    }

    public void redo() {
        game.action(ActionFactory.redo());
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        switch (GameActionTypes.fromValue(e.getActionCommand())) {
            case WIN:
                update();
                break;
            case RESET:
                update();
                break;
        }
    }

    private void update(){
        winLabel.setVisible(game.isWin());
    }

}
