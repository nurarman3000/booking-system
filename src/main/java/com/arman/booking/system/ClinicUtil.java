
package com.arman.booking.system;
import java.time.LocalDateTime;
import java.util.Scanner;

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
                .flatMap(pt -> pt.getTreatments().stream())
                .flatMap(treatment -> treatment.getAppointments().stream())
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
                t.getAppointments().forEach(appt -> {
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
                t.getAppointments().forEach(appt -> {
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
        Physiotherapist pt = clinic.getPhysiotherapists().stream()
                .filter(p -> p.getTreatments().stream()
                        .anyMatch(t -> t.getName().equalsIgnoreCase(treatmentName)))
                .findFirst()
                .orElse(null);

        if (pt == null) {
            System.out.println("Treatment not found!");
            return;
        }

        Treatment treatment = pt.getTreatments().stream()
                .filter(t -> t.getName().equalsIgnoreCase(treatmentName))
                .findFirst()
                .orElse(null);

        System.out.println("Available times for " + treatmentName + ":");
        treatment.getAvailableAppointments().forEach(appt -> {
            System.out.println("  Time: " + appt.getStartTime());
        });

        System.out.print("Enter the time slot (yyyy-MM-ddTHH:mm): ");
        String dateTime = scanner.nextLine();
        LocalDateTime appointmentTime = LocalDateTime.parse(dateTime);

        Appointment selectedAppt = treatment.getAppointments().stream()
                .filter(appt -> appt.getStartTime().equals(appointmentTime))
                .findFirst()
                .orElse(null);

        if (selectedAppt == null) {
            System.out.println("No appointment exists at that time.");
        } else if (!selectedAppt.isAvailable()) {
            System.out.println("That appointment has already been booked by another patient.");
        } else {
            clinic.bookAppointment(patient, selectedAppt);
            System.out.println("Appointment booked successfully!");
        }
    }
    
    public static void cancelAppointment(Clinic clinic) {
        System.out.print("\nEnter Patient ID to cancel appointment: ");
        String patientId = scanner.nextLine();
        Patient patient = clinic.getPatients().stream()
                .filter(p -> p.getId().equals(patientId))
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.print("Enter Treatment name to cancel: ");
        String treatmentName = scanner.nextLine();
        Physiotherapist pt = clinic.getPhysiotherapists().stream()
                .filter(p -> p.getTreatments().stream()
                        .anyMatch(t -> t.getName().equalsIgnoreCase(treatmentName)))
                .findFirst()
                .orElse(null);

        if (pt == null) {
            System.out.println("Treatment not found!");
            return;
        }

        Treatment treatment = pt.getTreatments().stream()
                .filter(t -> t.getName().equalsIgnoreCase(treatmentName))
                .findFirst()
                .orElse(null);

        System.out.print("Enter the time slot to cancel (yyyy-MM-ddTHH:mm): ");
        String dateTime = scanner.nextLine();
        LocalDateTime appointmentTime = LocalDateTime.parse(dateTime);

        Appointment selectedAppt = treatment.getAppointments().stream()
                .filter(appt -> appt.getStartTime().equals(appointmentTime))
                .findFirst()
                .orElse(null);

        if (selectedAppt != null && selectedAppt.getPatient() != null) {
            clinic.cancelAppointment(selectedAppt);
            System.out.println("Appointment cancelled successfully!");
        } else {
            System.out.println("No such booked appointment found.");
        }
    }

    public static void attendAppointment(Clinic clinic) {
        System.out.print("\nEnter Patient ID to mark attendance: ");
        String patientId = scanner.nextLine();
        Patient patient = clinic.getPatients().stream()
                .filter(p -> p.getId().equals(patientId))
                .findFirst()
                .orElse(null);
    
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }
    
        System.out.print("Enter Treatment name: ");
        String treatmentName = scanner.nextLine();
        Physiotherapist pt = clinic.getPhysiotherapists().stream()
                .filter(p -> p.getTreatments().stream()
                        .anyMatch(t -> t.getName().equalsIgnoreCase(treatmentName)))
                .findFirst()
                .orElse(null);
    
        if (pt == null) {
            System.out.println("Treatment not found!");
            return;
        }
    
        Treatment treatment = pt.getTreatments().stream()
                .filter(t -> t.getName().equalsIgnoreCase(treatmentName))
                .findFirst()
                .orElse(null);
    
        System.out.print("Enter appointment time to mark attended (yyyy-MM-ddTHH:mm): ");
        String dateTime = scanner.nextLine();
        LocalDateTime appointmentTime = LocalDateTime.parse(dateTime);
    
        Appointment selectedAppt = treatment.getAppointments().stream()
                .filter(appt -> appt.getStartTime().equals(appointmentTime))
                .findFirst()
                .orElse(null);
    
        if (selectedAppt != null && selectedAppt.getPatient() != null
            && selectedAppt.getPatient().getId().equals(patientId)
            && selectedAppt.getStatus() == AppointmentStatus.BOOKED) {
            
            selectedAppt.attend();
            System.out.println("Attendance marked successfully!");
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
}

