package fxControllers;

import Personel.Manager;
import Personel.Trucker;
import hibernate.ManagerHib;
import hibernate.TruckerHib;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.query.criteria.internal.expression.function.UpperFunction;
import utils.FxUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPage implements Initializable {
    @FXML
    public Button registerButton;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField lastnameField;
    @FXML
    public TextField phoneField;
    @FXML
    public DatePicker birthField;
    @FXML
    public TextField loginField;
    @FXML
    public CheckBox adminField;
    @FXML
    public CheckBox medicalCheck;
    @FXML
    public CheckBox managerCheck;
    @FXML
    public TextField distanceField;
    @FXML
    public CheckBox licenceCheck;
    @FXML
    public TextField DriversLicense;
    @FXML
    public TextField MedicalCard;
    private ManagerHib managerHib;
    private TruckerHib truckerHib;
    private EntityManagerFactory entityManagerFactory;

    public void setData(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.managerHib = new ManagerHib(entityManagerFactory);
        this.truckerHib = new TruckerHib(entityManagerFactory);
    }
    public void disableFields() {
        if(managerCheck.isSelected() == false){
            medicalCheck.setDisable(false);
            licenceCheck.setDisable(false);
            distanceField.setDisable(false);
            DriversLicense.setDisable(false);
            MedicalCard.setDisable(false);
            adminField.setDisable(true);
        } else {
            medicalCheck.setDisable(true);
            licenceCheck.setDisable(true);
            distanceField.setDisable(true);
            DriversLicense.setDisable(true);
            MedicalCard.setDisable(true);
            adminField.setDisable(false);
        }
    }
    public void Register() throws IOException {

        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty() || nameField.getText().isEmpty() || lastnameField.getText().isEmpty() || phoneField.getText().isEmpty()){
            FxUtils.generateAlert(Alert.AlertType.INFORMATION, "One or more fields are empty", "User Registration Data");
        }
        if(phoneField.getText().length() >= 15)
        {
            FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Phone number cannot exceed 15 digits", phoneField.getText());
        }
        if(passwordField.getText().length() <= 10)
        {
            FxUtils.generateAlert(Alert.AlertType.INFORMATION, "Password must be longer than 10 characters", passwordField.getText());
        }
        else {
            if (!managerCheck.isSelected()) {
                Trucker trucker = null;
                trucker = new Trucker(DriversLicense.getText(), MedicalCard.getText(), passwordField.getText(), nameField.getText(), lastnameField.getText(), birthField.getValue(), phoneField.getText(), loginField.getText(), medicalCheck.isSelected(), licenceCheck.isSelected(), Integer.valueOf(distanceField.getText()));
                truckerHib.createTrucker(trucker);
            } else {
                Manager manager = null;
                manager = new Manager(passwordField.getText(), nameField.getText(), lastnameField.getText(), birthField.getValue(), phoneField.getText(), loginField.getText(), adminField.isSelected());
                managerHib.createManager(manager);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../fxml/login-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) passwordField.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableFields();
    }
}
