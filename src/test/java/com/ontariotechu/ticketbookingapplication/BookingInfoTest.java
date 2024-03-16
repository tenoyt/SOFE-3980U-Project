package com.ontariotechu.ticketbookingapplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BookingInfoTest {

    @Test
    public void testRegularBooking() {
        String simulatedInput = "one-way\nno\nJohn Doe\n12A\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BookingInfo.getBookingInfo("ABC123");

        String expectedOutput = "Enter booking details for flight ABC123:\n" +
                "Is it a one-way or round-trip flight? (one-way/round-trip): Do you want to book a multi-stop flight? (yes/no): Passenger Name: Seat Number: ";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testMultiStopBooking() {
        String simulatedInput = "one-way\nyes\n2\nJFK\nLAX\nJohn Doe\n12A\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BookingInfo.getBookingInfo("ABC123");

        String expectedOutput = "Enter booking details for flight ABC123:\n" +
                "Is it a one-way or round-trip flight? (one-way/round-trip): Do you want to book a multi-stop flight? (yes/no): How many stops does your multi-stop flight have? " +
                "Enter airport code for stop 1:\n" +
                "Enter airport code for stop 2:\n" +
                "Passenger Name: Seat Number: ";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testInvalidFlightNumber() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BookingInfo.getBookingInfo("XYZ456");

        String expectedOutput = "Flight XYZ456 is not available for booking.\n";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testInvalidTripType() {
        String simulatedInput = "direct\none\none-way\nno\nJohn Doe\n12A\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BookingInfo.getBookingInfo("ABC123");

        String expectedOutput = "Enter booking details for flight ABC123:\n" +
                "Is it a one-way or round-trip flight? (one-way/round-trip): Invalid input. Please enter 'one-way' or 'round-trip': " +
                "Do you want to book a multi-stop flight? (yes/no): Passenger Name: Seat Number: ";

        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }

}
