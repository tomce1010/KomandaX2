package fxControllers;

import Personel.Manager;
import Personel.Trucker;
import hibernate.ManagerHib;
import hibernate.TruckerHib;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.FxUtils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginPage {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    public CheckBox managerCheck;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Kursinis");
    ManagerHib managerHib = new ManagerHib(entityManagerFactory);
    TruckerHib truckerHib = new TruckerHib(entityManagerFactory);


    public void login() throws IOException, InterruptedException {
        if (managerCheck.isSelected()) {
            Manager manager = managerHib.getManagerByLoginData(loginField.getText(), passwordField.getText());
            if (manager != null) {
                long startTime = System.currentTimeMillis();
                long endTime = startTime + 10000;
                while (System.currentTimeMillis() < endTime) {
                    //funkcija
                    if (endTime<System.currentTimeMillis()) {
                        FxUtils.generateAlert(Alert.AlertType.INFORMATION, "User login report", "Login Timeout");
                    }
                }
                FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../fxml/main-page.fxml"));
                Parent parent = fxmlLoader.load();
                MainPage mainPage = fxmlLoader.getController();
                mainPage.setDataManager(entityManagerFactory, manager, manager);
                Scene scene = new Scene(parent);
                Stage stage = (Stage) passwordField.getScene().getWindow();
                stage.setTitle("Main page");
                stage.setScene(scene);
                stage.show();
            } else {
                FxUtils.generateAlert(Alert.AlertType.INFORMATION, "User login report", "No such user or wrong credentials");
            }
        } else {
            Trucker trucker = truckerHib.getTruckerByLoginData(loginField.getText(), passwordField.getText());
            if (trucker != null) {
                long startTime = System.currentTimeMillis();
                long endTime = startTime + 10000;
                while (System.currentTimeMillis() < endTime) {
                    //funkcija
                    if (endTime<System.currentTimeMillis()) {

                        FxUtils.generateAlert(Alert.AlertType.INFORMATION, "User login report", "Login Timeout");
                    }
                }
                FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../fxml/main-page.fxml"));
                Parent parent = fxmlLoader.load();
                MainPage mainPage = fxmlLoader.getController();
                mainPage.setDataTrucker(entityManagerFactory, trucker, trucker);

                Scene scene = new Scene(parent);
                Stage stage = (Stage) passwordField.getScene().getWindow();
                stage.setTitle("Main page");
                stage.setScene(scene);
                stage.show();
            } else {
                FxUtils.generateAlert(Alert.AlertType.INFORMATION, "User login report", "No such user or wrong credentials");
            }
        }
    }

    public void Register() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../fxml/register-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
        RegisterPage registerPage = fxmlLoader.getController();
        registerPage.setData(entityManagerFactory);
    }
}
