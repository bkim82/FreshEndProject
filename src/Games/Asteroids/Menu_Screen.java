package Games.Asteroids;


import Games.Asteroids.AsteroidsApp;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        BorderPane bp = new BorderPane();

        Button play = new Button("PLAY!");


        Text text = new Text("To play, you move the direction of where the ship shoots with the left and right buttons." +
                " Press space to shoot bullets, destroying the asteroids in front of the ship. You die" +
                "as soon as you hit the asteroid with your ship.");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Times New Roman", 30));
        text.setWrappingWidth(500);
        text.setFill(Color.GRAY);
        text.setTranslateY(60);
        text.setTranslateX(30);


        pane.getChildren().add(text);
        bp.setBottom(play);
        bp.setCenter(pane);

        AsteroidsApp sim1 = new AsteroidsApp();
        play.setOnMousePressed(e -> sim1.start(primaryStage));

        // Set a random color and opacity
        text.setFill(Color.BLACK);

//        pane.getChildren().add(text);


        Scene scene = new Scene(bp, 570, 500);
        primaryStage.setTitle("Menu Screen"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        }



}
