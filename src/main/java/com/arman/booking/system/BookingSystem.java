package com.arman.booking.system;

import java.util.Scanner;

public class BookingSystem {

    private static Clinic clinic;
    private static Scanner scanner;

    public static void main(String[] args) {
        clinic = DataInitializer.initializeClinic(); // Initialize clinic with preloaded data
        scanner = new Scanner(System.in);

        System.out.println("Welcome to Boost Physio Clinic (BPC) Booking System!");

        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            String userInput = scanner.nextLine();
            switch (userInput.toLowerCase()) {
                case "1":
                    ClinicUtil.addPatient(clinic);
                    break;
                case "exit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }

        System.out.println("Exiting... Thank you for using BPC Booking System!");
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a new patient");
        System.out.println("Type 'exit' to quit");
        System.out.print("Choose an option: ");
    }
}
