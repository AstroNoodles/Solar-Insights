import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class LocalNewsWebViewer implements Initializable {

    private String urlToLoad = "about:blank";

    @FXML
    private WebView webInterface;

    public LocalNewsWebViewer(String urlToLoad) {
        if(urlToLoad != "") {
            System.out.printf("Loading %s\n", urlToLoad);
            this.urlToLoad = urlToLoad;
        } else {
            System.out.println("Loading about:blank, double check that the source was sent correctly!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webInterface.getEngine().load(urlToLoad);
    }

    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }
    
}
