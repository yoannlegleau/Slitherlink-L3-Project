package fr.slitherlink.app.gui.popup;

import com.google.api.services.oauth2.model.Userinfo;
import com.google.firebase.auth.FirebaseAuthException;
import fr.slitherlink.app.FireBase;
import fr.slitherlink.game.Score;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ScoreListCell extends ListCell<Score> {

    private FireBase db;

    public ScoreListCell() throws IOException {
        db = FireBase.getInstance();
    }

    @Override
    protected void updateItem(Score score, boolean empty) {
        super.updateItem(score, empty);
        if (empty || score == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_LEFT);
                Userinfo userinfo = db.getUserInfoByUid(score.getUserId());

                ImageView userImage = new ImageView(new Image(userinfo.getPicture()));
                userImage.setFitWidth(35);
                userImage.setFitHeight(35);
                userImage.setStyle("-fx-background-radius: 5;");
                Label userName = new Label(userinfo.getName());
                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);
                userName.setPadding(new Insets(0, 0, 0, 10));
                Label scoreLabel = new Label(Integer.toString(score.getNbPoints()) + " points");
                hBox.getChildren().addAll(userImage, userName, region, scoreLabel);
                setGraphic(hBox);
            } catch (InterruptedException | ExecutionException e) {

            }
        }

    }
}
