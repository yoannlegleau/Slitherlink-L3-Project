package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private Button playButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button quitButton;

    public void onPlayButtonClick(){
        System.out.println("On a appuyé sur le bouton jouer");
        Slitherlink.getMainInstance().setActive(Slitherlink.GAME_SELECTION_MENU);
    }

    public void onRulesButtonClick(){ System.out.println("On a appuyé sur le bouton des règles"); }

    public void onTutorialButtonClick(){
        System.out.println("On a appuyé sur le bouton du tutoriel");
    }

    public void onQuitButtonClick(){
        System.out.println("On quitte le jeu");
        System.exit(0);
    }
}
