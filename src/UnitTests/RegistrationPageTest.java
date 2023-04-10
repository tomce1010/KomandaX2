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
        CheckBox managerCheck = new CheckBox();
        managerCheck.setSelected(false);
        CheckBox medicalCheck = new CheckBox();
        medicalCheck.setSelected(true);
        CheckBox licenceCheck = new CheckBox();
        licenceCheck.setSelected(true);
        TextField distanceField = new TextField("10");
        TextField driversLicense = new TextField("12345");
        TextField medicalCard = new TextField("67890");
        CheckBox adminField = new CheckBox();
        adminField.setSelected(true);

        // Act
        RegisterPage.disableFieldsForUser(managerCheck, medicalCheck, licenceCheck, distanceField, driversLicense, medicalCard, adminField);

        // Assert
        assertFalse(medicalCheck.isDisabled());
        assertFalse(licenceCheck.isDisabled());
        assertFalse(distanceField.isDisabled());
        assertFalse(driversLicense.isDisabled());
        assertFalse(medicalCard.isDisabled());
        assertTrue(adminField.isDisabled());
    }

    @Test
    void testManagerSelected() {
        // Arrange
        CheckBox managerCheck = new CheckBox();
        managerCheck.setSelected(true);
        CheckBox medicalCheck = new CheckBox();
        medicalCheck.setSelected(true);
        CheckBox licenceCheck = new CheckBox();
        licenceCheck.setSelected(true);
        TextField distanceField = new TextField("10");
        TextField driversLicense = new TextField("12345");
        TextField medicalCard = new TextField("67890");
        CheckBox adminField = new CheckBox();
        adminField.setSelected(true);

        // Act
        RegisterPage.disableFieldsForUser(managerCheck, medicalCheck, licenceCheck, distanceField, driversLicense, medicalCard, adminField);

        // Assert
        assertTrue(medicalCheck.isDisabled());
        assertTrue(licenceCheck.isDisabled());
        assertTrue(distanceField.isDisabled());
        assertTrue(driversLicense.isDisabled());
        assertTrue(medicalCard.isDisabled());
        assertFalse(adminField.isDisabled());
    }
}
