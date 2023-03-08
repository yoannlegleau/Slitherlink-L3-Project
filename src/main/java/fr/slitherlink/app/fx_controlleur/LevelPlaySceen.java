package fr.slitherlink.app.fx_controlleur;

import fr.slitherlink.app.component.PuzlGridGroup;
import fr.slitherlink.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * @author LE GLEAU Yoann
 * @version 1, 08/03/2023
 */
public class LevelPlaySceen {

    private static final int levelid = 1;

    @FXML
    public Pane gamePane;

    @FXML
    public void initialize() {
        int pxSize = 500; //TODO trouver un moyen de le recuperer la taille de gamePane
        gamePane.getChildren().add(new PuzlGridGroup(new Game(levelid), pxSize));
    }

}
