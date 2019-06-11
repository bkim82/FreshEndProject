package Games.Breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BALL extends Breakout {

    public static final String BALL_IMAGE = "ball.gif";
    private Integer lives = 3;
    private double xDirection;
    private double yDirection;
    private int speed;
    private boolean protectedBall;
    private BALL ball;
    private ImageView imageView;

    public BALL(Image image, int fast) {
        xDirection = 1;
        yDirection = 1;

        imageView = new ImageView((image));
        speed = fast;
        protectedBall = false;
    }

    public ImageView getBallImage() {
        return this.imageView;
    }

    public void protectBall() {
        this.protectedBall= true;
    }

    public void changeSpeed(int fast) {
        this.speed = fast;
    }

    public double getXDirection() {
        return this.xDirection;
    }

    public double getY_DIRECTION() {
        return this.yDirection;
    }

    public void setPos(double elapsedTime) {
        this.imageView.setX(this.imageView.getX() + xDirection * this.speed * elapsedTime);
        this.imageView.setY(this.imageView.getY() - yDirection * this.speed * elapsedTime);
    }

    public void changeX() {
        this.xDirection = this.xDirection * -1;
        this.setPos(delay2);
    }

    public void changeY() {

        this.yDirection = this.yDirection * -1;
        this.setPos(delay2);
    }

    public Integer lives() {
        return this.lives;
    }

    public void addLife() {
        this.lives++;
    }

    public void resetBall() {
        this.imageView.setX(WIDTH / 2 - this.imageView.getBoundsInLocal().getWidth() / 2);
    }


    public BALL createBouncer(ImageView myPaddle, int speed) {

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(BALL_IMAGE));
        ball = new BALL (image, speed);
        ball.imageView.setFitHeight(15);
        ball.imageView.setFitWidth(15);
        ball.resetBall();

        return ball;
    }







}
