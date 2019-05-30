package Games.Simon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;

public class Test extends Application {


    public static Button create(String style, Color stroke, double translateX, double translateY, Pane pane){
        Button b = new Button();
        b.setStyle("-fx-background-color: #" + style);
        b.setBorder(new Border(new BorderStroke(stroke,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        b.translateYProperty().bind(pane.heightProperty().divide(translateY));
        b.translateXProperty().bind(pane.widthProperty().divide(translateX));
        return b;
    }

    @Override
    public void start(Stage primaryStage){


        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #404040");

        Button yellow = create("ffff66", Color.LIGHTGOLDENRODYELLOW, 1.7,5.5, pane);
        yellow.prefHeightProperty().bind(pane.heightProperty().divide(4));
        yellow.prefWidthProperty().bind(pane.widthProperty().divide(4));

        Button red = create("ff3300", Color.PINK, 5.5,  1.7, pane);
        red.prefHeightProperty().bind(pane.heightProperty().divide(4));
        red.prefWidthProperty().bind(pane.widthProperty().divide(4));


        Button green = create("55aa33", Color.LIGHTGREEN, 1.7, 1.7, pane);
        green.prefHeightProperty().bind(pane.heightProperty().divide(4));
        green.prefWidthProperty().bind(pane.widthProperty().divide(4));


        Button blue = create("0099ff", Color.LIGHTBLUE, 5.5, 5.5, pane);
        blue.prefHeightProperty().bind(pane.heightProperty().divide(4));
        blue.prefWidthProperty().bind(pane.widthProperty().divide(4));
        pane.getChildren().addAll(blue, yellow, green, red);


        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simon");
        primaryStage.show();

    }
}
