package fr.slitherlink.app;

import fr.slitherlink.app.fx_controlleur.component.PuzllGridGroup;
import fr.slitherlink.game.Game;
import fr.slitherlink.save.gamesave.GameSaveResourceManageur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GrilleTestApp extends Application{

    int width = 500, height = 500;

    PuzllGridGroup gridGroup;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grille");
        primaryStage.show();

        Game game = new Game(new GameSaveResourceManageur(1));

        gridGroup = new PuzllGridGroup(game,width);
        Scene scene = new Scene(gridGroup, width, height);
        primaryStage.setScene(scene);

    }


}
