package fr.slitherlink.app.gui;

import fr.slitherlink.app.Slitherlink;
import fr.slitherlink.save.technic.ListTechnic;
import fr.slitherlink.save.technic.Technic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

/**
 * @author LE GLEAU Yoann
 * @version 1, 13/04/2023
 */
public class HelpController {
    public Text longText;
    public ImageView image;
    public Text titleText;

    public HelpController() {
    }

    @FXML
    public void initialize() {
        titleText.setText("test");
        longText.setText("test");
    }

    public void setTechnicId(int id) {
        Technic technic = ListTechnic.getInstance().getTechnic(id);
        titleText.setText(technic.getTitle());
        longText.setText(technic.getDesc());
        File img = new File("src/main/resources/fr/slitherlink/technic/images/Tech" + technic.getId() + ".png");
        Image imageI = new Image(img.toURI().toString());
        image.setImage(imageI);
    }

}
