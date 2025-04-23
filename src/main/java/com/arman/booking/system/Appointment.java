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
}