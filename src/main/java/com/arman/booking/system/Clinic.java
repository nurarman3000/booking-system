/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arman.booking.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author NurArman
 */
class Clinic {
    private List<Patient> patients = new ArrayList<>();
    private List<Physiotherapist> physiotherapists = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void listPatients() {
        patients.stream().forEach(p -> System.out.println("Patient Name: " + p.fullName + " ID: " + p.id));
    }

    public void removePatient(String patientId) {
        patients.removeIf(p -> p.getId().equals(patientId));
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPhysiotherapist(Physiotherapist p) {
        physiotherapists.add(p);
    }

    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

    public List<Physiotherapist> searchByExpertise(String area) {
        return physiotherapists.stream()
                .filter(p -> p.getSkills().contains(area))
                .collect(Collectors.toList());
    }

    public Physiotherapist searchByName(String name) {
        return physiotherapists.stream()
                .filter(p -> p.getFullName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public void bookAppointment(Patient patient, Appointment appointment, Physiotherapist physiotherapist) {
        if (appointment.isAvailable()) {
            appointment.book(patient, physiotherapist);
            System.out.println("Appointment booked successfully!");
        }
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.cancel();
        System.out.println("Appointment cancelled.");
    }

    public void attendAppointment(Appointment appointment) {
        appointment.attend();
        System.out.println("Appointment attended.");
    }

    public void generateReport() {
        System.out.println("--- BPC Clinic Report ---");
        Map<Physiotherapist, Long> attendedCount = new HashMap<>();

        for (Physiotherapist p : physiotherapists) {
            List<Appointment> all = p.getAllAppointments();
            System.out.println("\nPhysiotherapist: " + p.getFullName());

            for (Appointment appt : all) {
                List<Treatment> treatmentsForAppointment = p.getTreatments().stream()
                        .filter(t -> p.getAllAppointments().contains(appt))
                        .collect(Collectors.toList());

                if (!treatmentsForAppointment.isEmpty()) {
                    for (Treatment treatment : treatmentsForAppointment) {
                        System.out.println("Treatment: " + treatment.getName() +
                                ", Patient: " + (appt.getPatient() != null ? appt.getPatient().getFullName() : "None") +
                                ", Time: " + appt.getStartTime() +
                                ", Status: " + appt.getStatus());
                    }
                } else {
                    System.out.println("No treatment found for this appointment.");
                }
            }

            long attended = all.stream().filter(a -> a.getStatus() == AppointmentStatus.ATTENDED).count();
            attendedCount.put(p, attended);
        }

        System.out.println("\n--- Physiotherapists by Attended Appointments ---");
        attendedCount.entrySet().stream()
                .sorted(Map.Entry.<Physiotherapist, Long>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey().getFullName() + ": " + e.getValue()));
    }

}
