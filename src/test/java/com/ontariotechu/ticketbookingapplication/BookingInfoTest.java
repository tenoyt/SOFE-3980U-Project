import org.example.BookingInfo;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BookingInfoTest {

    @Test
    public void testValidOneWayBooking() {
        // Test case 1: Valid one-way booking
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String simulatedUserInput = "flight123\none-way\nno\nJohn Doe\nA123\n";

        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);

        BookingInfo bookingInfo = new BookingInfo(); // Create an instance of BookingInfo
        bookingInfo.getBookingInfo("flight123"); // Call the method on the instance

        String expectedOutput = "Enter booking details for flight flight123:\n";
        expectedOutput += "Passenger Name: Seat Number: ";
        String actualOutput = outContent.toString().replaceAll("\r", "");

        System.out.println("Actual Output: " + actualOutput); // Print out the actual output

        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);

    }
    

    @Test
    public void testInvalidFlightNumber() {
        // Test case 4: Invalid flight number
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String simulatedUserInput = "";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);

        BookingInfo bookingInfo = new BookingInfo(); // Create an instance of BookingInfo
        bookingInfo.getBookingInfo("invalidFlightNumber"); // Call the method on the instance

        String expectedOutput = "Flight invalidFlightNumber is not available for booking.";
        String actualOutput = outContent.toString().replaceAll("\r", "");

        assertTrue(actualOutput.contains(expectedOutput));
    }



    @Test
    public void testValidInputFileReading() {
        // Test case 8: Valid input file reading
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        File testFile = new File("bookinginfo.txt");
        if (!testFile.exists()) {
            try {
                testFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BookingInfo bookingInfo = new BookingInfo(); // Create an instance of BookingInfo
        bookingInfo.getBookingInfoFromFile(); // Call the method on the instance
        String actualOutput = outContent.toString().replaceAll("\r", "");
        assertTrue(actualOutput.contains("Booking details:"));
    }


    @Test
    public void testRandomBookingIdGeneration() {
        // Test case 10: Random booking ID generation
        BookingInfo bookingInfo = new BookingInfo(); // Create an instance of BookingInfo
        int id1 = bookingInfo.generateBookingId(); // Call the method
        int id2 = bookingInfo.generateBookingId(); // Call the method again
        assertNotEquals(id1, id2); // Ensure generated IDs are not equal
    }
    @Test
    public void testGetBookingInfo_flightNotAvailable() {
        // Simulate input for a flight that is not available
        String flightNumber = "NonExistentFlight";

        // Redirect output to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call method to get booking information
        BookingInfo.getBookingInfo(flightNumber);

        // Verify console output
        String output = outContent.toString();
        assertTrue(output.contains("Flight NonExistentFlight is not available for booking."), "Error message not displayed.");
    }
    @Test
    public void testGetBookingInfoFromFile() {
        // Test if the method correctly reads booking information from the file
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to print booking information from the file
        BookingInfo.getBookingInfoFromFile();

        // Verify that output is not empty
        String output = outContent.toString();
        assertFalse(output.isEmpty(), "Booking information not printed from file.");
    }






}
