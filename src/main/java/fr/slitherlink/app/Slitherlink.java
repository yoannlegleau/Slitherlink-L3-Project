package fr.slitherlink.app;

import fr.slitherlink.app.gui.LevelPlaySceen;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Slitherlink extends Application {

    /* Constantes de sélection de menu */
    public static final String MAIN_MENU = "mainMenu";

    public static final String GAME_SELECTION_MENU = "levelSelectionMenu";
    public static final String LEVEL_PLAY_SRCEEN = "levelPlayScreen";
    public static final String RULE_SCREEN = "ruleScreen";

    public static final String SELECT_LEVEL = "selectLevel";

    public static final String FREEPLAY_MENU = "freeplayMenu";

    private static Slitherlink instance = null;

    private AnchorPane mainPane;
    public AnchorPane getMainPane() {return mainPane;}
    private ButtonBar mainButtonBar;

    private Pane backButton;

    private Pane popupBackground;

    private AnchorPane blur = new AnchorPane();

    // Les différents écrans
    private Map<String, FXMLLoader> sceneLoaders;

    /* Pile qui contient les écrans affichées */
    private Stack<String> screenStack;

    public Pane active;

    @Override
    public void start(Stage stage) throws IOException {
        // Notre application est d'un AnchorPane qui contient
        // Le contenu principal
        // Le bouton retour
        // Et le barre de boutons

        sceneLoaders = new HashMap<>();
        screenStack = new Stack<>();

        mainPane = new AnchorPane();
        mainPane.setMinSize(960, 720);
        mainPane.getStylesheets().add(getClass().getResource("style/default-style.css").toExternalForm());

        // TODO: 13/04/2023 suprimer test 



        mainButtonBar = FXMLLoader.load(getClass().getResource("gui/navBar/main.fxml"));
        if(mainButtonBar == null){
            System.err.println("Erreur lors du chargement de la barre de navigation !");
            System.exit(1);
        }
        AnchorPane.setBottomAnchor(mainButtonBar, 0d);
        AnchorPane.setRightAnchor(mainButtonBar, 0d);
        AnchorPane.setLeftAnchor(mainButtonBar, 0d);

        backButton = FXMLLoader.load(getClass().getResource("gui/backButton/main.fxml"));
        AnchorPane.setTopAnchor(backButton, 0d);
        AnchorPane.setLeftAnchor(backButton, 30d);
        backButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> onBackButtonClicked());

        sceneLoaders.put(MAIN_MENU,new FXMLLoader(getClass().getResource("gui/mainMenu/menu.fxml")));
        sceneLoaders.put(GAME_SELECTION_MENU,new FXMLLoader(getClass().getResource("gui/gameTypeSelectionMenu/main.fxml")));
        sceneLoaders.put(FREEPLAY_MENU,new FXMLLoader(getClass().getResource("gui/freeplay/main.fxml")));
        sceneLoaders.put(RULE_SCREEN,new FXMLLoader(getClass().getResource("gui/rule_screen/ruleScreen.fxml")));
        sceneLoaders.put(SELECT_LEVEL,new FXMLLoader(getClass().getResource("gui/select_level/selectLevel.fxml")));
        sceneLoaders.put(LEVEL_PLAY_SRCEEN,new FXMLLoader(getClass().getResource("gui/level_play_sceen/level-play-screen.fxml")));

        ObservableList<Node> children = mainPane.getChildren();

        for(FXMLLoader fxmlLoader : sceneLoaders.values()){
            Pane p = fxmlLoader.load();
            AnchorPane.setLeftAnchor(p, 0d);
            AnchorPane.setRightAnchor(p, 0d);
            AnchorPane.setTopAnchor(p, 0d);
            AnchorPane.setBottomAnchor(p, mainPane.getHeight());
            p.setVisible(false);
            children.add(p);
        }

        children.add(mainButtonBar);
        children.add(backButton);
        setActive(MAIN_MENU);

        //toggleBlur();
        AnchorPane.setLeftAnchor(blur, 0d);
        AnchorPane.setRightAnchor(blur, 0d);
        AnchorPane.setTopAnchor(blur, 0d);
        AnchorPane.setBottomAnchor(blur, 0d);

        popupBackground = FXMLLoader.load(getClass().getResource("gui/popup/popup-background.fxml"));

        blur.getChildren().add(popupBackground);


        //mainPane.getChildren().add(blur);

        AnchorPane.setLeftAnchor(popupBackground, 200d);
        AnchorPane.setTopAnchor(popupBackground, 60d);
        AnchorPane.setBottomAnchor(popupBackground, 60d);
        AnchorPane.setRightAnchor(popupBackground, 200d);


        Scene mainScene = new Scene(mainPane);

        stage.setWidth(960);
        stage.setHeight(720);
        stage.setTitle("SlitherLink !");
        stage.setScene(mainScene);
        stage.show();

        instance = this;

        // test css
        //Slitherlink.getMainInstance().setStyle("test-style");
    }

    public void toggleBlur(){
        active.setEffect(active.getEffect() != null ? null : new GaussianBlur());
        mainButtonBar.setEffect(mainButtonBar.getEffect() != null ? null : new GaussianBlur());
    }

    public void setActive(String paneName){
        // On récupère le paneau
        Pane p = sceneLoaders.get(paneName).getRoot();

        // On regarde si aucun panneau n'est actif
        if(active != null)
            active.setVisible(false);

        if(paneName==LEVEL_PLAY_SRCEEN){
            LevelPlaySceen.seconds=0;
            LevelPlaySceen.boolHandle=false;
            LevelPlaySceen.startTimer();
            p.setDisable(false);
        }else if(LevelPlaySceen.timeline!=null){
            LevelPlaySceen.stopTimer();
        }

        if(screenStack.size() > 1 && screenStack.peek().equals(paneName))
            screenStack.pop();
        screenStack.push(paneName);
        p.setVisible(true);
        backButton.setVisible(paneName != MAIN_MENU);
        active = p;
    }

    public Object getController(String paneName){
        return sceneLoaders.get(paneName).getController();
    }

    public void onBackButtonClicked(){
        if(screenStack.size() > 1){
            screenStack.pop();
            setActive(screenStack.pop());
        }
    }

    public static Slitherlink getMainInstance(){
        return instance;
    }

    public void setStyle(String style){
        mainPane.getStylesheets().clear();
        mainPane.getStylesheets().add(getClass().getResource("style/"+style+".css").toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }



}