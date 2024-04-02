package com.ontariotechu.ticketbookingapplication;

import ch.qos.logback.classic.Level;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Nested
class FlightInfoTest {
    private final String testFileName = "testFlightInfo.txt";

    @Test
    void testFlightInfo() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the method to test
        FlightInfo.flightInfo();

        // Get the actual output
        String actualOutput = outContent.toString();

        // Normalize line separators in actual output
        actualOutput = actualOutput.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");

        // Restore standard output
        System.setOut(System.out);

        // Verify output
        String expectedOutput = "Flight details:" + LINE_SEPARATOR +
                "Flight Number: RS180, Departure Airport: Surrey, Destination Airport: Montreal, Day of the week: Wednesday, Departure Time: 19:00, Arrival Time: 4:00" + LINE_SEPARATOR +
                "Flight Time: 9 hours 0 minutes" + LINE_SEPARATOR +
                "Flight Number: AC123, Departure Airport: Toronto, Destination Airport: Montreal, Day of the week: Friday, Departure Time: 13:00, Arrival Time: 17:00" + LINE_SEPARATOR +
                "Flight Time: 4 hours 0 minutes" + LINE_SEPARATOR +
                "Flight Number: AB990, Departure Airport: Hallifax, Destination Airport: Vancouver, Day of the week: Tuesday, Departure Time: 11:00, Arrival Time: 21:00" + LINE_SEPARATOR +
                "Flight Time: 10 hours 0 minutes" + LINE_SEPARATOR;

        // Normalize line separators in expected output
        expectedOutput = expectedOutput.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");

        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    void testFlightTimeMidnightCrossing() {
        assertEquals(540, FlightInfo.calculateFlightTime("19:00", "04:00"),
                "Flight crossing midnight should be calculated correctly.");
    }

    @Test
    void testFlightTimeSameDay() {
        assertEquals(240, FlightInfo.calculateFlightTime("13:00", "17:00"),
                "Flight on the same day should be calculated correctly.");
    }

    @Test
    void testFlightTimeEarlyMorningToEvening() {
        assertEquals(600, FlightInfo.calculateFlightTime("11:00", "21:00"),
                "Flight from morning to evening should be calculated correctly.");
    }

    @Test
    void testFlightTimeWithMinutes() {
        assertEquals(130, FlightInfo.calculateFlightTime("12:15", "14:25"),
                "Flight time including minutes calculation should be accurate.");
    }

    @Test
    void testFlightTimeWhenDepartureEqualsArrival() {
        assertEquals(0, FlightInfo.calculateFlightTime("15:00", "15:00"),
                "Flight time should be 0 when departure and arrival times are the same.");
    }
}





