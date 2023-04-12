package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.game.Game;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class GameTypeSelectionMenuController {

    @FXML
    private VBox freePlayButton;

    @FXML
    private VBox adventurePlayButton;

    @FXML
    private VBox rushPlayButton;

    public void onFreePlayButtonClick(){
        Slitherlink.getMainInstance().setActive(Slitherlink.FREEPLAY_MENU);
    }

    public void onAdventurePlayButtonClick(){
        Slitherlink.getMainInstance().setActive(Slitherlink.SELECT_LEVEL);
    }

    public void onRushPlayButtonClick(){
    }
}
