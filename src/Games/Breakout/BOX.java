package Games.Breakout;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BOX {
    private int boxLives;
    private int WIDTH;
    private int HEIGHT;
    private boolean havePowerup;
    private String boxType;
    private ImageView box;


    public BOX(int width, int height, String type, int lives, boolean power) {
        WIDTH = width;
        HEIGHT = height;
        boxType = type;
        boxLives = lives;
        havePowerup = power;
        Image brickImage = new Image(getClass().getClassLoader().getResourceAsStream(boxType + "_box.gif"));
        box= new ImageView(brickImage);
        box.setFitWidth(WIDTH);
        box.setFitHeight(HEIGHT);
    }


    public String checkBrickType() {
        return this.boxType;
    }

    public void minuslife() {
        this.boxLives--;
    }

    public int checkBrickLives() {
        return this.boxLives;
    }

    public ImageView getBrickIV() {
        return this.box;
    }

    public void setBrickPos(double x, double y) {
        this.box.setX(x);
        this.box.setY(y);
    }

    public BOX copy() {
        return new BOX(this.WIDTH, this.HEIGHT, this.boxType, this.boxLives, this.havePowerup);
    }

    public boolean checkPower() {
        return this.havePowerup;
    }

    public void addPower() {
        this.havePowerup = true;
    }

}
