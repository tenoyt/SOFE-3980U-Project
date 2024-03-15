package com.ontariotechu.ticketbookingapplication;
import java.util.*;
import java.io.*;


public class FlightInfo {
    //Enter flight info into file
    private static void writeToFile(String fileName, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(data);
            writer.newLine(); // Add newline for each entry
            writer.close();
        } catch (IOException e) {
            System.err.println(STR."Error writing to file: \{e.getMessage()}");
        }
    }
    //Get flight info from user
    public static void getflightinfo(){
        Scanner scanner = new Scanner(System.in);
        //ask user for input
        System.out.println("Enter flight details:");
        System.out.print("Flight Number: ");
        String flightNumber = scanner.nextLine();

        System.out.print("Departure Airport: ");
        String departureAirport = scanner.nextLine();

        System.out.print("Destination Airport: ");
        String destinationAirport = scanner.nextLine();

        System.out.print("Departure Time: ");
        String departureTime = scanner.nextLine();
        String flightDetails = "Flight Number: " + flightNumber + ", Departure Airport: " + departureAirport
                + ", Destination Airport: " + destinationAirport + ", Departure Time: " + departureTime;
        writeToFile("flightinfo.txt", flightDetails);
    }
    //to retrieve flight info from file
    public static void flightinfo(){
        String fileName = "flightinfo.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String flightDetails;
            System.out.println("Flight details:");
            // Read each line from the file and process it
            while ((flightDetails = reader.readLine()) != null) {
                // Splitting the line into individual components
                String[] components = flightDetails.split(", ");

                // Accessing each piece of flight information individually
                for (String component : components) {
                    // Splitting the component into key-value pairs
                    String[] keyValue = component.split(": ");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    System.out.println(key + ": " + value);
                }
                System.out.println(); // Add a blank line between flights
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    public static void editFlight(){
        String fileName = "flightinfo.txt";

        try {
            // Read the contents of the file and store them in a list
            List<String> flights = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                flights.add(line);
            }
            reader.close();
            // Display flight information to the user
            System.out.println("Flight details:");
            for (int i = 0; i < flights.size(); i++) {
                System.out.println((i + 1) + ". " + flights.get(i));
            }

            // Prompt the user to select which flight to edit
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the flight to edit: ");
            int flightIndex = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Prompt the user to enter the new flight information
            System.out.println("Enter new flight information:");
            System.out.print("Flight Number: ");
            String flightNumber = scanner.nextLine();
            System.out.print("Departure Airport: ");
            String departureAirport = scanner.nextLine();
            System.out.print("Destination Airport: ");
            String destinationAirport = scanner.nextLine();
            System.out.print("Departure Time: ");
            String departureTime = scanner.nextLine();
            // Update the selected flight information
            String newFlightInfo = "Flight Number: " + flightNumber + ", Departure Airport: " + departureAirport
                    + ", Destination Airport: " + destinationAirport + ", Departure Time: " + departureTime;
            flights.set(flightIndex - 1, newFlightInfo);
            // Rewrite the updated flight information back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String flight : flights) {
                writer.write(flight);
                writer.newLine();
            }
            writer.close();
            System.out.println("Flight information updated successfully!");
        } catch (IOException e) {
            System.err.println("Error reading or writing to file: " + e.getMessage());
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean entermore = true;
        System.out.println("Welcome Admin!");
        System.out.println("Menu:\n" +
                    "1. Enter new flights\n" +
                    "2. Edit existing flights\n" +
                    "Enter your choice:");
        int ch = sc.nextInt();
        if (ch == 1) {
            while (entermore) {
                getflightinfo();
                System.out.print("Do you want to enter another flight? (yes/no): ");
                String choice = sc.nextLine().toLowerCase();
                entermore = choice.equals("yes");
            }
        }
        else if (ch==2){
            System.out.println("Enter Flight Number");
            editFlight();
        }
    }
}
