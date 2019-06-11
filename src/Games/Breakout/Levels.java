package Games.Breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Levels {
    public static final String TITLE = "Breakout Game";
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final Paint BACKGROUND = Color.WHITE;
    public static final double GROWTH_RATE = 1.1;
    private int maxLevel = 3;
    private int levelID;

    public Levels(int level) {
        levelID = level;
    }

    public int currentLevel() {
        return this.levelID;
    }


    public void nextLevel(Stage s, int speed, Text winningText, Timeline animation) {
        levelID++;
        speed = (int) (speed * GROWTH_RATE);
        if (levelID > maxLevel) {
            animation.stop();
            winningText.setVisible(true);
            return;
        }
        Breakout nextBG = new Breakout();
        Scene scene = nextBG.setupGame(WIDTH, HEIGHT, BACKGROUND, s, levelID, speed);
        s.setScene(scene);
        s.setTitle(TITLE);
        s.show();
        nextBG.setAnimation(s, levelID);
    }

}

