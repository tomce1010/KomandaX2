package UnitTests;

import Personel.Trucker;
import hibernate.TruckerHib;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateTruckerTest {

    @Mock
    private TruckerHib truckerHib;

    @Test
    public void testCreateTrucker() throws IOException {
        // Arrange
        String medicalCard = "1234";
        String driversLicense = "5678";
        String password = "password";
        String name = "John";
        String lastName = "Doe";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        String phone = "555-1234";
        String login = "johndoe";
        boolean medicalField = true;
        boolean driverField = true;
        int distance = 100;

        Trucker expectedTrucker = new Trucker(medicalCard, driversLicense, password, name, lastName, birthDate, phone, login, medicalField, driverField, distance);
        doNothing().when(truckerHib).createTrucker(expectedTrucker);

        // Act
        truckerHib.createTrucker(expectedTrucker);

        // Assert
        verify(truckerHib, times(1)).createTrucker(expectedTrucker);
    }
}