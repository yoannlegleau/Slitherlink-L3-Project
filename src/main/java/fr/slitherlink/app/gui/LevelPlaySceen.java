package fr.slitherlink.app.gui;

import fr.slitherlink.app.gui.component.PuzllGridGroup;
import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.action.actions.HelpAction;
import fr.slitherlink.save.technic.ListTechnic;
import fr.slitherlink.save.technic.Technic;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    public Pane helpPanel;

    Game game;

    PuzllGridGroup puzlGridGroup;

    @FXML
    public BorderPane root;

    @FXML
    public Pane gamePane;

    public static int seconds = 0;
    public static Boolean boolHandle=false;
    @FXML
    public Label timeLabel=new Label("00:00:00");
    @FXML
    private Button pauseButton;
    public static Timeline timeline=null;
    public static LevelPlaySceen lpc=null;

    @FXML
    public void initialize() {
        //TODO trouver un moyen de le recuperer la taille de gamePane

        lpc=this;

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

    public void setGame(Game game) {
        this.game = game;
        game.subscribe(this);
        int pxSize = 500;
        puzlGridGroup = new PuzllGridGroup(game, pxSize);
        gamePane.getChildren().clear();
        gamePane.getChildren().add(puzlGridGroup);
        game.redoAllAction();
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

        //TODO afficher l'aide
//        titleHelpText.setText(technic.getTitle());
//        descriptionHelpText.setText(technic.getDesc());
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
            case NEW_HELP -> updateHelpDisplay((HelpAction) e.getSource());
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

    public static void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seconds++;
                updateTimer();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void stopTimer(){
        timeline.stop();
    }

    private static void updateTimer() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        StringProperty str=new SimpleStringProperty();
        str.setValue(time);
        lpc.timeLabel.textProperty().bind(str);
    }

    public void pauseAction(ActionEvent event) {
        if(boolHandle){
            startTimer();
            boolHandle=false;
        }else{
            stopTimer();
            boolHandle=true;
        }
    }

}
