import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
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

    private ObservableList<Vendor> targetVendors = FXCollections.observableArrayList();
    private ObservableList<Review> vendorReviews = FXCollections.observableArrayList();

    public void setVendor(Vendor currVendor) {
        targetVendors.add(currVendor);
        currentVendorList.setItems(targetVendors);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ratingFilterBox.setItems(
                FXCollections.observableArrayList("Ratings - Highest To Lowest", "Ratings - Lowest To Highest"));

        ratingFilterBox.getSelectionModel().selectedItemProperty()
                .addListener((value, oldFilter, newFilter) -> updateRatings(oldFilter, newFilter));

        currentVendorList.setCellFactory(cb -> new VendorController().new VendorCard());

        vendorReviewList.setCellFactory(cb -> new VendorReviewCard());

        vendorReviewList.setItems(vendorReviews);
    }

    @FXML
    private void seeReviews(ActionEvent ae) {
        String vendorName = targetVendors.get(0).getBusinessName();
        System.out.println(vendorName);
        try (VendorQueries vq = new VendorQueries("secrets.properties")) {
            System.out.println("cherry");

            List<Review> vendorSQLReviews = vq.getVendorReviews(vendorName);
            if (vendorSQLReviews.isEmpty()) {
                System.out.println(
                        "List should be full for vendors: Solar Works NJ, A Clear Alternative, Concord Engineering, Bovio Rubino Service");
                System.out.println("Else, this should be empty");
                Review defaultReview = new Review("No Response Found", "", 0, false);
                vendorReviews.add(defaultReview);
            } else {
                vendorReviews.addAll(vendorSQLReviews);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRatings(String oldFilter, String newFilter) {
        if (vendorReviews.isEmpty()) {
            System.out.println("The review list is empty. Press the button first...");
            return;
        }

        switch (newFilter) {
            case "Ratings - Highest To Lowest":
                FXCollections.sort(vendorReviews, Comparator.reverseOrder());
                break;
            case "Ratings - Lowest To Highest":
                FXCollections.sort(vendorReviews, Comparator.naturalOrder());
                break;
        }
    }

    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }

    // ------------------------------------------
    // BEGIN CARD CELL INTERFACE -- INNER CLASS
    // ------------------------------------------

    public class VendorReviewCard extends ListCell<Review> {
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
            if (empty || review == null) {
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
