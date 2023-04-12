package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.save.feePlay.FreePlayRessourceManager;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
import fr.slitherlink.save.puzzle.Difficulty;
import fr.slitherlink.save.puzzle.PuzzleResourceManageur;
import fr.slitherlink.save.puzzle.PuzzleSave;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LE GLEAU Yoann
 * @version 1, 08/04/2023
 */
public class FreePlayController implements ActionListener {

    // Composent graphique
    public Button newGameButton;
    public Button resumeButton;
    public MenuButton sizeMenuButon;
    public RadioButton easyButton;
    public RadioButton mediumButton;
    public RadioButton hardButton;
    public ToggleButton finishedFilterToggle;
    public Text easyText;
    public Text mediumText;
    public Text hardText;
    public HBox HelpHbox;

    private final FreePlayRessourceManager config;

    public FreePlayController() {
        config = FreePlayRessourceManager.getInstance();
    }

    @FXML
    public void initialize(){
        // TODO: 10/04/2023 Implementer les parametre des aides 
        HelpHbox.setVisible(false);
        
        updateFormOption();
        sizeMenuButon.setText(config.getSize() + "x" + config.getSize());

        finishedFilterToggle.setSelected(config.getFinishedFilter());
        finishedFilterToggle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setFinishedFilter(finishedFilterToggle.isSelected()));

        setDificutlty(config.getDifficulty());
        easyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setDificutlty(Difficulty.EASY));
        mediumButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setDificutlty(Difficulty.MEDIUM));
        hardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setDificutlty(Difficulty.HARD));

        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> newGame());

        if (config.loadSave() != null)
            resumeButton.setDisable(false);
        else
            resumeButton.setDisable(true);
        resumeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> resumeGame());
    }

    private void newGame(){
        int levelId = PuzzleResourceManageur.getLevelList().stream()
                .filter(puzzleSave -> puzzleSave.getSize() == config.getSize())
                .filter(puzzleSave -> puzzleSave.getDifficulty() == config.getDifficulty())
                .filter(puzzleSave -> !config.isFinished(puzzleSave.getId()))
                .mapToInt(PuzzleSave::getId)
                .findFirst().orElse(-1);
        if (levelId == -1)
            return;

        config.createSave(levelId);
        resumeGame();

    }

    public void resumeGame(){
        Game game = new Game(config);
        game.subscribe(this);
        Slitherlink.getMainInstance().setActive(Slitherlink.LEVEL_PLAY_SRCEEN);
        ((LevelPlaySceen)Slitherlink.getMainInstance().getController(Slitherlink.LEVEL_PLAY_SRCEEN)).setGame(game);
    }

    private void setSize(int size) {
        config.setSize(size);
        sizeMenuButon.setText(size + "x" + size);
        updateFormOption();
    }

    private void setDificutlty(Difficulty difficulty){
        config.setDifficulty(difficulty);
        easyButton.setSelected(false);
        mediumButton.setSelected(false);
        hardButton.setSelected(false);
        switch (difficulty){
            case EASY:
                easyButton.setSelected(true);
                break;
            case MEDIUM:
                mediumButton.setSelected(true);
                break;
            case HARD:
                hardButton.setSelected(true);
                break;
        }
    }

    private void setFinishedFilter(boolean finishedFilter){
        config.setFinishedFilter(finishedFilter);
        updateFormOption();
    }

    private void updateFormOption(){
        Set<Integer> sizeList = new HashSet<>();
        Set<Difficulty> difficultyList = new HashSet<>();

        PuzzleResourceManageur.getLevelList().forEach(puzzleSave -> {
            if (!config.getFinishedFilter() || !config.isFinished(puzzleSave.getId())) {
                sizeList.add(puzzleSave.getSize());
                if (puzzleSave.getSize() == config.getSize())
                    difficultyList.add(puzzleSave.getDifficulty());
            }
        });

        if ( !sizeList.isEmpty() && !sizeList.contains(config.getSize()))
            if (sizeList.iterator().hasNext()) {
                setSize( sizeList.iterator().next());
                return;
            }

        if (!sizeList.isEmpty() && !difficultyList.contains(config.getDifficulty()))
            if (sizeList.iterator().hasNext())
                setDificutlty(difficultyList.iterator().next());

        sizeMenuButon.getItems().clear();
        for (int size : sizeList) {
            MenuItem mi = new MenuItem(size + "x" + size);
            mi.setOnAction(event -> {
                setSize(size);
            });
            sizeMenuButon.getItems().add(mi);
        }

        hardText.setVisible(difficultyList.contains(Difficulty.HARD));
        hardButton.setVisible(difficultyList.contains(Difficulty.HARD));
        mediumText.setVisible(difficultyList.contains(Difficulty.MEDIUM));
        mediumButton.setVisible(difficultyList.contains(Difficulty.MEDIUM));
        easyText.setVisible(difficultyList.contains(Difficulty.EASY));
        easyButton.setVisible(difficultyList.contains(Difficulty.EASY));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GameActionTypes.WIN.name())) {
            config.addFinishedLevelId(config.loadPuzzle().getId());
            config.save(null);
        }
    }
}
