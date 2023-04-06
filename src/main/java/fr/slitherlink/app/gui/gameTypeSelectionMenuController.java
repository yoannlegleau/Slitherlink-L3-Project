package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
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
        System.out.println("On appuye sur le boutton freeplay");
        Slitherlink.getMainInstance().setActive(Slitherlink.FREEPLAY_MENU);
    }

    public void onAdventurePlayButtonClick(){
        System.out.println("On appuye sur le bouton aventure");
        Slitherlink.getMainInstance().setActive(Slitherlink.LEVEL_PLAY_SRCEEN);
    }

    public void onRushPlayButtonClick(){
        System.out.println("On appuye sur le bouton rush");
    }
}
