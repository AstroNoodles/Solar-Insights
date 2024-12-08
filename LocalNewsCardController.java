import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LocalNewsCardController implements Initializable {

    @FXML
    private ImageView cardImage;

    private String source;

    @FXML
    private Label headlineLabel, primaryTextLabel, sourceLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headlineLabel.setText("");
        primaryTextLabel.setText("");
        sourceLabel.setText("");
    }

    @FXML
    private void openSite(ActionEvent ae) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/news_viewer.fxml"));
            VBox newsViewer = loader.<VBox>load();

            LocalNewsWebViewer webController = loader.getController();
            webController.setURLToLoad(source);

            Stage webStage = new Stage();
            webStage.setTitle("Local News Reader");

            Scene primaryScene = new Scene(newsViewer);
            webStage.setScene(primaryScene);
            webStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewsItem(NewsItem item) {
        cardImage.setImage(new Image(item.getImageURL()));
        headlineLabel.setText(item.getHeadline());
        primaryTextLabel.setText(item.getPrimaryText());
        sourceLabel.setText(item.getSourceSite());
        source = item.getSourceURL();
    }

}
