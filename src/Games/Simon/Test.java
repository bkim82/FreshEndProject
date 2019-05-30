package Games.Simon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class Test extends Application {

    public Arc make(int start){
        Arc a = new Arc();
        a.setCenterX(250);
        a.setCenterY(250);
        a.setStartAngle(start);
        a.setLength(90);
        a.setRadiusY(50);
        a.setRadiusX(50);
        return a;
    }

    public void start (Stage primaryStage){



        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #404040");

        Button yellow = new Button();
        yellow.setStyle("-fx-background-color: #ffff66");
//        yellow.setTranslateY();
//        yellow.setTranslateX();

        Button red = new Button();
        red.setStyle("-fx-background-color: #ff3300");
//        red.setTranslateY();
//        red.setTranslateX();

        Button green = new Button();
        green.setStyle("-fx-background-color: #55aa33");
//        green.setTranslateY();
//        green.setTranslateX();

        Button blue = new Button();
        blue.setShape(make(270));
        blue.setStyle("-fx-background-color: #0099ff");
        blue.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        blue.setTranslateY(100);
        blue.setTranslateX(100);

        pane.getChildren().add(blue);


        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simon");
        primaryStage.show();

    }
}
