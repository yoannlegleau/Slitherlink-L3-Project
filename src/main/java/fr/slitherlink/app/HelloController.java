package fr.slitherlink.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    String text;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(text);
    }

    public HelloController() {
        super();
    }

    public HelloController(String welcomeText) {
        this();
        text = welcomeText;
    }
}