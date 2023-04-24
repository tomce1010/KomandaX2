package fxControllers;

import Personel.*;
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

public class CreateDestination implements Initializable {
    public Button createButton;
    public TextField startField;
    public TextField destinationField;
    public TextField distanceField;
    public DatePicker arrivalDate;
    public DatePicker departureDate;
    public ChoiceBox statusField;
    public TextField truckdetails;
    private DestinationHib destinationHib;
    private EntityManagerFactory entityManagerFactory;
    public User user;

    public void setData(EntityManagerFactory entityManagerFactory, User user) {
        this.entityManagerFactory = entityManagerFactory;
        this.destinationHib = new DestinationHib(entityManagerFactory);
        this.user = user;

    }

    public void createDestination() throws IOException {
        Destination destination = null;
        destination = new Destination(startField.getText(), destinationField.getText(), Integer.parseInt(distanceField.getText()), departureDate.getValue(), arrivalDate.getValue(), Status.valueOf(statusField.getSelectionModel().getSelectedItem().toString()), (Manager) user, truckdetails.getText());
        destinationHib.createDestination(destination);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusField.getItems().add(Status.AVAILABLE.toString());
        statusField.getItems().add(Status.INPROGRESS.toString());
        statusField.getItems().add(Status.FINISHED.toString());
    }
}
