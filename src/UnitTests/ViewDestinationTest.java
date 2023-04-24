package UnitTests;

import Personel.Destination;
import Personel.Manager;
import Personel.Status;
import hibernate.DestinationHib;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewDestinationTest {
    @Test
    public void testGetDataByFilter() {
        // Create an invalid destination with arrival after departure
        LocalDate departuredate = LocalDate.of(2023, 04, 29);
        LocalDate arrivalDate = LocalDate.of(2023, 04, 18);
        LocalDate dateOfBirth = LocalDate.of(2023, 04, 13);
        Manager manager = new Manager("admin", "admin", "admin", dateOfBirth, "123456789", "admin", true);
        Destination destination = new Destination("Warsaw", "Vilnius", 500, departuredate, arrivalDate, Status.INPROGRESS, manager, null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Kursinis");
        EntityManager entityManager = emf.createEntityManager();

        DestinationHib destinationHib = new DestinationHib(entityManager);
        // Add the invalid destination to the database
        entityManager = destinationHib.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(destination);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

        DestinationHib destinationHibs = new DestinationHib(entityManager);
        List<Destination> destinations = destinationHibs.getDataByFilter(Status.AVAILABLE, LocalDate.now(), LocalDate.now().plusDays(7));

        // Assert that the result is empty
        assertTrue(destinations.isEmpty());
    }
}
