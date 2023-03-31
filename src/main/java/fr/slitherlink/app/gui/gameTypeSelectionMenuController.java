package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    }

    public void onRushPlayButtonClick(){
        System.out.println("On appuye sur le bouton rush");
    }
}
