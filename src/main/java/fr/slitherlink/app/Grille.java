package fr.slitherlink.app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.scene.input.*;

public class Grille extends Application implements Comparable {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grille");
        primaryStage.show();

        Group root = getGroup(1);
        Scene scene = new Scene(root, 200, 200);
        scene.fillProperty().set(Color.GRAY);
        primaryStage.setScene(scene);
    }

    private Group getGroup(int size){
        Group root = new Group();

        Rectangle droite = new Rectangle(160, 40, 0, 110);
        Rectangle gauche = new Rectangle(40, 40, 0, 110);
        Rectangle haut = new Rectangle(40, 40, 110, 0);
        Rectangle bas = new Rectangle(50, 150, 110, 0);

        droite.setStrokeWidth(10);
        droite.setStroke(Color.DARKGRAY);

        gauche.setStrokeWidth(10);
        gauche.setStroke(Color.DARKGRAY);

        haut.setStrokeWidth(10);
        haut.setStroke(Color.DARKGREY);

        bas.setStrokeWidth(10);
        bas.setStroke(Color.DARKGREY);

        droite.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchBoutton(droite);
            }
        });

        gauche.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchBoutton(gauche);
            }
        });

        haut.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchBoutton(haut);
            }
        });

        bas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchBoutton(bas);
            }
        });

        root.getChildren().addAll(droite, gauche, haut, bas);

        return root;
    }

    private void switchBoutton(Rectangle r){
        if(r.getStroke() == Color.DARKGRAY){
            r.setStroke(Color.BLACK);
        }else{
            r.setStroke(Color.DARKGRAY);
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
