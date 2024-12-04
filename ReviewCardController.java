import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class ReviewCardController implements Initializable{

    @FXML
    private Label reviewerNameLabel, reviewerDescLabel;

    @FXML
    private CheckBox reviewerVerified;

    @FXML
    private Slider reviewerRating;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reviewerNameLabel.setText("");
        reviewerDescLabel.setText("");
        reviewerVerified.setSelected(false);
        reviewerRating.setValue(5);
    }

    public void addReview(Review review) {
        reviewerNameLabel.setText(review.getName());
        reviewerDescLabel.setText(review.getDescription());
        reviewerVerified.setSelected(review.didReviewerBuy());
        reviewerRating.setValue(review.getNumStars());
    }
    
}
