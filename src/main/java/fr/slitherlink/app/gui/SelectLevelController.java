package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.app.fx_controlleur.LevelPlaySceen;
import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.save.adventureMode.AdventureModeRessourceManageur;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
import fr.slitherlink.save.puzzle.PuzzleResourceManageur;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class SelectLevelController implements ActionListener {

    @FXML
    public FlowPane buttonLevelPanel;
    
    @FXML
    public void initialize(){
        List<Integer> listID = PuzzleResourceManageur.getLevelIdList();
        for(Integer id : listID)
            buttonLevelPanel.getChildren().add(createButton(id));
    }
    public Button createButton(int id) {

        Button button = new Button(id+"");
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setGraphicTextGap(0.0);
        button.setMaxHeight(100.0);
        button.setMaxWidth(100.0);
        button.setMinHeight(100.0);
        button.setMinWidth(100.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(100.0);
        button.setPrefWidth(100.0);
        button.setScaleX(1.1);
        button.setStyle("-fx-translate-y: -50;");
        button.getStyleClass().add("SelectButton");
        button.getStylesheets().add("@selectLevel.css");
        button.setTextAlignment(TextAlignment.CENTER);
        button.setCursor(Cursor.HAND);
        button.setFont(new Font(30.0));

        if (AdventureModeRessourceManageur.getInstence().isLevelFinished(id))
            //TODO: 12/04/2023 afficher comme fini
            button.setDisable(true);
        else
            // TODO: 12/04/2023 afficher comme a faire
            button.getStyleClass().add("SelectButtonUnfinished");

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Game game = new Game(new GameSaveResourceManageur(id));
            game.subscribe(this);
            Slitherlink.getMainInstance().setActive(Slitherlink.LEVEL_PLAY_SRCEEN);
            ((LevelPlaySceen)Slitherlink.getMainInstance().getController(Slitherlink.LEVEL_PLAY_SRCEEN)).setGame(game);
        });
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GameActionTypes.WIN.name()))
            AdventureModeRessourceManageur.getInstence().addLevel(((Game)e.getSource()).getPuzzleId());
    }
}
