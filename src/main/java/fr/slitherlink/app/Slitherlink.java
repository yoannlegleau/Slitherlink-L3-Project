package fr.slitherlink.app;

import fr.slitherlink.app.gui.MainWindowController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Slitherlink extends Application {

    private AnchorPane mainPane;
    private ButtonBar mainButtonBar;

    // Les différents écrans
    private Map<String, Pane> scenes;

    @Override
    public void start(Stage stage) throws IOException {
        // Notre application est d'un AnchorPane qui contient
        // Le contenu principal 
        // Le bouton retour
        // Et le barre de boutons

        scenes = new HashMap<>();

        mainPane = new AnchorPane();
        mainPane.setMinSize(960, 720);

        mainButtonBar = (ButtonBar) FXMLLoader.load(getClass().getResource("gui/navBar/main.fxml"));
        if(mainButtonBar == null){
            System.err.println("Erreur lors du chargement de la barre de navigation !");
            System.exit(1);
        }
        AnchorPane.setBottomAnchor(mainButtonBar, 0d);
        AnchorPane.setRightAnchor(mainButtonBar, 0d);
        AnchorPane.setLeftAnchor(mainButtonBar, 0d);


        scenes.put("mainMenu", (Pane) FXMLLoader.load(getClass().getResource("gui/mainMenu/menu.fxml")));

        ObservableList<Node> children = mainPane.getChildren();



        for(Pane p : scenes.values()){
            AnchorPane.setLeftAnchor(p, 0d);
            AnchorPane.setRightAnchor(p, 0d);
            AnchorPane.setTopAnchor(p, 0d);
            AnchorPane.setBottomAnchor(p, mainPane.getHeight());
            p.setVisible(false);
            children.add(p);
        }

        children.add(mainButtonBar);

        scenes.get("mainMenu").setVisible(true);

        Scene mainScene = new Scene(mainPane);

        stage.setWidth(960);
        stage.setHeight(720);
        stage.setTitle("SlitherLink !");
        stage.setScene(mainScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}