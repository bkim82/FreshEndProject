import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MiniGames extends Application {

    public void start (Stage ps) {

        BorderPane bp = new BorderPane();
        Pane p = new Pane();
        HBox fp = new HBox(10);
        HBox fp2 = new HBox(10);
        fp.setAlignment(Pos.CENTER);

        Label text = new Label("Mini Games!");
        text.setFont(Font.font("Times Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
        p.getChildren().add(text);

        Image image = new Image("image/free-minesweeper-thumbnail.png");
        ImageView view = new ImageView(image);
        view.fitHeightProperty().bind(fp.heightProperty().divide(3));
        view.fitWidthProperty().bind(fp.widthProperty().divide(3));

        Image image2 = new Image("image/google-birthday-surprise-spinner.jpg");
        ImageView view2 = new ImageView(image2);
        view2.fitHeightProperty().bind(fp.heightProperty().divide(3));
        view2.fitWidthProperty().bind(fp.widthProperty().divide(3));

        Image image3 = new Image("image/simon.jpg");
        ImageView view3 = new ImageView(image3);
        view3.fitHeightProperty().bind(fp2.heightProperty().divide(3));
        view3.fitWidthProperty().bind(fp2.widthProperty().divide(3));



        fp.getChildren().addAll(view, view2);
        fp2.getChildren().addAll(view3);


        bp.setTop(p);

        bp.setCenter(fp);

        bp.setBottom(fp2);


        Scene scene = new Scene(bp,700,500);
        ps.setScene(scene);
        ps.show();
        bp.requestFocus();
    }
}

