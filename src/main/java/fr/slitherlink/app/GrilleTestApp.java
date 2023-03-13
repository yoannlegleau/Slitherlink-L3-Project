package fr.slitherlink.app;

import fr.slitherlink.game.Game;
import fr.slitherlink.app.component.PuzlGridGroup;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GrilleTestApp extends Application{

    int width = 500, height = 500;

    PuzlGridGroup gridGroup;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grille");
        primaryStage.show();

        Game game = new Game(1);

        gridGroup = new PuzlGridGroup(game,width);
        Scene scene = new Scene(gridGroup, width, height);
        primaryStage.setScene(scene);

    }


}
