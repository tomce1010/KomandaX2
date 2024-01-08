package Test;

import fxControllers.CreateTruck;
import hibernate.TruckHib;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

import static org.mockito.Mockito.*;

class CreateTruckTest {

    @InjectMocks
    private CreateTruck createTruck;

    @Mock
    private TruckHib mockTruckHib;

    @BeforeEach
    void setUp() {
        new JFXPanel();
        MockitoAnnotations.openMocks(this);
        createTruck = new CreateTruck();
        createTruck.setData(mock(EntityManagerFactory.class));
        createTruck.numPlateField = new TextField();
        createTruck.makeField = new TextField();
        createTruck.modelField = new TextField();
        createTruck.yearField = new TextField();
        createTruck.transitField = new CheckBox();
        createTruck.locationField = new TextField();
        createTruck.truckHib = mockTruckHib;
    }

    @Test
    void testCreateTruckWithOneInput() throws IOException {
        setTestData("ABC123", "Volvo", "VNL", "2022", true, "Location1");
        long startTime = System.currentTimeMillis();
        long startMemory = getUsedMemory();
        createTruck.createField();
        verifyTruckCreation("ABC123", "Volvo", "VNL", 2022, true, "Location1");
        long endTime = System.currentTimeMillis();
        long endMemory = getUsedMemory();

        System.out.println("Test with 1 input passed." + "Time taken: " + (endTime - startTime) + "ms " + " Memory used: " + (endMemory - startMemory) / (1024 * 1024) + " MB");
    }

    @Test
    void testCreateTruckWithTenInputs() throws IOException {
        long startTime = System.currentTimeMillis();
        long startMemory = getUsedMemory();
        for (int i = 0; i < 10; i++) {
            setTestData("ABC" + i, "Make" + i, "Model" + i, "2022", i % 2 == 0, "Location" + i);
            createTruck.createField();
            verifyTruckCreation("ABC" + i, "Make" + i, "Model" + i, 2022, i % 2 == 0, "Location" + i);
        }
        long endTime = System.currentTimeMillis();
        long endMemory = getUsedMemory();

        System.out.println("Test with 10 inputs passed." + "Time taken: " + (endTime - startTime) + "ms " + " Memory used: " + (endMemory - startMemory) / (1024 * 1024) + " MB");
    }

    @Test
    void testCreateTruckWithFiveHundredInputs() throws IOException {
        long startTime = System.currentTimeMillis();
        long startMemory = getUsedMemory();
        for (int i = 0; i < 500; i++) {
            setTestData("ABC" + i, "Make" + i, "Model" + i, "2022", i % 2 == 0, "Location" + i);
            createTruck.createField();
            verifyTruckCreation("ABC" + i, "Make" + i, "Model" + i, 2022, i % 2 == 0, "Location" + i);
        }
        long endTime = System.currentTimeMillis();
        long endMemory = getUsedMemory();

        System.out.println("Test with 500 inputs passed." + "Time taken: " + (endTime - startTime) + "ms " + " Memory used: " + (endMemory - startMemory) / (1024 * 1024) + " MB");
    }

    @Test
    void testCreateTruckWithThousandInputs() throws IOException {
        long startTime = System.currentTimeMillis();
        long startMemory = getUsedMemory();
        for (int i = 0; i < 10000; i++) {
            setTestData("ABC" + i, "Make" + i, "Model" + i, "2022", i % 2 == 0, "Location" + i);
            createTruck.createField();
            verifyTruckCreation("ABC" + i, "Make" + i, "Model" + i, 2022, i % 2 == 0, "Location" + i);
        }
        long endTime = System.currentTimeMillis();
        long endMemory = getUsedMemory();

        System.out.println("Test with 10000 inputs passed." + "Time taken: " + (endTime - startTime) + "ms " + " Memory used: " + (endMemory - startMemory) / (1024 * 1024) + " MB");
    }

    @Test
    void testCreateTruckWithTwentyFiveHundredInputs() throws IOException {
        long startTime = System.currentTimeMillis();
        long startMemory = getUsedMemory();
        for (int i = 0; i < 2500; i++) {
            setTestData("ABC" + i, "Make" + i, "Model" + i, "2022", i % 2 == 0, "Location" + i);
            createTruck.createField();
            verifyTruckCreation("ABC" + i, "Make" + i, "Model" + i, 2022, i % 2 == 0, "Location" + i);
        }
        long endTime = System.currentTimeMillis();
        long endMemory = getUsedMemory();

        System.out.println("Test with 2500 inputs passed." + "Time taken: " + (endTime - startTime) + "ms " + " Memory used: " + (endMemory - startMemory) / (1024 * 1024) + " MB");
    }

    private void setTestData(String numPlate, String make, String model, String year, boolean transit, String location) {
        createTruck.numPlateField.setText(numPlate);
        createTruck.makeField.setText(make);
        createTruck.modelField.setText(model);
        createTruck.yearField.setText(year);
        createTruck.transitField.setSelected(transit);
        createTruck.locationField.setText(location);
    }

    private void verifyTruckCreation(String numPlate, String make, String model, int year, boolean transit, String location) {
        verify(mockTruckHib).createTruck(argThat(truck ->
                truck.getNumPlate().equals(numPlate) &&
                        truck.getMake().equals(make) &&
                        truck.getModel().equals(model) &&
                        truck.getYear() == year &&
                        truck.isInTransit() == transit &&
                        truck.getLocation().equals(location)));
    }

    private long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
