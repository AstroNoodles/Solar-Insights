import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Make sure you point to the correct path of where JavaFX and JDBC are installed 

// Command on Windows
// javac --module-path Libraries/javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.web *.java
// java --module-path Libraries/javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml,javafx.web Dashboard.java

// Command on Mac:
// javac --module-path /Library/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml,javafx.web *.java
// java --module-path /Library/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml,javafx.web Dashboard.java

public class Dashboard extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox dashboardBox = FXMLLoader.<VBox>load(getClass().getResource("/views/dashboard.fxml"));
        Scene scene = new Scene(dashboardBox);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}