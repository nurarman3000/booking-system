package com.arman.booking.system;

import java.time.LocalDateTime;

/**
 *
 * @author NurArman
 */
public class Appointment {
    private static int appointmentCounter = 1;  // To generate unique appointment IDs
    private final String appointmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AppointmentStatus status;
    private Patient patient;
    private Physiotherapist physiotherapist;

    public Appointment(LocalDateTime startTime, LocalDateTime endTime) {
        this.appointmentId = "APPT" + appointmentCounter++;  // Generate a unique ID
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = AppointmentStatus.AVAILABLE; // Default status is AVAILABLE
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public void setPhysiotherapist(Physiotherapist physiotherapist) {
        this.physiotherapist = physiotherapist;
    }

    public void book(Patient patient, Physiotherapist physiotherapist) {
        this.patient = patient;
        this.physiotherapist = physiotherapist;
        this.status = AppointmentStatus.BOOKED;
    }

    public void cancel() {
        if (this.status == AppointmentStatus.ATTENDED)
            return;
        this.status = AppointmentStatus.AVAILABLE;
        this.patient = null;
    }

    public void attend() {
        this.status = AppointmentStatus.ATTENDED;
    }

    public boolean isAvailable() {
        return status == AppointmentStatus.AVAILABLE && startTime.isAfter(LocalDateTime.now());
    }
}