package Games.Breakout;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ROCKET {
    public static final String MISSILE_IMAGE = "missile.gif";
    public static final int MISSILE_SPEED = 90;
    private ImageView image;
    private int width = 22;
    private int height = 50;
    private double rocketX;
    private double rocketY;
    private ArrayList<ROCKET> myROCKETS;
    private boolean used;


    public ROCKET(ImageView myPaddle) {
        Image m = new Image(getClass().getClassLoader().getResourceAsStream(MISSILE_IMAGE));
        image = new ImageView(m);
        this.setInitialROCKETPos(myPaddle);
        image.setFitWidth(width);
        image.setFitHeight(height);
        image.setVisible(false);
        used = false;
    }
    public void setInitialROCKETPos(ImageView myPaddle) {
        this.rocketX = myPaddle.getX();
        this.rocketY = myPaddle.getY();
        this.image.setX(this.rocketX + width / 2);
        this.image.setY(this.rocketY);
        image.setVisible(true);
    }


    public boolean checkRocket() {
        return this.used;
    }


    public void resetRocket() {
        this.used = false;
    }


    public void useRocket() {
        this.used = true;
    }


    public ImageView getRocketIV() {
        return this.image;
    }


    public ArrayList<ROCKET> createROCKETS(ImageView myPaddle) {
        myROCKETS = new ArrayList<ROCKET>();
        for (int i = 0; i < 5; i++) {
            ROCKET m = new ROCKET(myPaddle);
            myROCKETS.add(m);
        }
        return myROCKETS;
    }

    public void ROCKETPos(double elapsedTime) {
        this.image.setY(this.image.getY() - MISSILE_SPEED * elapsedTime);
    }


}
