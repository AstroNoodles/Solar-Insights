import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class LocalNewsController implements Initializable {

    @FXML
    private ListView<NewsItem> newsList;

    @FXML
    private ChoiceBox<String> filterBox;

    private ObservableList<NewsItem> newsItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // By Topic is default behavior
        filterBox.setItems(FXCollections.observableArrayList("Intro", "Repairs", "Finances"));

        filterBox.getSelectionModel().selectedItemProperty().addListener((val, oldFilter, newFilter) -> {
            updateNewsList(oldFilter, newFilter);
        });

        newsList.setCellFactory(cb -> new LocalNewsCard());

        populateNewsList();

        newsList.setItems(newsItems);
    }

    public void populateNewsList() {
        try (VendorQueries vq = new VendorQueries("secrets.properties")) {
            List<NewsItem> newsQueryItems = vq.getResources();
            newsItems.addAll(newsQueryItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateNewsList(String oldFilter, String newFilter) {
        System.out.println("Updating the news list from new filter");
        String categoryFilter = newFilter.toLowerCase();

        List<NewsItem> filteredItems = newsItems.stream().filter((item) -> {
            String cat = item.getCategory();
            return cat.equals(categoryFilter);
        }).collect(Collectors.toList());
        System.out.println(filteredItems);

        newsList.setItems(FXCollections.observableArrayList(filteredItems));
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
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                controller.addNewsItem(item);
                this.setGraphic(card);
                this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }

    }

}
