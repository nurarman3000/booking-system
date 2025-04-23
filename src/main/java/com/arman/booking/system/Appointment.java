package com.arman.booking.system;

import java.time.LocalDateTime;

/**
 *
 * @author NurArman
 */
public class Appointment {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Treatment treatment;
    private Patient patient;
    private AppointmentStatus status;

    public Appointment(LocalDateTime startTime, LocalDateTime endTime, Treatment treatment) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.treatment = treatment;
        this.status = AppointmentStatus.BOOKED; // Temporary until booked
    }

    public void book(Patient patient) {
        this.patient = patient;
        this.status = AppointmentStatus.BOOKED;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    public void attend() {
        this.status = AppointmentStatus.ATTENDED;
    }

    public boolean isAvailable() {
        return this.patient == null && this.status != AppointmentStatus.CANCELLED;
    }

    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Treatment getTreatment() { return treatment; }
    public Patient getPatient() { return patient; }
    public AppointmentStatus getStatus() { return status; }
}