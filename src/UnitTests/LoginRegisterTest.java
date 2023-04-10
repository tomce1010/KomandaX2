package UnitTests;

import fxControllers.LoginPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginRegisterTest {
    @Test
    public void testRegister() throws IOException {
        LoginPage loginPage = new LoginPage();
        assertThrows(ExceptionInInitializerError.class, () -> loginPage.Register());
    }
}
