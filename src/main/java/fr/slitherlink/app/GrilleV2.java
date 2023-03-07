package fr.slitherlink.app;

import fr.slitherlink.game.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GrilleV2 extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grille");
        primaryStage.show();

        Game game = new Game(1);
        int pxSize = 400;

        Scene scene = new Scene(new PuzlGridGroup(game,pxSize), pxSize, pxSize);
        primaryStage.setScene(scene);
    }
}
