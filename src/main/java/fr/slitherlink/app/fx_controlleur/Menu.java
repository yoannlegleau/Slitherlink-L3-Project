package fr.slitherlink.app.fx_controlleur;

import fr.slitherlink.app.Navigation;
import fr.slitherlink.app.Slitherlink;
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
        Navigation.toGameScreen((Stage) buttonPlay.getScene().getWindow());
    }
}
