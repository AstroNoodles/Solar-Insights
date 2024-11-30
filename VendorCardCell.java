import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class VendorCardCell extends ListCell<Vendor>{
    private VBox card;
    private VendorCardController controller;

    public VendorCardCell() {
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
