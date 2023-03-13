package fr.slitherlink.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/03/2023
 */
public class LevelEditor extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Slitherlink.class.getResource("gui/level_editor/level-editor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setWidth(Screen.getPrimary().getBounds().getWidth()/1.5);
        stage.setHeight(Screen.getPrimary().getBounds().getHeight()/1.5);
        stage.setTitle("SlitherLink Level Editor!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
