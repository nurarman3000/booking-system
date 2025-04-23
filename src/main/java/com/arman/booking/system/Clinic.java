/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arman.booking.system;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NurArman
 */
class Clinic {
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    
    public void listPatients() {
        patients.stream().forEach(p -> System.out.println("Patient Name: " + p.fullName + " ID: " + p.id ));
    }

    public void removePatient(String patientId) {
        patients.removeIf(p -> p.getId().equals(patientId));
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
