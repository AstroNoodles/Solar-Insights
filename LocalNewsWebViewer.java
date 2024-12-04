import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class LocalNewsWebViewer {

    @FXML
    private WebView webInterface;


    public void setURLToLoad(String urlToLoad) {
        if(urlToLoad != "") {
            System.out.printf("Loading %s\n", urlToLoad);
            webInterface.getEngine().load(urlToLoad);
        } else {
            System.out.println("Loading about:blank, double check that the source was sent correctly!");
        }
    }


    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }
    
}
