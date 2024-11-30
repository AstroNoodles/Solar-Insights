import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class VendorCardController implements Initializable {

    @FXML
    private Label businessNameLabel, locationLabel, reviewsLabel;

    @FXML
    private Button ratingButton1, ratingButton2, ratingButton3, ratingButton4, ratingButton5;

    @FXML
    private FlowPane ratingBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        businessNameLabel.setText("");
        locationLabel.setText("");
        reviewsLabel.setText("");

        ratingBar.getChildren().forEach((buttonNode) -> {
            buttonNode.setStyle("-fx-background-color: yellow;");
        });
    }

    @FXML
    private void updateRating(ActionEvent ae) {
        Button ratingButton = (Button) ae.getSource();
        if(ratingButton.getStyle().contains("yellow")) {
            ratingButton.setStyle("-fx-background-color: ivory");
        } else {
            ratingButton.setStyle("-fx-background-color: yellow");
        }
    }

    public void addVendor(Vendor vendor) {
        // what happens to UI when new vendor gets added
    }
    
}
