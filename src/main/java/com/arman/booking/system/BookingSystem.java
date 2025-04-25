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
                case "2":
                    ClinicUtil.searchByExpertise(clinic);
                    break;
                case "3":
                    ClinicUtil.searchByPhysiotherapist(clinic);
                    break;
                case "4":
                    ClinicUtil.bookAppointment(clinic);
                    break;
                case "5":
                    ClinicUtil.cancelAppointment(clinic);
                    break;
                case "6": 
                    ClinicUtil.attendAppointment(clinic);
                    break;
                case "7":
                    ClinicUtil.generateReport(clinic);
                    break;
                case "8":
                    ClinicUtil.listAllPatients(clinic);
                    break;
                case "9":
                    ClinicUtil.removePatient(clinic);
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
        System.out.println("2. Search by expertise");
        System.out.println("3. Search by physiotherapist");
        System.out.println("4. Book an appointment");
        System.out.println("5. Cancel an appointment");
        System.out.println("6. Attend appointment");
        System.out.println("7. Generate report");
        System.out.println("8. See Patient List");
        System.out.println("9. Remove Patient");
        System.out.println("Type 'exit' to quit");
        System.out.print("Choose an option: ");
    }
}
