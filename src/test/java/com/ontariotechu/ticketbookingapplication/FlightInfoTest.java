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





