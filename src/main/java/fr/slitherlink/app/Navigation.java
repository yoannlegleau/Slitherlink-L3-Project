package fr.slitherlink.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 12/03/2023
 */
public class Navigation {
    public static void toGameScreen(Stage context) throws IOException {;
        FXMLLoader fxmlLoader = new FXMLLoader(Slitherlink.class.getResource("gui/level_play_sceen/level-play-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        context.setScene(scene);
        context.show();
    }
}
