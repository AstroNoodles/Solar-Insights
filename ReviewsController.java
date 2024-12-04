import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.VBox;

public class ReviewsController implements Initializable {

    @FXML
    private ListView<Vendor> currentVendorList;

    @FXML
    private ChoiceBox<String> ratingFilterBox;

    @FXML
    private ListView<Review> vendorReviewList;

    private ObservableList<Review> vendorReviews;

    public void setVendor(Vendor currVendor) {
        currentVendorList.setItems(FXCollections.observableArrayList(currVendor));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        
        ratingFilterBox.setItems(FXCollections.observableArrayList("Ratings - Highest To Lowest", "Ratings - Lowest To Highest"));
        
        ratingFilterBox.getSelectionModel().selectedItemProperty().addListener((value, oldFilter, newFilter) -> updateRatings(oldFilter, newFilter));

        currentVendorList.setCellFactory(cb -> new VendorController().new VendorCard());

        vendorReviewList.setCellFactory(cb -> new VendorReviewCard());
        
        Review sampleReview = new Review("Joe Flood", "very good company!", 4, true);
        vendorReviews = FXCollections.observableArrayList(sampleReview);
        vendorReviewList.setItems(vendorReviews);

        // JDBC Query here to add all reviews into review cards
    }

    private void updateRatings(String oldFilter, String newFilter) {
        System.out.println("Updating ratings from JDBC");
    }

    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }
    
    // ------------------------------------------
    // BEGIN CARD CELL INTERFACE -- INNER CLASS
    // ------------------------------------------

    public class VendorReviewCard extends ListCell<Review>{
        private VBox card;
        private ReviewCardController controller;
    
        public VendorReviewCard() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/review_card.fxml"));
                this.card = loader.<VBox>load();
                this.controller = loader.<ReviewCardController>getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        @Override
        protected void updateItem(Review review, boolean empty) {
            super.updateItem(review, empty);
            if(empty || review == null) {
                setText(null);
                setGraphic(null);
            } else {
                controller.addReview(review);
                this.setGraphic(card);
                this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }
    
}
