import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class VendorController implements Initializable{

    @FXML
    private ListView<Vendor> vendorPopulator;

    @FXML
    private ChoiceBox<String> vendorFilterBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        vendorFilterBox.setItems(FXCollections.observableArrayList("Hudson County", "Bergen County", "Number of Ratings"));
        vendorPopulator.setCellFactory(_ -> new VendorCardCell());
    }

    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }
    
}
