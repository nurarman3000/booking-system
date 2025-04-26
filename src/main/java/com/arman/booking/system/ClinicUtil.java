package com.arman.booking.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author NurArman
 */

public class ClinicUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static void addPatient(Clinic clinic) {
        System.out.print("\nEnter Patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Patient Phone Number: ");
        String phone = scanner.nextLine();

        Patient patient = new Patient(id, name, address, phone);
        clinic.addPatient(patient);
        System.out.println("Patient " + name + " added successfully.");
    }

    public static void removePatient(Clinic clinic) {
        System.out.print("\nEnter Patient ID to remove: ");
        String patientId = scanner.nextLine();

        Patient patient = clinic.getPatients().stream()
                .filter(p -> p.getId().equals(patientId))
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        clinic.getPhysiotherapists().stream()
                .flatMap(pt -> pt.getAllAppointments().stream())
                .filter(appt -> appt.getPatient() != null && appt.getPatient().getId().equals(patientId))
                .forEach(Appointment::cancel);

        // Remove patient from clinic
        clinic.removePatient(patientId);
        System.out.println("Patient removed and appointments cancelled.");
    }

    public static void searchByExpertise(Clinic clinic) {
        System.out.print("\nEnter expertise area (e.g., Physiotherapy, Massage): ");
        String expertise = scanner.nextLine();
        System.out.println("Searching for physiotherapists with expertise in " + expertise + "...");
        clinic.searchByExpertise(expertise).forEach(pt -> {
            System.out.println("\nPhysiotherapist: " + pt.getFullName());
            pt.getTreatments().forEach(t -> {
                System.out.println("  Treatment: " + t.getName());
                pt.getAllAppointments().forEach(appt -> {
                    System.out.println("    Time: " + appt.getStartTime());
                });
            });
        });
    }

    public static void searchByPhysiotherapist(Clinic clinic) {
        System.out.print("\nEnter physiotherapist's name: ");
        String name = scanner.nextLine();
        Physiotherapist pt = clinic.searchByName(name);

        if (pt != null) {
            System.out.println("\nPhysiotherapist: " + pt.getFullName());
            pt.getTreatments().forEach(t -> {
                System.out.println("  Treatment: " + t.getName());
                pt.getAllAppointments().forEach(appt -> {
                    System.out.println("    Time: " + appt.getStartTime());
                });
            });
        } else {
            System.out.println("Physiotherapist not found.");
        }
    }

    public static void bookAppointment(Clinic clinic) {
        System.out.print("\nEnter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = clinic.getPatients().stream()
                .filter(p -> p.getId().equals(patientId))
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.print("Enter Treatment name to book: ");
        String treatmentName = scanner.nextLine();

        // Find physiotherapists who offer this treatment (now based on physiotherapist
        // skills)
        List<Physiotherapist> availablePhysios = clinic.getPhysiotherapists().stream()
                .filter(p -> p.getSkills().contains(treatmentName))
                .collect(Collectors.toList());

        if (availablePhysios.isEmpty()) {
            System.out.println("No physiotherapist found offering this treatment.");
            return;
        }

        // Show available physiotherapists and their available time slots with IDs
        System.out.println("Available physiotherapists and times:");
        List<Appointment> availableAppointments = new ArrayList<>();
        int slotCounter = 1;

        for (Physiotherapist pt : availablePhysios) {
            for (Appointment appt : pt.getAllAppointments()) {
                if (appt.isAvailable()) {
                    System.out
                            .println(slotCounter + ". Physio: " + pt.getFullName() + " | Time: " + appt.getStartTime());
                    availableAppointments.add(appt);
                    slotCounter++;
                }
            }
        }

        if (availableAppointments.isEmpty()) {
            System.out.println("No available slots for the selected treatment.");
            return;
        }

        System.out.print("Enter the number corresponding to the time slot you want to book: ");
        int appointmentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (appointmentChoice < 1 || appointmentChoice > availableAppointments.size()) {
            System.out.println("Invalid choice! Please enter a valid slot number.");
            return;
        }

        Appointment selectedAppt = availableAppointments.get(appointmentChoice - 1);

        if (!selectedAppt.isAvailable()) {
            System.out.println("That appointment slot is no longer available.");
            return;
        }

        // Book the appointment using the appointmentId
        clinic.bookAppointment(patient, selectedAppt);
    }

    public static void cancelAppointment(Clinic clinic) {
        System.out.print("\nEnter Appointment ID to cancel: ");
        String appointmentId = scanner.nextLine();

        // Find the appointment using the appointmentId
        Appointment appointmentToCancel = clinic.getPhysiotherapists().stream()
                .flatMap(pt -> pt.getAllAppointments().stream())
                .filter(appt -> appt.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);

        if (appointmentToCancel == null) {
            System.out.println("Appointment not found!");
            return;
        }

        clinic.cancelAppointment(appointmentToCancel);
    }

    public static void attendAppointment(Clinic clinic) {
        System.out.print("\nEnter Appointment ID to mark attendance: ");
        String appointmentId = scanner.nextLine();

        // Find the appointment using the appointmentId
        Appointment selectedAppt = clinic.getPhysiotherapists().stream()
                .flatMap(pt -> pt.getAllAppointments().stream())
                .filter(appt -> appt.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);

        if (selectedAppt != null && selectedAppt.getStatus() == AppointmentStatus.BOOKED) {
            selectedAppt.attend();
            System.out.println("Attendance marked successfully for Appointment ID: " + appointmentId);
        } else {
            System.out.println("No such booked appointment found to mark attended.");
        }
    }

    public static void generateReport(Clinic clinic) {
        clinic.generateReport();
    }

    public static void listAllPatients(Clinic clinic) {
        clinic.listPatients();
    }

    public static void viewAllBookedAppointments(Clinic clinic) {
        System.out.println("\nListing all booked appointments:");

        List<Appointment> bookedAppointments = clinic.getPhysiotherapists().stream()
                .flatMap(pt -> pt.getAllAppointments().stream())
                .filter(appt -> appt.getStatus() == AppointmentStatus.BOOKED)
                .collect(Collectors.toList());

        if (bookedAppointments.isEmpty()) {
            System.out.println("No booked appointments found.");
            return;
        }

        int counter = 1;
        for (Appointment appt : bookedAppointments) {
            System.out.println(counter + ". Appointment ID: " + appt.getAppointmentId() +
                    " | Patient: " + (appt.getPatient() != null ? appt.getPatient().getFullName() : "N/A") +
                    " | Physio: " + (appt.getPhysiotherapist() != null ? appt.getPhysiotherapist().getFullName() : "N/A") +
                    " | Time: " + appt.getStartTime());
            counter++;
        }
    }
}
