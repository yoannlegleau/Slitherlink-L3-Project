package fr.slitherlink.app.gui.popup;

import fr.slitherlink.app.OAuthAuthenticator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class ConnectionPopup extends Popup {

    private OAuthAuthenticator oauth;
    private WebView webView;


    public ConnectionPopup() {
        super("Connexion", 700, 500);
        oauth = new OAuthAuthenticator();
    }

    public void onGoogleButtonClick() {
        oauth.authentifier();
    }

}
