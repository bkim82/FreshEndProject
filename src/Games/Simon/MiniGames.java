package Games.Simon;

import Games.Asteroids.Menu_Screen;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MiniGames extends Application {

    public void start (Stage primaryStage) {

        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(180, 180, 180), CornerRadii.EMPTY, Insets.EMPTY)));

        Text text = new Text("Which would you like to play?");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Times New Roman", 60));
        text.setWrappingWidth(500);
        text.setFill(Color.GRAY);
        text.setTranslateY(60);
        text.setTranslateX(55);
        pane.getChildren().add(text);

        Image simon = new Image("image/simon2.png");
        ImageView simons = new ImageView(simon);
        simons.fitHeightProperty().bind(pane.heightProperty().divide(2));
        simons.fitWidthProperty().bind(pane.widthProperty().divide(3));
        simons.setY(150);
        simons.setX(75);
        pane.getChildren().add(simons);

        Image asteroid = new Image("image/asteroid.png");
        ImageView atari = new ImageView(asteroid);
        atari.fitHeightProperty().bind(pane.heightProperty().divide(2.5));
        atari.fitWidthProperty().bind(pane.widthProperty().divide(3.75));
        atari.setY(175);
        atari.setX(335);
        pane.getChildren().add(atari);

        Simon_Says sim1 = new Simon_Says();
        Menu_Screen sim2 = new Menu_Screen();

//      Brandon, to make this work you'll need to type in the name of the file that breakout runs from
//      and name the variable. After that use the image I already created called atari and do setOnMousePressed
//      (e -> fileVariable.start(stage name)) then clicking the image should make the game appear. GL

        simons.setOnMousePressed(e -> sim1.start(primaryStage));
        atari.setOnMousePressed(e -> sim2.start(primaryStage));


        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mini Games!");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

