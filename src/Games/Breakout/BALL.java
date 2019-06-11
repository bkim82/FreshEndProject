package Games.Breakout;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

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

    public double getYDIRECTION() {
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
        this.imageView.setY(HEIGHT / 2 - this.imageView.getBoundsInLocal().getHeight()/2);
    }


    public BALL createBALL(ImageView myPaddle, int speed) {

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(BALL_IMAGE));
        ball = new BALL (image, speed);
        ball.imageView.setFitHeight(15);
        ball.imageView.setFitWidth(15);
        ball.resetBall();

        return ball;
    }

    public BALL ballPos(double time, ImageView myPaddle, Timeline animation, Stage s, int currentLV,
                        ArrayList<BOX> myBox, int speed, Text winning, Text losing) {

        checkX();

        checkY(animation, s, currentLV, myBox, speed, winning, losing, myPaddle);

        checkPaddle(myPaddle);

        setPos(time);

        return this;


    }

    private void checkX() {
        if (this.imageView.getBoundsInParent().getMaxX() >= WIDTH || this.imageView.getX() <= 0) {
            xDirection = xDirection * -1;
        }
    }

    private int countUndestroyable(ArrayList<BOX> mybox) {
        int count = 0;
        for (BOX brick : mybox) {
            if (brick.checkBrickType() == "undestroyable") {
                count++;
            }
        }
        return count;
    }

    public ImageView getBouncerIV() {
        return this.imageView;
    }


    private void checkY(Timeline animation, Stage s, int currentLV, ArrayList<BOX> myBox, int speed,
                        Text winning, Text losing, ImageView myPaddle) {

        if (myBox.size() <= countUndestroyable(myBox) && this.imageView.getBoundsInParent().getMinY() <= 0
                && (this.imageView.getBoundsInParent().getMinX() >= WIDTH / 3
                && this.imageView.getBoundsInParent().getMaxX() <= 2 * WIDTH / 3)) {
            animation.stop();
            Levels level = new Levels(currentLV);
            level.nextLevel(s, speed, winning, animation);

        }

        else if (this.imageView.getBoundsInParent().getMaxY() >= HEIGHT) {
            if (!this.protectedBall) {
                lives--;
                if (lives <= 0) {
                    losing.setVisible(true);
                    animation.stop();
                } else {
                    this.resetBall();
                    resetPaddle(myPaddle);
                    animation.pause();
                }
            } else {
                yDirection = yDirection * -1;
            }
        } else if (this.imageView.getY() <= 0) {
            yDirection = yDirection * -1;
        }
    }

    private void resetPaddle(ImageView myPaddle) {
        myPaddle.setX(WIDTH / 2 - myPaddle.getBoundsInLocal().getWidth() / 2);
        myPaddle.setY(HEIGHT - 12);
    }
    private void checkPaddle(ImageView myPaddle) {
        double xBouncer = this.imageView.getX() + this.imageView.getBoundsInLocal().getWidth() / 2;
        double xPaddle = myPaddle.getBoundsInLocal().getWidth() / 8;
        if (this.imageView.getBoundsInParent().intersects(myPaddle.getBoundsInParent())) {
            if (xBouncer <= myPaddle.getX() + xPaddle) {
                xDirection = -1.10;
                yDirection = 1;
            } else if (xBouncer >= myPaddle.getX() + 7 * xPaddle) {
                xDirection = 1.10;
                yDirection = 1;
            } else if (xBouncer <= myPaddle.getX() + 2 * xPaddle) {
                xDirection = -0.8;
                yDirection = 1;
            } else if (xBouncer >= myPaddle.getX() + 6 * xPaddle) {
                xDirection = 0.8;
                yDirection = 1;
            } else if (xBouncer <= myPaddle.getX() + 3 * xPaddle) {
                xDirection = -0.5;
                yDirection = 1;
            } else if (xBouncer >= myPaddle.getX() + 5 * xPaddle) {
                xDirection = 0.5;
                yDirection = 1;
            } else if (xBouncer <= myPaddle.getX() + 4 * xPaddle) {
                xDirection = -0.2;
                yDirection = 1;
            } else if (xBouncer >= myPaddle.getX() + 4 * xPaddle) {
                xDirection = 0.2;
                yDirection = 1;
            }
        }
    }
}









}
