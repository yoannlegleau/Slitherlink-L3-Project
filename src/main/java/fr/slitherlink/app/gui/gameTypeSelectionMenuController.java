package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.app.fx_controlleur.LevelPlaySceen;
import fr.slitherlink.game.Game;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class gameTypeSelectionMenuController {

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
        System.out.println("On appuye sur le bouton aventure");
        Slitherlink.getMainInstance().setActive(Slitherlink.SELECT_LEVEL);
    }

    public void onRushPlayButtonClick(){
        System.out.println("On appuye sur le bouton rush");
    }
}
