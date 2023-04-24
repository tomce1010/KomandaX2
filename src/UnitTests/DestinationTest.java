package UnitTests;

import static org.junit.jupiter.api.Assertions.*;

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

class DestinationTest {
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
    void testDeleteDestinationNullEntityManager() {
        EntityManager entityManager = null;
        destinationHib = new DestinationHib(entityManager);

        assertThrows(IllegalStateException.class, () -> {
            destinationHib.deleteDestination(destination);
        });
    }

}
