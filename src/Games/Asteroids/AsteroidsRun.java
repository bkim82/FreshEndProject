package Games.Asteroids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class AsteroidsRun extends Application {

    private Pane root;

    private List<GameObject> bullets = new ArrayList<>();
    private List<GameObject> enemies = new ArrayList<>();
    public int score = 0;
    Image rocket = new Image ("image/rocket.png");


    private GameObject player;

    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(600, 600);

        player = new Player();
        player.setVelocity(new Point2D(1, 0));
        addGameObject(player, 300, 300);

        AnimationTimer timer = new AnimationTimer() {

            public void handle(long now) {
                onUpdate();
            }
        };

        timer.start();

        return root;
    }

    private void addBullet(GameObject bullet, double x, double y) {
        bullets.add(bullet);
        addGameObject(bullet, x, y);
    }

    private void addEnemy(GameObject enemy, double x, double y) {
        enemies.add(enemy);
        addGameObject(enemy, x, y);
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private void addScoreBoard() {


        Button board = new Button();

        board.setText("Lives:");
        board.setFont(Font.font("Verdana", 30));
        board.setTextFill(Color.LIGHTGREEN);
        board.setStyle("-fx-background-color: #55aa33");
        board.prefWidthProperty().bind(root.widthProperty().divide(3));
        board.prefHeightProperty().bind(root.heightProperty().divide(7));
        board.layoutYProperty().bind(root.heightProperty().divide(50));
        board.layoutXProperty().bind(root.widthProperty().divide(1.6));

        root.getChildren().add(board);
    }

//    private void PlayerMeetsAsteroid() {
//        player.isColliding(bullets);
//
//    }
//    }

    private void onUpdate() {
        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.isColliding(enemy)) {
                    bullet.setAlive(false);
                    enemy.setAlive(false);
                    score++;

                    root.getChildren().removeAll(bullet.getView(), enemy.getView());
                }

            }
        }





        bullets.removeIf(GameObject::isDead);
        enemies.removeIf(GameObject::isDead);

        bullets.forEach(GameObject::update);
        enemies.forEach(GameObject::update);

        player.update();

        if (Math.random() < 0.02) {
            addEnemy(new Enemy(), Math.random() * root.getPrefWidth(), Math.random() * root.getPrefHeight());
        }
    }



    private static class Player extends GameObject {
        Player() {
//            super.GameObject(rocket);
            super(new ImageView("image/asteroid_two.png"));
        }
    }

    private static class Enemy extends GameObject {
        Enemy() {

            super(new ImageView("image/asteroid_four" +
                    ".png"));



        }
    }

    private static class Bullet extends GameObject {
        Bullet() {

            super(new Rectangle(5, 5, Color.RED));

        }
    }




    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent(),Color.BLACK);
        stage.setScene(scene);
//        scene.getStylesheets().addAll(this.getClass().getResource("large.png").toExternalForm());
//        Background background = new Background(new Image ("large.png"));

        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                player.rotateLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.rotateRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                Bullet bullet = new Bullet();
                bullet.setVelocity(player.getVelocity().normalize().multiply(5));
                addBullet(bullet, player.getView().getTranslateX()+90, player.getView().getTranslateY()+20);
            }
        });


        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}