package fxControllers;

import Personel.Destination;
import Personel.Status;
import Personel.Trucker;
import hibernate.DestinationHib;
import hibernate.TruckerHib;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateDestination implements Initializable{
    public Button updateButton;
    public TextField startField;
    public TextField destinationField;
    public TextField distanceField;
    public DatePicker arrivalDate;
    public DatePicker departureDate;
    private DestinationHib destinationHib;
    private Destination selectedDestination;
    public ChoiceBox statusField;
    private EntityManagerFactory entityManagerFactory;

    public void setData(EntityManagerFactory entityManagerFactory, Destination selectedDestination) {
        this.entityManagerFactory = entityManagerFactory;
        this.selectedDestination = selectedDestination;
        this.destinationHib = new DestinationHib(entityManagerFactory);
        fillFields();
    }

    private void fillFields() {
        Destination destination = (Destination) destinationHib.getDestinationById(selectedDestination.getId());
        startField.setText(destination.getStart());
        destinationField.setText(destination.getDestination());
        distanceField.setText(String.valueOf(destination.getDistance()));
        departureDate.setValue(destination.getDepartureDate());
        arrivalDate.setValue(destination.getArrivalDate());

    }

    public void updateDestination() throws IOException {
        Destination destination = (Destination) destinationHib.getDestinationById(selectedDestination.getId());
        destination.setStart(startField.getText());
        destination.setDestination(destinationField.getText());
        destination.setDistance(Integer.parseInt(distanceField.getText()));
        destination.setDepartureDate(departureDate.getValue());
        destination.setArrivalDate(arrivalDate.getValue());
        destination.setStatus(Status.valueOf(statusField.getSelectionModel().getSelectedItem().toString()));
        destinationHib.updateDestination(destination);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusField.getItems().add(Status.AVAILABLE.toString());
        statusField.getItems().add(Status.INPROGRESS.toString());
        statusField.getItems().add(Status.FINISHED.toString());
    }

}
