package UnitTests;

import Personel.Destination;
import Personel.Manager;
import Personel.Status;
import hibernate.DestinationHib;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateDestinationTest {
    LocalDate departuredate = LocalDate.of(2023,04,17);
    LocalDate arrivalDate = LocalDate.of(2023,04,18);
    LocalDate dateOfBirth = LocalDate.of(2023,04,13);
    Manager manager = new Manager("admin", "admin", "admin", dateOfBirth, "123456789", "admin", true);
    Destination destination = new Destination("Warsaw", "Vilnius", 500, departuredate, arrivalDate, Status.INPROGRESS, manager, null);

    @Mock
    private EntityManager entityManager;

    private DestinationHib destinationHib;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        destinationHib = new DestinationHib(entityManager);
    }

    @Test
    void testCreateDestinationNullEntityManager() {
        EntityManager entityManager = null;
        destinationHib = new DestinationHib(entityManager);

        assertThrows(IllegalStateException.class, () -> {
            destinationHib.updateDestination(destination);
        });
    }
}
