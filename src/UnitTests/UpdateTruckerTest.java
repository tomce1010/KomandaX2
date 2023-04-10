package UnitTests;

import Personel.Trucker;
import hibernate.TruckerHib;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateTruckerTest {

    LocalDate dateOfBirth = LocalDate.of(2023,01,05);
    Trucker trucker = new Trucker("123", "123", "t", "t", "t", dateOfBirth, "123456789", "t", true, true, 123);


    @Mock
    private EntityManagerFactory entityManagerFactory;

    private TruckerHib truckerHib;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        truckerHib = new TruckerHib(null);
    }

    @Test
    void testUpdateTruckerNullEntityManager() {
        truckerHib = new TruckerHib(null);

        assertThrows(IllegalStateException.class, () -> {
            truckerHib.updateTrucker(trucker);
        });
    }
}
