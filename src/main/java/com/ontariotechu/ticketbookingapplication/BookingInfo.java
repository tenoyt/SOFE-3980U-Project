package com.ontariotechu.ticketbookingapplication;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingInfo {

    private static final String FLIGHTS_FILE = "flightinfo.txt";
    private static final String BOOKING_FILE = "bookinginfo.txt";

    public static void writeToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_FILE, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void getBookingInfo(String flightNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FLIGHTS_FILE))) {
            boolean found = false;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(flightNumber)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Flight " + flightNumber + " is not available for booking.");
                return;
            }

            // Proceed with booking
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter booking details for flight " + flightNumber + ":");
            System.out.print("Is it a one-way or round-trip flight? (one-way/round-trip): ");
            String tripType = scanner.nextLine().toLowerCase();
            while (!tripType.equals("one-way") && !tripType.equals("round-trip")) {
                System.out.print("Invalid input. Please enter 'one-way' or 'round-trip': ");
                tripType = scanner.nextLine().toLowerCase();
            }

            System.out.print("Do you want to book a multi-stop flight? (yes/no): ");
            String multiStopChoice = scanner.nextLine().toLowerCase();
            if (multiStopChoice.equals("yes")) {
                handleMultiStopBooking(flightNumber, tripType);
            } else {
                // Handle regular one-way or round-trip booking
                System.out.print("Passenger Name: ");
                String passengerName = scanner.nextLine();
                System.out.print("Seat Number: ");
                String seatNumber = scanner.nextLine();

                // Generate booking ID
                int bookingId = generateBookingId();

                String bookingDetails = "Booking ID: " + bookingId +
                        ", Flight Number: " + flightNumber +
                        ", Trip Type: " + tripType +
                        ", Passenger Name: " + passengerName +
                        ", Seat Number: " + seatNumber;
                writeToFile(bookingDetails);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static int generateBookingId() {
        // Generate a unique booking ID
        // This is a simple method, you may want to use a more sophisticated approach in production
        return (int) (Math.random() * 100000);
    }

    public static void handleMultiStopBooking(String flightNumber, String tripType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many stops does your multi-stop flight have? ");
        int numStops = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        List<String> stopDetails = new ArrayList<>();
        for (int i = 1; i <= numStops; i++) {
            System.out.println("Enter airport code for stop " + i + ":");
            String airport = scanner.nextLine();
            stopDetails.add("Stop " + i + ": " + "Airport Code: " + airport);
        }

        // Handle regular one-way or round-trip booking
        System.out.print("Passenger Name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Seat Number: ");
        String seatNumber = scanner.nextLine();

        // Generate booking ID
        int bookingId = generateBookingId();

        // Construct booking details
        StringBuilder bookingDetails = new StringBuilder();
        bookingDetails.append("Booking ID: ").append(bookingId);
        bookingDetails.append(", Flight Number: ").append(flightNumber);
        bookingDetails.append(", Trip Type: ").append(tripType);
        bookingDetails.append(", Stops: ");
        for (String stop : stopDetails) {
            bookingDetails.append(stop).append(" ");
        }
        bookingDetails.append(", Passenger Name: ").append(passengerName);
        bookingDetails.append(", Seat Number: ").append(seatNumber);
        writeToFile(bookingDetails.toString());
    }

    public static void getBookingInfoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_FILE))) {
            String bookingDetails;
            System.out.println("Booking details:");
            while ((bookingDetails = reader.readLine()) != null) {
                System.out.println(bookingDetails);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome Admin!");
        System.out.println("Menu:\n" +
                "1. Enter booking information\n" +
                "2. View booking information\n" +
                "Enter your choice:");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline character
        switch (choice) {
            case 1:
                System.out.print("Enter flight number: ");
                String flightNumber = sc.nextLine();
                getBookingInfo(flightNumber);
                break;
            case 2:
                getBookingInfoFromFile();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
