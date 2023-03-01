package fr.slitherlink.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.scene.input.*;





public class Grille1x1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Grille 1x1");
        primaryStage.show();

        Group root = getGroup(1);
        Scene scene = new Scene(root, 200, 200);
        scene.fillProperty().set(Color.GRAY);
        primaryStage.setScene(scene);
    }

    /**
     * @param size nombre de ligne et de colonne de la grille
     * @return groupe contenant la grille
     */
    private Group getGroup(int size) {
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

        Circle droiteP = new Circle(160, 35, 5, Color.RED);
        /*
        Point2D gaucheP = new Point2D(40, 40);
        Point2D hautP = new Point2D(50, 30);
        Point2D basP = new Point2D(50, 150);
        */



        root.getChildren().add(droite);
        root.getChildren().add(gauche);
        root.getChildren().add(haut);
        root.getChildren().add(bas);
        //root.getChildren().add(droiteP);
        return root;
    }

    public Scene creationScene(int x, int y) {
        Group root = new Group();
        Scene scene = new Scene(root, x, y);
        return scene;
    }

    public void switchBoutton(Rectangle boutton) {
        if (boutton.getStroke() == Color.DARKGREY) {
            boutton.setStroke(Color.SKYBLUE);
        }
        else if (boutton.getStroke() == Color.SKYBLUE) {
            boutton.setStroke(Color.RED);
        }
        else {
            boutton.setStroke(Color.DARKGREY);
        }
    }
}
