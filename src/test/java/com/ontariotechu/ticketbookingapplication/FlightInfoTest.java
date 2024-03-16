package com.ontariotechu.ticketbookingapplication;

import ch.qos.logback.classic.Level;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class FlightInfoTest {

    private final String testFileName = "testFlightInfo.txt";

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(testFileName));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(testFileName));
    }

    @Test
    void testWriteToFile() throws IOException {
        String testData = "Test Flight Data";
        FlightInfo.writeToFile(testFileName, testData);

        try (BufferedReader reader = new BufferedReader(new FileReader(testFileName))) {
            String readData = reader.readLine();
            assertEquals(testData, readData, "The written data should match the test data.");
        }
    }
    @Test
    void testFlightInfoReadsCorrectly() throws IOException {
        // The rest of the setup code remains unchanged

        FlightInfo.flightinfo();

        // Normalize newlines and trim whitespace before comparison
        
        String expectedOutputNormalized = expectedOutput.replace("\r\n", "\n").trim();
        Level outContent = null;
        String actualOutputNormalized = outContent.toString().replace("\r\n", "\n").trim();

        // Use assertEquals to compare the normalized and trimmed strings
        assertEquals(expectedOutputNormalized, actualOutputNormalized, "The flight details should match the expected output.");

        // Cleanup: Reset System.out and delete test file
        System.setOut(System.out);
        Files.deleteIfExists(Paths.get("flightinfo.txt"));
    }


    @Test
    void testFlightInfoWithIncorrectFormat() throws IOException {
        // Pre-populate the file with an incorrectly formatted line
        String incorrectData = "Incorrectly formatted data";
        Files.write(Paths.get(testFileName), Collections.singletonList(incorrectData));

        // Redirect standard error to capture the error output
        final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        final PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        // Call the method under test
        FlightInfo.flightinfo();

        // Restore standard error so it can be used normally again
        System.setErr(originalErr);

        // Verify that an error message was printed to standard error
        assertTrue(errContent.toString().contains("Invalid data format"));

        // Clean up test file
        Files.deleteIfExists(Paths.get(testFileName));
    }
    @Test
    void testAddingFlightInfo() throws IOException {
        // Setup - Assume a method to add flight info programmatically or mock user input
        String flightNumber = "124";
        String departureAirport = "DEF";
        String destinationAirport = "GHI";
        String departureTime = "12:00";
        FlightInfo.addFlightInfo(flightNumber, departureAirport, destinationAirport, departureTime); // Assume this method exists

        // Read the file content
        List<String> lines = Files.readAllLines(Paths.get("flightinfo.txt"));

        // Verify the flight info was added correctly
        assertTrue(lines.contains("Flight Number: " + flightNumber + ", Departure Airport: " + departureAirport
                + ", Destination Airport: " + destinationAirport + ", Departure Time: " + departureTime));
    }
    @Test
    void testEditingFlightInfo() throws IOException {
        // Setup - first add a flight, then edit it
        String originalFlightDetails = "Flight Number: 125, Departure Airport: JKL, Destination Airport: MNO, Departure Time: 14:00";
        Files.write(Paths.get("flightinfo.txt"), Collections.singletonList(originalFlightDetails));
        String updatedDepartureTime = "15:00";
        FlightInfo.editFlightInfo("125", "JKL", "MNO", updatedDepartureTime);

        // Verify the file content has been updated
        List<String> lines = Files.readAllLines(Paths.get("flightinfo.txt"));
        assertTrue(lines.contains("Flight Number: 125, Departure Airport: JKL, Destination Airport: MNO, Departure Time: " + updatedDepartureTime));
    }

}

