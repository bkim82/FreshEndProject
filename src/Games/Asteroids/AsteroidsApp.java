package Games.Asteroids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class AsteroidsApp extends Application {

    private Pane root;

    private Label scoreBoard;

    private int enemyCount;

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
        if (enemyCount == 14){
            return;
        }
        enemies.add(enemy);
        addGameObject(enemy, x, y);
        enemyCount++;
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
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
                    scoreBoard.setText("Score: " + score);
                    enemyCount--;
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

          super(new ImageView("image/asteroid_four.png"));

//            super(new Circle(15, 15, 15, Color.DARKGRAY));


        }
    }

    private static class Bullet extends GameObject {
        Bullet() {

            super(new Rectangle(5, 5, Color.RED));

        }
    }




    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent(),Color.BEIGE));

        root.setStyle("-fx-background-color: #707070");

        scoreBoard = new Label();

        scoreBoard.setText("Score: " + score);
        scoreBoard.setFont(Font.font("Times New Roman", 40));
        scoreBoard.setTranslateX(10);
        root.getChildren().add(scoreBoard);

        primaryStage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                player.rotateLeft();
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.rotateRight();
            } else if (e.getCode() == KeyCode.SPACE) {
                Bullet bullet = new Bullet();
                bullet.setVelocity(player.getVelocity().normalize().multiply(5));
                addBullet(bullet, player.getView().getTranslateX()+25, player.getView().getTranslateY()+20);
            }
        });


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}