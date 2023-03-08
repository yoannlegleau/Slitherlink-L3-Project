package fr.slitherlink.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Slitherlink extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Slitherlink.class.getResource("gui/mainMenu/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setWidth(Screen.getPrimary().getBounds().getWidth()/1.5);
        stage.setHeight(Screen.getPrimary().getBounds().getHeight()/1.5);
        stage.setTitle("SlitherLink!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}