package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.app.fx_controlleur.LevelPlaySceen;
import fr.slitherlink.game.Game;
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

import java.util.List;

public class SelectLevelController {

    @FXML
    public FlowPane buttonLevelPanel;

    @FXML
    public void initialize() {
        List<Integer> listID = PuzzleResourceManageur.getLevelIdList();
        int i = 1;
        for (Integer id : listID) {
            Button button = createButton(id);
            if (i <= 9) {
                buttonLevelPanel.getChildren().add(button);
            } else {
                FlowPane newFlowPane = new FlowPane();
                newFlowPane.getChildren().add(button);
                buttonLevelPanel.setMaxWidth(600);
                newFlowPane.setMaxWidth(400); // largeur maximale du FlowPane
                newFlowPane.setMaxHeight(400); // largeur maximale du FlowPane

                buttonLevelPanel.getChildren().add(newFlowPane);
            }
            i++;
        }
    }

    public Button createButton(int id) { // TODO: 11/04/2023 modif interface pour que ce soit clean + sauvegarder partie en cours avec id

        Button button = new Button(id + "");
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

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Slitherlink.getMainInstance().setActive(Slitherlink.LEVEL_PLAY_SRCEEN);
            ((LevelPlaySceen) Slitherlink.getMainInstance().getController(Slitherlink.LEVEL_PLAY_SRCEEN)).setGame(new Game(new GameSaveResourceManageur(id)));
        });
        return button;
    }
}
