package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.app.gui.component.PuzllGridGroup;
import fr.slitherlink.game.Game;
import fr.slitherlink.game.action.ActionFactory;
import fr.slitherlink.game.action.GameActionTypes;
import fr.slitherlink.game.action.actions.HelpAction;
import fr.slitherlink.game.grid.GridCell;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.io.IOException;

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
    public HBox toolHBox;
    public VBox centerVbox;
    public HBox timeVbox;
    public BorderPane root;
    public Pane gamePane;
    public Label timeLabel=new Label("00:00:00");
    public HBox retractablePanel;
    public Region helpIcon;

    Game game;

    PuzllGridGroup puzlGridGroup;

    public static int seconds = 0;
    public static Boolean boolHandle=false;
    public static Timeline timeline=null;
    public static LevelPlaySceen lpc=null;
    private TranslateTransition transition;
    private TranslateTransition transition2;

    private RotateTransition rotateTransition;

    @FXML
    public void initialize() {
        //TODO trouver un moyen de le recuperer la taille de gamePane

        lpc=this;
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.Z && event.isControlDown()) {
                undo();
            }

            if (event.getCode() == KeyCode.Y && event.isControlDown()) {
                redo();
            }
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (puzlGridGroup != null) {
                puzlGridGroup.setPxSize(getPuzzleSize());
            }
            gamePane.setPrefWidth(gamePane.getHeight());
            gamePane.setMaxWidth(gamePane.getHeight());
        });


        transition = new TranslateTransition(Duration.millis(250), retractablePanel);
        transition2 = new TranslateTransition(Duration.millis(250), gamePane);
        rotateTransition = new RotateTransition(Duration.millis(250), helpIcon);
        helpIcon.setRotate(-90);
    }

    private int getPuzzleSize() {
        return (int) (root.getScene().getWindow().getHeight() * 0.7 )  - 100 ;
    }

    public void setGame(Game game) {
        this.game = game;
        togglePanel();
        game.subscribe(this);
        retractablePanel.setVisible(false);
        puzlGridGroup = new PuzllGridGroup(game, getPuzzleSize());
        gamePane.getChildren().clear();
        gamePane.getChildren().add(puzlGridGroup);
        helpPanel.getChildren().clear();
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
        helpPanel.getChildren().clear();
        helpPanel.getChildren().add(getHelpPane(helpAction.getTechnicId()));
    }

    private Pane getHelpPane(int id) {
        FXMLLoader loader = new FXMLLoader(Slitherlink.class.getResource("gui/help.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HelpController controller = loader.getController();
        controller.setTechnicId(id);
        Pane pane = loader.getRoot();
        pane.setPrefWidth(gamePane.getWidth()/2);
        pane.setMaxWidth(gamePane.getWidth()/2);

        return pane;
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
            case NEW_HELP -> {
                updateHelpDisplay((HelpAction) e.getSource());
                retractablePanel.setVisible(true);
            }
        }
    }

    private void updateAssumptionButton(boolean assumption) {
        assumptionButton.setVisible(!assumption);
        assumptionCancelButton.setVisible(assumption);
        assumptionValidButton.setVisible(assumption);
    }

    private void updateWin(){
        if(game.isWin()){
            timeline.stop();
            root.setDisable(true);
        }
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
        if(boolHandle && !winLabel.isVisible()){
            startTimer();
            boolHandle=false;
            puzlGridGroup.setDisable(false);
        }else{
            stopTimer();
            boolHandle=true;
            puzlGridGroup.setDisable(true);
        }
    }

    @FXML
    public void togglePanel() {
        rotateTransition.setByAngle(180); // rotation de 180 degrés
        if (retractablePanel.getTranslateX() == 0) {
            rotateTransition.setByAngle(180);
            transition.setToX(helpPanel.getWidth());
            transition2.setToX(helpPanel.getWidth()/2);
        } else {
            // Si le panneau est rétracté, l'étendre
            rotateTransition.setByAngle(-180);
            transition.setToX(0);
            transition2.setToX(0);
        }
        rotateTransition.play();
        transition2.play();
        transition.play();
    }

}
