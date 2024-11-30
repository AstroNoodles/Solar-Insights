import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController {

    public static void loadScene(SceneEnum whichScene){
        try {
            VBox loadedScene = FXMLLoader.<VBox>load(DashboardController.class.getResource(whichScene.getResourceName()));
            Stage newStage = new Stage();
            Scene scene = new Scene(loadedScene);
            newStage.setScene(scene);
            newStage.setTitle(whichScene.getSceneName());
            newStage.show();
        } catch(IOException e) {
            System.err.printf("Problem occured loading resource %s\n", whichScene.getResourceName());
        }
    }
    
    @FXML
    private void openAboutUs(ActionEvent ae) {
        loadScene(SceneEnum.ABOUT_US);
    }

    @FXML
    private void openVendors(ActionEvent ae){
        loadScene(SceneEnum.VENDORS);
    }

    @FXML
    private void openLocalNews(ActionEvent ae) {
        loadScene(SceneEnum.LOCAL_NEWS);
    }

    @FXML
    private void openCostEstimates(ActionEvent ae) {
        loadScene(SceneEnum.COST_ESTIMATES);
    }

}
