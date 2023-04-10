package UnitTests;

import fxControllers.LoginPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    LoginPage loginPage = new LoginPage();
    @Test
    public void testLogin() throws IOException, InterruptedException {
        // Arrange
        loginPage.managerCheck.setSelected(true);
        loginPage.loginField.setText("admin");
        loginPage.passwordField.setText("admin");
        // Act
        boolean result = loginPage.login();
        // Assert
        assertTrue(result);
    }
}
