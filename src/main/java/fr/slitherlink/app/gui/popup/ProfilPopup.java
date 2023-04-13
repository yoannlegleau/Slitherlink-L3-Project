package fr.slitherlink.app.gui.popup;

import com.google.api.services.oauth2.model.Userinfo;
import com.google.firebase.auth.FirebaseAuthException;
import fr.slitherlink.app.FireBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ProfilPopup extends Popup {

    public Label userName;
    public Label mailAdress;
    public ImageView userPicture;
    private FireBase db;


    public ProfilPopup() throws IOException {
        super("Profile", 500, 500);
        db = FireBase.getInstance();
    }

    @FXML
    private void initialize() throws FirebaseAuthException, ExecutionException, InterruptedException {
        Userinfo user = db.getUserInfoByUid(db.getCurrentUser());
        userName.setText(user.getName());
        userPicture.setImage(new Image(user.getPicture()));
        //TODO : radius img
        userPicture.setStyle("-fx-border-radius: 50px");
        mailAdress.setText(user.getEmail());
    }

    @FXML
    public void onLogOutButton() {
        db.logOut();
    }
}
