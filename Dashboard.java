import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// /Library/javafx-sdk-11.0.2/lib
// javac --module-path /Library/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml *.java
// java --module-path /Library/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml Dashboard.java

public class Dashboard extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox dashboardBox = FXMLLoader.<VBox>load(getClass().getResource("/views/dashboard.fxml"));
        Scene scene = new Scene(dashboardBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}