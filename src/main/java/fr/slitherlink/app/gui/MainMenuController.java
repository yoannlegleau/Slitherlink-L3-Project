package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.game.Game;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
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
        Slitherlink.getMainInstance().setActive(Slitherlink.GAME_SELECTION_MENU);
    }

    public void onRulesButtonClick(){
        Slitherlink.getMainInstance().setActive(Slitherlink.RULE_SCREEN);
    }

    public void onTutorialButtonClick(){
        System.out.println("On a appuyé sur le bouton du tutoriel");
    }

    public void onQuitButtonClick(){
        System.out.println("On quitte le jeu");
        System.exit(0);
    }
}
