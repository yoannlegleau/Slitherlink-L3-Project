package fr.slitherlink.app.gui.popup;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Popup {
    private Stage stage;
    private Scene scene;
    private VBox layout;

    public Popup(String title, double width, double height) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(width);
        stage.setMinHeight(height);

        layout = new VBox();
        layout.setStyle("-fx-background-color: #252525;");
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                stage.hide();
            }
        });
    }

    public void addContent(Node node) {
        layout.getChildren().add(node);
    }

    public void show() {
        stage.showAndWait();
    }

}