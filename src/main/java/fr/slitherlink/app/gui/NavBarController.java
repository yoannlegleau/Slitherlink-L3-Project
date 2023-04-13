package fr.slitherlink.app.gui;

import com.google.api.services.oauth2.model.Userinfo;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserRecord;
import fr.slitherlink.app.FireBase;
import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.app.gui.popup.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class NavBarController {

    private FXMLLoader loader;
    private FireBase db;

    public NavBarController() throws IOException {
        db = FireBase.getInstance();
    }

    @FXML
    private void onSettingsButtonClick() {
        try {
            loader.setLocation(Slitherlink.class.getResource("gui/popup/settings-popup.fxml"));
            Pane root = loader.load();
            SettingsPopup settingsPopup = new SettingsPopup();
            settingsPopup.addContent(root);
            settingsPopup.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onAccountButtonClick() throws IOException, FirebaseAuthException {
        loader = new FXMLLoader();
        Pane root;
        Popup popup;
        if (db.isUserConnected()) {
            loader.setLocation(Slitherlink.class.getResource("gui/popup/profil-popup.fxml"));
            root = loader.load();
            popup = new ProfilPopup();
        } else {
            loader.setLocation(Slitherlink.class.getResource("gui/popup/connection-popup.fxml"));
            root = loader.load();
            popup = new ConnectionPopup();
        }
        popup.addContent(root);
        popup.show();
    }

    public void setScore(int nbPoints) throws FirebaseAuthException {
        db.setScore(nbPoints);
    }


    public void onScoreBoardButtonClick() throws IOException, FirebaseAuthException {
        loader = new FXMLLoader();
        loader.setLocation(Slitherlink.class.getResource("gui/popup/scoreboard-popup.fxml"));
        Pane root = loader.load();
        ScoreBoardPopup popup = new ScoreBoardPopup();
        popup.addContent(root);
        popup.show();

    }


}
