import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
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

public class VendorController implements Initializable {

    @FXML
    private ListView<Vendor> vendorPopulator;

    @FXML
    private ChoiceBox<String> vendorFilterBox;

    private ObservableList<Vendor> vendorList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vendorFilterBox.setItems(FXCollections.observableArrayList("Rating - High To Low", "Rating - Low To High"));

        vendorFilterBox.getSelectionModel().selectedItemProperty().addListener((val, oldFilter, newFilter) -> {
            reloadVendorList(oldFilter, newFilter);
        });

        vendorPopulator.setCellFactory(cb -> new VendorCard());

        // Vendor sampleVendor = new Vendor("Industrial Holdings", "31 Second St,
        // Pennsauken, NJ, 07016", 4);
        // vendorList = FXCollections.observableArrayList(sampleVendor);

        populateVendorList();
        vendorPopulator.setItems(vendorList);
    }

    public void populateVendorList() {
        vendorList.clear();
        try (VendorQueries vq = new VendorQueries(VendorQueries.SECRETS_FILE)) {
            HashMap<String, Vendor> allVendors = vq.getAllVendors();
            HashMap<String, Double> vendorAverageRatings = vq.getAvgRatings();

            for (String byVendorName : allVendors.keySet()) {
                String vendorName = byVendorName;
                String vendorAddress = allVendors.get(byVendorName).getLocation();
                String vendorImageSrc = allVendors.get(byVendorName).getImageSrc();
                double averageRating = vendorAverageRatings.getOrDefault(byVendorName, 0.0);
                Vendor vendor = new Vendor(vendorName, vendorAddress, vendorImageSrc, averageRating);
                vendorList.add(vendor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void reloadVendorList(String oldFilter, String newFilter) {
        switch (newFilter) {
            case "Rating - High To Low":
                FXCollections.sort(vendorList, Comparator.reverseOrder());
                break;
            case "Rating - Low To High":
                FXCollections.sort(vendorList, Comparator.naturalOrder());
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

    public class VendorCard extends ListCell<Vendor> {
        private VBox card;
        private VendorCardController controller;

        public VendorCard() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/vendor_card.fxml"));
                this.card = loader.<VBox>load();
                this.controller = loader.<VendorCardController>getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void updateItem(Vendor item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                controller.addVendor(item);
                this.setGraphic(card);
                this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

}
