package Games.Asteroids;


import Games.Asteroids.AsteroidsRun;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class Menu_Screen extends Application {

    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #707070");
        BorderPane bp = new BorderPane();

        Image astero = new Image("image/aster.png");

        ImageView aster1 = new ImageView(astero);
        aster1.setFitWidth(150);
        aster1.setFitHeight(150);
        aster1.setX(-15);
        aster1.setY(280);
        pane.getChildren().add(aster1);

        ImageView aster2 = new ImageView(astero);
        aster2.setFitWidth(150);
        aster2.setFitHeight(150);
        aster2.setX(450);
        aster2.setY(280);
        pane.getChildren().add(aster2);

        Image play = new Image("image/play.png");
        ImageView player = new ImageView(play);
        player.setX(120);
        player.setY(255);
        player.setFitHeight(200);
        player.setFitWidth(350);
        pane.getChildren().add(player);


        Text text = new Text("To play, you move the direction of where the ship shoots with the left and right buttons." +
                " Press space to shoot bullets, destroying the asteroids in front of the ship. You die" +
                " as soon the asteroid hits you.");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Times New Roman", 30));
        text.setWrappingWidth(500);
        text.setFill(Color.GRAY);
        text.setTranslateY(60);
        text.setTranslateX(65);


        pane.getChildren().addAll(text);

        bp.setCenter(pane);

        AsteroidsRun asteroid = new AsteroidsRun();
        player.setOnMousePressed(e -> asteroid.start(primaryStage));

        text.setFill(Color.BLACK);

//        pane.getChildren().add(text);


        Scene scene = new Scene(bp, 610, 500);
        primaryStage.setTitle("Menu Screen"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }



}