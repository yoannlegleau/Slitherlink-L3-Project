package fr.slitherlink.app.gui.popup;

import fr.slitherlink.app.OAuthAuthenticator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConnectionPopup extends Popup {

    private OAuthAuthenticator oauth;


    public ConnectionPopup() {
        super("Connexion", 700, 500);
        oauth = new OAuthAuthenticator();
    }

    public void onGoogleButtonClick() {
        oauth.authentifier();
    }

}
