package fr.slitherlink.app.gui.popup;

import com.google.api.services.oauth2.model.Userinfo;
import com.google.firebase.auth.FirebaseAuthException;
import fr.slitherlink.app.FireBase;
import fr.slitherlink.game.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ScoreBoardPopup extends Popup {

    @FXML
    public ListView<Score> scoreListView;
    private FireBase db = FireBase.getInstance();

    public ScoreBoardPopup() throws IOException {
        super("ScoreBoard", 600,600);
    }

    public void initialize() throws ExecutionException, FirebaseAuthException, InterruptedException {
        List<Score> scoreBoard = db.getAllScores();
        ObservableList<Score> scores = FXCollections.observableArrayList(scoreBoard);
        scoreListView.setItems(scores);
        scoreListView.setCellFactory(param -> {
            try {
                return new ScoreListCell();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
