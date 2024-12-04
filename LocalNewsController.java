import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class LocalNewsController implements Initializable {

    @FXML
    private ListView<NewsItem> newsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newsList.setCellFactory(cb -> new LocalNewsCard());

        String sampleImageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Photovoltaik_Dachanlage_" +
        "Hannover_-_Schwarze_Heide_-_1_MW.jpg/640px-Photovoltaik_Dachanlage_Hannover_-_Schwarze_Heide_-_1_MW.jpg";

        NewsItem sampleItem = new NewsItem(sampleImageURL, "Solar Energy is Green!", 
            "You should buy solar panels", "https://en.wikipedia.org/wiki/Solar_panel", true);
        
        newsList.setItems(FXCollections.observableArrayList(sampleItem));

    }

    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }
    
    // ------------------------------------------
    // BEGIN CARD CELL INTERFACE -- INNER CLASS
    // ------------------------------------------

    public class LocalNewsCard extends ListCell<NewsItem> {

        private HBox card;
        private LocalNewsCardController controller;
    
        public LocalNewsCard() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/news_card.fxml"));
                this.card = loader.<HBox>load();
                this.controller = loader.<LocalNewsCardController>getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        @Override
        protected void updateItem(NewsItem item, boolean empty) {
            super.updateItem(item, empty);
            if(empty || item == null) {
                setGraphic(null);
            } else {
                controller.addNewsItem(item);
                this.setGraphic(card);
                this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
        
    }


}
