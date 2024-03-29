package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.app.gui.popup.ConnectionPopup;
import fr.slitherlink.app.gui.popup.ProfilPopup;
import fr.slitherlink.app.gui.popup.SettingsPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NavBarController {

    @FXML
    private void onSettingsButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(Slitherlink.class.getResource("gui/popup/settings-popup.fxml"));
            Pane root = loader.load();
            SettingsPopup settingsPopup = new SettingsPopup();
            settingsPopup.addContent(root);
            settingsPopup.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onAccountButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(Slitherlink.class.getResource("gui/popup/connection-popup.fxml"));
            Pane root = loader.load();
            ConnectionPopup connectionPopup = new ConnectionPopup();
            connectionPopup.addContent(root);
            connectionPopup.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
