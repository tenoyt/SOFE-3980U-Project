package com.ontariotechu.ticketbookingapplication;

import java.io.*;
import java.util.*;

public class FlightInfo {

    private static final String FILE_NAME = "flightinfo.txt";

    public static void writeToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static int calculateFlightTime(String departureTime, String arrivalTime) {
        // Split the departure and arrival times into hours and minutes
        String[] departureParts = departureTime.split(":");
        String[] arrivalParts = arrivalTime.split(":");

        // Parse hours and minutes from the split strings
        int departureHours = Integer.parseInt(departureParts[0]);
        int departureMinutes = Integer.parseInt(departureParts[1]);
        int arrivalHours = Integer.parseInt(arrivalParts[0]);
        int arrivalMinutes = Integer.parseInt(arrivalParts[1]);

        // Convert hours to minutes and add the minutes to get total minutes from midnight
        int departureTotalMinutes = departureHours * 60 + departureMinutes;
        int arrivalTotalMinutes = arrivalHours * 60 + arrivalMinutes;

        // Calculate the difference in minutes
        int difference = arrivalTotalMinutes - departureTotalMinutes;

        // If the arrival is on the next day (difference is negative), adjust by adding 24 hours worth of minutes
        if (difference < 0) {
            difference += 24 * 60;
        }

        return difference;
    }
    
    public static void getFlightInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight details:");
        System.out.print("Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Departure Airport: ");
        String departureAirport = scanner.nextLine();
        System.out.print("Destination Airport: ");
        String destinationAirport = scanner.nextLine();
        System.out.print("Day of the week: ");
        String dayOfWeek = scanner.nextLine(); // Add day of the week input
        System.out.print("Departure Time (24-hour format): ");
        String departureTime = scanner.nextLine();
        System.out.print("Arrival Time (24-hour format): ");
        String arrivalTime = scanner.nextLine(); // Add arrival time input
        String flightDetails = "Flight Number: " + flightNumber +
                ", Departure Airport: " + departureAirport +
                ", Destination Airport: " + destinationAirport +
                ", Day of the week: " + dayOfWeek + // Include day of the week in flight details
                ", Departure Time: " + departureTime +
                ", Arrival Time: " + arrivalTime; // Include arrival time in flight details
        writeToFile(flightDetails);
    }

    public static void flightInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String flightDetails;
            System.out.println("Flight details:");
            while ((flightDetails = reader.readLine()) != null) {
                System.out.println(flightDetails);
                // Calculate flight time
                String[] detailsArray = flightDetails.split(", ");
                String departureTime = detailsArray[4].substring(detailsArray[4].indexOf(":") + 2);
                String arrivalTime = detailsArray[5].substring(detailsArray[5].indexOf(":") + 2);
                int departureHour = Integer.parseInt(departureTime.split(":")[0]);
                int departureMinute = Integer.parseInt(departureTime.split(":")[1]);
                int arrivalHour = Integer.parseInt(arrivalTime.split(":")[0]);
                int arrivalMinute = Integer.parseInt(arrivalTime.split(":")[1]);
                int totalMinutes = (arrivalHour - departureHour) * 60 + (arrivalMinute - departureMinute);
                int flightHours = totalMinutes / 60;
                int flightMinutes = totalMinutes % 60;
                System.out.println("Flight Time: " + flightHours + " hours " + flightMinutes + " minutes");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome Admin!");
        System.out.println("""
                Menu:
                1. Enter new flights
                2. View flight information
                Enter your choice:""");
        int ch = sc.nextInt();
        sc.nextLine();
        if (ch == 1) {
            boolean enterMore = true;
            while (enterMore) {
                getFlightInfo();
                System.out.print("Do you want to enter another flight? (yes/no): ");
                String choice = sc.nextLine().toLowerCase();
                enterMore = choice.equals("yes");
            }
        } else if (ch == 2) {
            flightInfo();
        }
    }
}
