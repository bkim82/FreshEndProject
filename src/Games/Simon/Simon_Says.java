package Games.Simon;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Iterator;

public class Simon_Says extends Application {

    private static final String PATH = "/image/";
    private Iterator<Integer> colors;

    private int pos = -1;

    private ArrayList<Integer> order = new ArrayList<>();

    private MediaPlayer red1, green1, blue1, yellow1;

    private Button red, green, blue, yellow;

    private final int BLUE = 1;
    private final int YELLOW = 2;
    private final int RED = 3;
    private final int GREEN = 4;

    private Scene scene;
    private Pane pane;

    private Button reset;

    private final String BLUEPRIME = "0099ff";
    private final String BLUEEND = "aaddff";

    private final String YELLOWPRIME = "ffff44";
    private final String YELLOWEND = "ffffee";

    private final String REDPRIME = "ff3300";
    private final String REDEND = "ffaaaa";

    private final String GREENPRIME = "55aa33";
    private final String GREENEND = "55ee55";

    private Label counter;

    private int clicked;

    private boolean clickable = false;

    private PauseTransition pause = new PauseTransition(Duration.millis(750));

    private Circle wrong;
    private Circle right;

    private int turn = 1;

    public Button create(String style, Color stroke, double translateX, double translateY, Pane pane){
        Button b = new Button();
        b.setStyle("-fx-background-color: #" + style);
        b.setBorder(new Border(new BorderStroke(stroke,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        b.translateYProperty().bind(pane.heightProperty().divide(translateY));
        b.translateXProperty().bind(pane.widthProperty().divide(translateX));
        return b;
    }

    public void reset(Stage primaryStage){
        order.clear();
        turn = 1;
        pos = -1;
        start(primaryStage);
    }

    public void setBlink(Button color, String valueEnd, String valueStart, MediaPlayer sound, int button){
        color.setOnMouseEntered(e -> color.setStyle("-fx-background-color: #" + valueEnd));
        color.setOnMouseExited(e -> color.setStyle("-fx-background-color: #" + valueStart));
        color.setOnAction(e -> {
            sound.seek(Duration.ZERO);
            sound.play();
            checkPress(button);
        });
    }


    public void blinkNext() {
        if(colors.hasNext()) {
            light(colors.next());
        }
        else {
            pos = 0;
        }
    }

    public void blink(String prime, String end, Button color, MediaPlayer sound) {
        pause.setOnFinished(e -> {
            color.setStyle("-fx-background-color: #" + prime);
            blinkNext();
        });
        color.setStyle("-fx-background-color: #" + end);
        sound.seek(Duration.ZERO);
        sound.play();
        pause.play();
    }


    public void light(int button){
        if (button == 1){
            blink(BLUEPRIME, BLUEEND, blue, blue1);
        }
        else if (button == 2){
            blink(YELLOWPRIME, YELLOWEND, yellow, yellow1);
        }
        else if (button == 3){
            blink(REDPRIME, REDEND, red, red1);
        }
        else{
            blink(GREENPRIME, GREENEND, green, green1);
        }
    }


    public void game(){

        colors = order.iterator();
        blinkNext();

        if (pos == -1){
            counter.setText("0 / 1");
        }
        else {
            counter.setText(pos + " / " + turn);
        }
    }



    public void checkPress (int color) {
        int expectedColor = order.get(pos);

        if (expectedColor == color) {
            pos++;
            counter.setText(pos + " / " + turn);
            if (pos == order.size()) {
                pause.setDuration(Duration.millis(1100));
                pause.setOnFinished(e -> {
                    pause.setDuration(Duration.millis(750));
                    right.setFill(Color.LIGHTGREEN);
                    turn++;
                    counter.setText("0 / " + turn);
                    order.add((int)(Math.random() * 4)  + 1);
                    colors = order.iterator();
                    blinkNext();
                });
                right.setFill(Color.GREEN);
                pause.play();
            }
        }
        else {
            pause.setOnFinished(e -> wrong.setFill(Color.RED));
            wrong.setFill(Color.CRIMSON);
            pause.play();
            pane.getChildren().remove(reset);

            Rectangle r = new Rectangle(500, 500);
            r.setFill(Color.rgb(64, 64, 64, 0.7));
            pane.getChildren().add(r);
            pane.getChildren().add(reset);

            Label go = new Label();
            go.setText("GAME OVER");

        }

    }

    public void start(Stage primaryStage){

        order.add((int)(Math.random() * 4) + 1);

        pane = new Pane();
        pane.setStyle("-fx-background-color: #404040");

        yellow = create(YELLOWPRIME, Color.LIGHTGOLDENRODYELLOW, 1.7,5.5, pane);
        yellow.prefHeightProperty().bind(pane.heightProperty().divide(4));
        yellow.prefWidthProperty().bind(pane.widthProperty().divide(4));

        red = create(REDPRIME, Color.PINK.brighter().brighter(), 5.5,  1.7, pane);
        red.prefHeightProperty().bind(pane.heightProperty().divide(4));
        red.prefWidthProperty().bind(pane.widthProperty().divide(4));

        green = create(GREENPRIME, Color.LIGHTGREEN, 1.7, 1.7, pane);
        green.prefHeightProperty().bind(pane.heightProperty().divide(4));
        green.prefWidthProperty().bind(pane.widthProperty().divide(4));

        blue = create(BLUEPRIME, Color.LIGHTBLUE, 5.5, 5.5, pane);
        blue.prefHeightProperty().bind(pane.heightProperty().divide(4));
        blue.prefWidthProperty().bind(pane.widthProperty().divide(4));

        Image image = new Image("image/simon1.png");
        ImageView logo = new ImageView(image);
        logo.fitWidthProperty().bind(pane.widthProperty().divide(3));
        logo.fitHeightProperty().bind(pane.heightProperty().divide(3));
        logo.layoutXProperty().bind(pane.widthProperty().divide(2.9));
        logo.layoutYProperty().bind(pane.heightProperty().divide(2.9));

        wrong = new Circle();
        wrong.setRadius(15);
        wrong.setFill(Color.PINK);
        wrong.setStroke(Color.RED);
        wrong.setStrokeWidth(4);
        wrong.translateXProperty().bind(pane.widthProperty().divide(4));
        wrong.translateYProperty().bind(pane.heightProperty().divide(9));

        right = new Circle();
        right.setRadius(15);
        right.setFill(Color.LIGHTGREEN);
        right.setStroke(Color.DARKGREEN);
        right.setStrokeWidth(4);
        right.translateXProperty().bind(pane.widthProperty().divide(2.7));
        right.translateYProperty().bind(pane.heightProperty().divide(9));

        red1 = new MediaPlayer(new Media(getClass().getResource(PATH + "red.wav").toExternalForm()));
        green1 = new MediaPlayer(new Media(getClass().getResource(PATH + "green.wav").toExternalForm()));
        blue1 = new MediaPlayer(new Media(getClass().getResource(PATH + "blue.wav").toExternalForm()));
        yellow1 = new MediaPlayer(new Media(getClass().getResource(PATH + "yellow.wav").toExternalForm()));

        setBlink(red, REDEND, REDPRIME, red1, RED);
        setBlink(green, GREENEND, GREENPRIME, green1, GREEN);
        setBlink(blue, BLUEEND, BLUEPRIME, blue1, BLUE);
        setBlink(yellow,YELLOWEND, YELLOWPRIME, yellow1, YELLOW);

        Button start = new Button();
        start.setText("Start!");
        start.setFont(Font.font ("Verdana", 30));
        start.setTextFill(Color.LIGHTGREEN);
        start.setStyle("-fx-background-color: #55aa33");
        start.prefWidthProperty().bind(pane.widthProperty().divide(3));
        start.prefHeightProperty().bind(pane.heightProperty().divide(7));
        start.layoutYProperty().bind(pane.heightProperty().divide(50));
        start.layoutXProperty().bind(pane.widthProperty().divide(1.6));

        counter = new Label();
        counter.setText("0 / " + turn);
        counter.translateXProperty().bind(pane.widthProperty().divide(10));
        counter.translateYProperty().bind(pane.heightProperty().divide(2.2));
        counter.maxHeightProperty().bind(pane.heightProperty().divide(7));
        counter.maxWidthProperty().bind(pane.widthProperty().divide(4));
        counter.setFont(Font.font("Oswald", 40));
        counter.setTextFill(Color.BLACK);

        reset = new Button();
        reset.layoutYProperty().bind(start.layoutYProperty());
        reset.layoutXProperty().bind(start.layoutXProperty());
        reset.prefHeightProperty().bind(start.prefHeightProperty());
        reset.prefWidthProperty().bind(start.prefWidthProperty());
        reset.setStyle("-fx-background-color: #ff1a1a");
        reset.setText("Reset!");
        reset.setFont(Font.font ("Verdana", 30));

        pane.getChildren().addAll(blue, yellow, green, red, logo, wrong, right, start, counter);

        start.setOnAction(e -> {
            pane.getChildren().remove(start);
            pane.getChildren().add(reset);
            pause.setOnFinished(t -> game());
            pause.play();
        });

        reset.setOnAction(e -> reset(primaryStage));

        scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simon");
        primaryStage.show();

    }

}
