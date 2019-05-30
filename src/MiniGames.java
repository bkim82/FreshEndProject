import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MiniGames extends Application {

    public void start (Stage ps) {

        BorderPane bp = new BorderPane();
        Pane p = new Pane();

        Label a = new Label("Mini Games!");
        p.getChildren().add(a);
        bp.setTop(p);


        Scene scene = new Scene(p);
        ps.setScene(scene);
        ps.show();
    }
}