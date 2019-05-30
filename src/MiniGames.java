import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MiniGames extends Application {

    public void start (Stage ps) {

        BorderPane bp = new BorderPane();
        Pane p = new Pane();
        GridPane gp = new GridPane();

        Label text = new Label("Mini Games!");
        text.setFont(Font.font("Times Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        p.getChildren().add(text);

        gp.add(new ImageView(new Image("image/og_image.jpg")), 0, 0);

        bp.setTop(p);
        bp.setCenter(gp);


        Scene scene = new Scene(bp,500,500);
        ps.setScene(scene);
        ps.show();
        bp.requestFocus();
    }
}

