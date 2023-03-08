package fr.slitherlink.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * @author LE GLEAU Yoann
 * @version 1, 08/03/2023
 */
public class Menu {


    public Button buttonPlay;

    @FXML
    public void onPlayButonClick(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) buttonPlay.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Slitherlink.class.getResource("gui/level_play_sceen/level-play-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
