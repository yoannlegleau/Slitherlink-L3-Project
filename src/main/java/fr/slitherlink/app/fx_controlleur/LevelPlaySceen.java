package fr.slitherlink.app.fx_controlleur;

import fr.slitherlink.app.fx_controlleur.component.PuzllGridGroup;
import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.action.actions.HelpAction;
import fr.slitherlink.save.technic.ListTechnic;
import fr.slitherlink.save.technic.Technic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.awt.event.ActionListener;

/**
 * @author LE GLEAU Yoann
 * @version 1, 08/03/2023
 */
public class LevelPlaySceen implements ActionListener {

    private static final int levelid = 1;
    public Label winLabel;
    public Button assumptionButton;
    public Button assumptionCancelButton;
    public Button assumptionValidButton;

    Game game;

    PuzllGridGroup puzlGridGroup;

    @FXML
    public BorderPane root;

    @FXML
    public Pane gamePane;

    @FXML
    public Text titleHelpText;

    @FXML
    public Text descriptionHelpText;

    @FXML
    public void initialize() {
        int pxSize = 500; //TODO trouver un moyen de le recuperer la taille de gamePane
        game = new Game(levelid);
        game.subscribe(this);
        puzlGridGroup = new PuzllGridGroup(game, pxSize);
        gamePane.getChildren().add(puzlGridGroup);

        game.redoAllAction();

        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("Touche Echap appuyée !");
            }

            if (event.getCode() == KeyCode.Z && event.isControlDown()) {
                undo();
            }

            if (event.getCode() == KeyCode.Y && event.isControlDown()) {
                redo();
            }
        });
    }

    public void resetAction(ActionEvent actionEvent) {
        game.reset();
    }

    public void undo() {
        game.action(ActionFactory.undo());
    }

    public void redo() {
        game.action(ActionFactory.redo());
    }

    public void updateHelpDisplay(HelpAction helpAction) {
        ListTechnic listTechnic = ListTechnic.getInstance();
        Technic technic = listTechnic.getTechnic(helpAction.getTechnicId());
        titleHelpText.setText(technic.getTitle());
        descriptionHelpText.setText(technic.getDesc());
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        switch (GameActionTypes.valueOf(e.getActionCommand())) {
            case WIN -> updateWin();
            case RESET -> {
                updateWin();
                updateAssumptionButton(false);
            }
            case ASSUMPTION_START -> updateAssumptionButton(true);
            case ASSUMPTION_VALID,ASSUMPTION_CANCEL -> updateAssumptionButton(false);
            case NEW_HELP, HIGHLIGTH_HELP -> updateHelpDisplay((HelpAction) e.getSource());
        }
    }

    private void updateAssumptionButton(boolean assumption) {
        assumptionButton.setVisible(!assumption);
        assumptionCancelButton.setVisible(assumption);
        assumptionValidButton.setVisible(assumption);
    }

    private void updateWin(){
        winLabel.setVisible(game.isWin());
    }

    public void assumptionStart(ActionEvent actionEvent) {
        game.action(ActionFactory.assumptionStart());
    }

    public void assumptionCancel(ActionEvent actionEvent) {
        game.action(ActionFactory.assumptionCancel());
    }

    public void assumptionValid(ActionEvent actionEvent) {
        game.action(ActionFactory.assumptionValid());
    }

    public void helpAction(ActionEvent actionEvent) {
        game.action(ActionFactory.help());
    }
}
