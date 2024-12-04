import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VendorCardController implements Initializable {

    @FXML
    private Label businessNameLabel, locationLabel;

    @FXML
    private Slider ratingBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        businessNameLabel.setText("");
        locationLabel.setText("");
        ratingBar.setValue(5);
    }

    @FXML
    public void openReviewsForVendor(MouseEvent me) {
        if(me.getButton() == MouseButton.PRIMARY && me.getClickCount() == 2) {
            System.out.println("Opening vendors");
            // open the vendors scene and pass along current vendor info

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/vendor_review_list.fxml"));
                VBox reviewWindow = loader.<VBox>load();

                Vendor currentVendor = new Vendor(businessNameLabel.getText(), locationLabel.getText(), ratingBar.getValue());
                ReviewsController controller = loader.getController();
                controller.setVendor(currentVendor);

                Stage reviewStage = new Stage();
                reviewStage.setTitle("Vendor Reviews");
                
                Scene reviewScene = new Scene(reviewWindow);
                reviewStage.setScene(reviewScene);
                reviewStage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addVendor(Vendor vendor) {
        businessNameLabel.setText(vendor.getBusinessName());
        locationLabel.setText(vendor.getLocation());
        ratingBar.setValue(vendor.getRating());
    }
    
}
