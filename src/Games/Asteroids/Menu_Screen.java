package Games.Asteroids;


import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Menu_Screen extends Application {

    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        BorderPane bp = new BorderPane();

        Button play = new Button("PLAY!");
        play.resize(60,60);
        play.alignmentProperty();


        Text text = new Text("To play, you move the direction of where the ship shoots with the left and right buttons." +
                " Press space to shoot bullets, destroying the asteroids in front of the ship. You die" +
                " as soon the asteroid hits you.");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Times New Roman", 30));
        text.setWrappingWidth(500);
        text.setFill(Color.GRAY);
        text.setTranslateY(60);
        text.setTranslateX(30);


        pane.getChildren().addAll(text);

        bp.setCenter(pane);
        bp.setBottom(play);

        AsteroidsRun sim1 = new AsteroidsRun();
//        play.setOnMousePressed(e -> sim1.main(e));

        text.setFill(Color.BLACK);

//        pane.getChildren().add(text);


        Scene scene = new Scene(bp, 570, 500);
        primaryStage.setTitle("Menu Screen"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        }



}
