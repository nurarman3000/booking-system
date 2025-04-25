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
        this.status = AppointmentStatus.AVAILABLE;
    }

    public void book(Patient patient) {
        this.patient = patient;
        this.status = AppointmentStatus.BOOKED;
    }

    public void cancel() {
        if (this.status == AppointmentStatus.ATTENDED) {
            System.out.println("Cannot cancel an attended appointment.");
            return; 
        }

        this.patient = null;
        if (this.startTime.isAfter(LocalDateTime.now())) {
            this.status = AppointmentStatus.AVAILABLE;
        } else {
            this.status = AppointmentStatus.CANCELLED;
        }
    }

    public void attend() {
        this.status = AppointmentStatus.ATTENDED;
    }

    public boolean isAvailable() {
        return this.patient == null
            && this.status == AppointmentStatus.AVAILABLE
            && this.startTime.isAfter(LocalDateTime.now());
    }

    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Treatment getTreatment() { return treatment; }
    public Patient getPatient() { return patient; }
    public AppointmentStatus getStatus() { return status; }
}