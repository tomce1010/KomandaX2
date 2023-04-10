package UnitTests;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import fxControllers.RegisterPage;

class TestDisableFieldsForUser {

    @Test
    void testManagerNotSelected() {
        // Arrange
        String managerSelected = "false";
        CheckBox managerCheck = new CheckBox(managerSelected);
        CheckBox medicalCheck = new CheckBox("true");
        CheckBox licenceCheck = new CheckBox("true");
        TextField distanceField = new TextField("10");
        TextField DriversLicense = new TextField("12345");
        TextField MedicalCard = new TextField("67890");
        CheckBox adminField = new CheckBox("true");

        // Act
        RegisterPage.disableFieldsForUser(managerCheck, medicalCheck, licenceCheck, distanceField, DriversLicense, MedicalCard, adminField);

        // Assert
        assertFalse(medicalCheck.isDisabled());
        assertFalse(licenceCheck.isDisabled());
        assertFalse(distanceField.isDisabled());
        assertFalse(DriversLicense.isDisabled());
        assertFalse(MedicalCard.isDisabled());
        assertTrue(adminField.isDisabled());
    }

    @Test
    void testManagerSelected() {
        // Arrange
        String managerSelected = "true";
        CheckBox managerCheck = new CheckBox(managerSelected);
        CheckBox medicalCheck = new CheckBox("true");
        CheckBox licenceCheck = new CheckBox("true");
        TextField distanceField = new TextField("10");
        TextField DriversLicense = new TextField("12345");
        TextField MedicalCard = new TextField("67890");
        CheckBox adminField = new CheckBox("true");

        // Act
        RegisterPage.disableFieldsForUser(managerCheck, medicalCheck, licenceCheck, distanceField, DriversLicense, MedicalCard, adminField);

        // Assert
        assertTrue(medicalCheck.isDisabled());
        assertTrue(licenceCheck.isDisabled());
        assertTrue(distanceField.isDisabled());
        assertTrue(DriversLicense.isDisabled());
        assertTrue(MedicalCard.isDisabled());
        assertFalse(adminField.isDisabled());
    }
}