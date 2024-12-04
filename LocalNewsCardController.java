import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    private Button favoriteButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headlineLabel.setText("");
        primaryTextLabel.setText("");
        sourceLabel.setText("");
    }

    @FXML
    private void addAsFavorite(ActionEvent ae) {
        if(favoriteButton.getStyle().contains("yellow")) {
            favoriteButton.setStyle("-fx-background-color: ivory");
        } else {
            favoriteButton.setStyle("-fx-background-color: yellow");
        }
    }

    @FXML
    private void openSite(ActionEvent ae) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/news_viewer.fxml"));
            VBox newsViewer = loader.<VBox>load();

            LocalNewsWebViewer webController = new LocalNewsWebViewer(source);
            loader.setController(webController);

            Stage webStage = new Stage();
            webStage.setTitle("Local News Reader");
            
            Scene primaryScene = new Scene(newsViewer);
            webStage.setScene(primaryScene);
            webStage.show();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewsItem(NewsItem item) {
        cardImage.setImage(new Image(item.getImageURL()));
        headlineLabel.setText(item.getHeadline());
        primaryTextLabel.setText(item.getPrimaryText());
        sourceLabel.setText(String.format("From %s", item.getSourceURL()));
        source = item.getSourceURL();
    }
    
}
