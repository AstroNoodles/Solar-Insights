import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class VendorController implements Initializable{

    @FXML
    private ListView<Vendor> vendorPopulator;

    @FXML
    private ChoiceBox<String> vendorFilterBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        vendorFilterBox.setItems(FXCollections.observableArrayList("Rating - High To Low", "Rating - Low To High"));
        vendorPopulator.setCellFactory(cb -> new VendorCard());

        // JDBC query here to add all vendors into vendor cards
        // ensure that fx:controller is correct for all fxml to ensure fields get updated properly among passthrough
        Vendor sampleVendor = new Vendor("Industrial Holdings", "31 Second St, Pennsauken, NJ, 07016", 4);
        vendorPopulator.setItems(FXCollections.observableArrayList(sampleVendor));
    }

    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }
    
    // ------------------------------------------
    // BEGIN CARD CELL INTERFACE -- INNER CLASS
    // ------------------------------------------

    public class VendorCard extends ListCell<Vendor>{
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
            if(empty || item == null) {
                setGraphic(null);
            } else {
                controller.addVendor(item);
                this.setGraphic(card);
                this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }
    
}