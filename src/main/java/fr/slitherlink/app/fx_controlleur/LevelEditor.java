package fr.slitherlink.app.fx_controlleur;

import fr.slitherlink.app.component.EditablePuzllGridGroup;
import fr.slitherlink.game.Game;
import fr.slitherlink.game.grid.Grid;
import fr.slitherlink.save.puzzle.PuzzleResourceManageur;
import fr.slitherlink.save.puzzle.PuzzleSave;
import fr.slitherlink.save.XmlResourcesManageur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 */
public class LevelEditor {

    // FXML elements
    public FlowPane gamePane;
    public BorderPane root;
    public TextField fileField;
    public Button saveButton;
    public TextField idField;
    public TextField sizeField;

    // custom FXML elements
    private EditablePuzllGridGroup puzlGridGroup;

    // Game elements
    private Game game;


    @FXML
    public void initialize() {

        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("Touche Echap appuyée !");
            }

            if (event.getCode() == KeyCode.S && event.isControlDown()) {
                saveLevel();
            }
        });

        fileField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loadLevel();
            }
        });

        sizeField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setsize();
            }
        });

    }

    private void createGame(int levelid) {
        int pxSize = 500; //TODO trouver un moyen de le recuperer la taille de gamePane
        game = new Game(levelid);
        game.setDoSave(false);
        game.setCurrentGrid(game.getSolution());
        if (puzlGridGroup != null)
            gamePane.getChildren().remove(puzlGridGroup);
        puzlGridGroup = new EditablePuzllGridGroup(game, pxSize);
        gamePane.getChildren().add(puzlGridGroup);

        idField.setText(String.valueOf(game.getPuzzleId()));
        sizeField.setText(String.valueOf(game.getCurrentGrid().getSize()));
    }

    public void resetAction() {
        if (game != null)
            game.reset();
        puzlGridGroup.update();
    }

    public void loadLevel() {
        createGame(Integer.parseInt(fileField.getText()));
    }

    public void saveLevel() {
        if (game == null)
            return;
        PuzzleSave curentLevel = new PuzzleSave(game.getPuzzleId(), game.getCurrentGrid(), game.getNumbers());
        PuzzleResourceManageur.saveLevel(curentLevel);
    }

    public void newLevel() {
        String dir = XmlResourcesManageur.RESOURCES_PATH + "puzzle";
        List<Integer> list = Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(file -> Integer.parseInt( file.getName().replace(".xml", "")))
                .collect(Collectors.toSet())
                .stream().sorted().toList();
        int id;
        if (list.isEmpty())
            id = 1;
        else
            id = list.get(list.size() - 1) + 1;


        PuzzleSave newLevel = new PuzzleSave(id, new Grid(6), new Integer[6][6]);

        PuzzleResourceManageur.saveLevel(newLevel);

        //newLevel.setSize(6);
        createGame(id);
    }

    public void setsize() {
        if (game == null)
            return;
        int size = Integer.parseInt(sizeField.getText());
        PuzzleSave newLevel = new PuzzleSave(game.getPuzzleId(),game.getCurrentGrid(), game.getNumbers());
        newLevel.setSize(size);
        PuzzleResourceManageur.saveLevel(newLevel);
        createGame(game.getPuzzleId());
    }
}
