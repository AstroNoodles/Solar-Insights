import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class CostEstimatesController implements Initializable {

    public static List<String> bergenCountyZips = List.of("07010",
            "07020", "07022", "07024", "07026", "07031", "07057", "07070", "07071",
            "07072", "07073", "07074", "07075", "07401", "07407", "07410", "07417",
            "07423", "07430", "07432", "07436", "07446", "07450", "07451", "07452",
            "07458", "07463", "07481", "07495", "07601", "07602", "07603", "07604",
            "07605", "07606", "07607", "07608", "07620", "07621", "07624", "07626",
            "07627", "07628", "07630", "07631", "07632", "07640", "07641", "07642",
            "07643", "07644", "07645", "07646", "07647", "07648", "07649", "07650",
            "07652", "07653", "07656", "07657", "07660", "07661", "07662", "07663",
            "07666", "07670", "07675", "07676", "07677", "07699");

    public static List<String> hudsonCountyZips = List.of("07087", "07002", "07093", "07305",
            "07047", "07306", "07030", "07307", "07304", "07032", "07094", "07029", "07086", "07310", "07311", "07096",
            "07097", "07099", "07303", "07308", "07395", "07399");

    @FXML
    private Label countyNameLabel, savingsLabel, taxCreditsLabel, maintenanceLabel, installationCostLabel,
            calendarLabel, daylightHoursLabel;

    @FXML
    private ChoiceBox<String> zipcodeBox;

    @FXML
    private ChoiceBox<Integer> solarPanelBox, gasApplianceBox;

    private boolean bergenCountyZip = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set up Labels
        savingsLabel.setText("");
        taxCreditsLabel.setText("");
        maintenanceLabel.setText("");
        installationCostLabel.setText("");
        calendarLabel.setText("");
        daylightHoursLabel.setText("");

        List<Integer> appliancePanelListing = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        solarPanelBox.setItems(FXCollections.observableArrayList(appliancePanelListing));
        gasApplianceBox.setItems(FXCollections.observableArrayList(appliancePanelListing));
        
        List<String> concatZipCodes = Stream.concat(bergenCountyZips.stream(), hudsonCountyZips.stream()).collect(Collectors.toList());
        zipcodeBox.setItems(FXCollections.observableArrayList(concatZipCodes));
        zipcodeBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> val, String oldZip, String newZip) {
                
               if(bergenCountyZips.contains(newZip)){
                    countyNameLabel.setText("Bergen County");
                    countyNameLabel.setTextFill(Color.RED);
                    bergenCountyZip = true;
               } else {
                    countyNameLabel.setText("Hudson County");
                    countyNameLabel.setTextFill(Color.BLUE);
                    bergenCountyZip = false;
               }
            }
            
        });
    }

    @FXML
    private void openAboutUs(ActionEvent ae) {
        DashboardController.loadScene(SceneEnum.ABOUT_US);
    }

    @FXML
    private void queryDatabase(ActionEvent ae) {
        System.out.println("Querying the database");
        System.out.printf("Zipcode currently is Bergen County? %b\n", bergenCountyZip);
    }

}