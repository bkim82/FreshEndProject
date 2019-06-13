package Games.Breakout;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BOXES extends Breakout {
    public static final int width = 30;
    public static final int height = 20;
    private ArrayList<BOX> myBOX;
    private int typicalLives = 1;
    private int toughLives = 2;
    private int undetroyableLives = 1000;

    public ArrayList<BOX> createBOX(int WIDTH, int HEIGHT, int currentLV) {
        myBOX = new ArrayList<BOX>();
        create(myBOX, currentLV);
        return myBOX;
    }


    public void create(ArrayList<BOX> myBricks, int currentLV) {
        if (currentLV == 1) {
            createHorizontalBricks(myBricks, new regular_box(width, height, "typical", typicalLives, false), 8, 3, WIDTH / 4 - width / 1.5,
                    HEIGHT / 4 - height / 2);
            createBricks(myBricks, new harder_box(width, height, "tough", toughLives, false), 5, WIDTH / 6 - width / 2, HEIGHT / 4 - height / 2, 30, 30, 1);
            createBricks(myBricks, new harder_box(width, height, "tough", toughLives, false), 5, 5 * WIDTH / 6 - width / 2, HEIGHT / 4 - height / 2, 30, 30,
                    -1);
            createBricks(myBricks, new unlimited_box(width, height, "undestroyable", undetroyableLives, false), 3, WIDTH / 3 - width / 1.09, HEIGHT / 2 - height / 2,
                    80, 0, 1);
        } else if (currentLV == 2) {
            createHorizontalBricks(myBricks, new regular_box(width, height, "typical", typicalLives, false), 12, 2, WIDTH / 10 - width / 1.5,
                    HEIGHT / 4 - height / 2);
            createBricks(myBricks, new regular_box(width, height, "typical", typicalLives, false), 8, WIDTH / 6 - width / 2, HEIGHT / 4 - height / 2, 30, 30, 1);
            createBricks(myBricks, new harder_box(width, height, "tough", toughLives, false), 8, 5 * WIDTH / 6 - width / 2, HEIGHT / 4 - height / 2, 30, 30,
                    -1);
            createBricks(myBricks, new unlimited_box(width, height, "undestroyable", undetroyableLives, false), 2, WIDTH / 2.32 - width / 1.09, HEIGHT / 4 - height / 2,
                    80, 0, 1);
        } else if (currentLV == 3) {
            createHorizontalBricks(myBricks,new regular_box(width, height, "typical", typicalLives, false), 12, 4, WIDTH / 10 - width / 1.5,
                    HEIGHT / 4 - height / 2);
            createBricks(myBricks, new harder_box(width, height, "tough", toughLives, false), 10, WIDTH / 6 - width / 2, HEIGHT / 4 - height / 2, 30, 30, 1);
            createBricks(myBricks, new harder_box(width, height, "tough", toughLives, false), 10, 5 * WIDTH / 6 - width / 2, HEIGHT / 4 - height / 2, 30, 30,
                    -1);
            createBricks(myBricks, new unlimited_box(width, height, "undestroyable", undetroyableLives, false), 3, WIDTH / 3 - width / 1.09, HEIGHT / 4 - height / 2,
                    80, 0, 1);
        }
    }


    public void createBricks(ArrayList<BOX> myBOX, BOX original, int length, double width, double height,
                             int xGap, int yGap, int direction) {
        for (int i = 0; i < length; i++) {
            BOX b = original.clone();
            b.setBOXPos(width + direction * i * xGap, height + i * yGap);
            myBOX.add(b);
        }
    }

    /**
     *
     * @param myBricks
     * @param brickType
     * @param length
     * @param rows
     * @param width
     * @param height
     *
     */
    public void createHorizontalBricks(ArrayList<BOX> myBricks, BOX b, int length, int rows, double width,
                                       double height) {
        for (int r = 0; r < rows; r++) {
            height = height - 25;
            createBricks(myBricks, b, length, width, height, 30, 0, 1);
        }
    }


    /**
     * Remove a brick
     *
     * @param iter
     * @param brick
     */
    public void removeBOX(Iterator<BOX> iter, BOX brick) {
        brick.getBOXIV().setImage(null);
        iter.remove();
    }

    /**
     * Check if brick has been hit
     *
     * @param myBouncer
     * @param myMissiles
     * @return
     */
    public ArrayList<BOX> checkBOX(BALL myBouncer, ArrayList<ROCKET> myMissiles) {
        Iterator<BOX> iter = myBOX.iterator();
        while (iter.hasNext()) {
            BOX box = iter.next();
            if (box.getBOXIV().getBoundsInParent().intersects(myBouncer.getBouncerIV().getBoundsInParent())) {
                if (myBouncer.getBouncerIV().getBoundsInParent().getMinX() >= box.getBOXIV().getBoundsInParent()
                        .getMinX()
                        && myBouncer.getBouncerIV().getBoundsInParent().getMaxX() <= box.getBOXIV()
                        .getBoundsInParent().getMaxX()) {
                    myBouncer.changeX();
                }
                box.minuslife();
                if (box.checkBOXLives() <= 0) {
                    removeBOX(iter, box);
                } else {
                    myBouncer.changeY();
                }
            }
            for (ROCKET m : myMissiles) {
                if (box.getBOXIV().getBoundsInParent().intersects(m.getRocketIV().getBoundsInParent())) {
                    removeBOX(iter, box);
                }
            }
        }
        return myBOX;
    }
}
