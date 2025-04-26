package com.arman.booking.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingSystemTests {

    private Clinic clinic;

    @BeforeEach
    public void setUp() {
        clinic = DataInitializer.initializeClinic();
    }

    @Test
    public void testAddAndRemovePatient() {
        Patient newPatient = new Patient("PT011", "New Patient", "11 Sample St", "555-0011");
        clinic.addPatient(newPatient);
        assertTrue(clinic.getPatients().contains(newPatient));

        clinic.removePatient("PT011");
        assertFalse(clinic.getPatients().contains(newPatient));
    }

    @Test
    public void testRemovePatientUsingClinicUtils() {
        Patient testPatient = new Patient("PT999", "Temp Patient", "99 Test Rd", "555-9999");
        clinic.addPatient(testPatient);

        Physiotherapist pt = clinic.getPhysiotherapists().get(0);
        Appointment appt = pt.getAvailableAppointments().get(0);
        clinic.bookAppointment(testPatient, appt);

        String simulatedInput = "PT999\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ClinicUtil.removePatient(clinic);

        System.setIn(originalIn);

        assertFalse(clinic.getPatients().contains(testPatient));
        assertNull(appt.getPatient());
        assertEquals(AppointmentStatus.AVAILABLE, appt.getStatus());
    }

    @Test
    public void testSearchByExpertise() {
        List<Physiotherapist> massageExperts = clinic.searchByExpertise("Massage");
        assertEquals(2, massageExperts.size());
    }

    @Test
    public void testSearchByPhysiotherapistName() {
        Physiotherapist found = clinic.searchByName("Dr. Sara");
        assertNotNull(found);
        assertEquals("P002", found.getId());
    }

    @Test
    public void testBookAppointment() {
        Patient patient = clinic.getPatients().get(0);
        Physiotherapist pt = clinic.searchByName("Dr. Kevin");
        Appointment appt = pt.getAvailableAppointments().get(0);

        clinic.bookAppointment(patient, appt);

        assertEquals(patient, appt.getPatient());
        assertEquals(AppointmentStatus.BOOKED, appt.getStatus());
    }

    @Test
    public void testCancelAppointment() {
        Patient patient = clinic.getPatients().get(1);
        Physiotherapist pt = clinic.searchByName("Dr. Kevin");
        Appointment appt = pt.getAvailableAppointments().get(0);
        clinic.bookAppointment(patient, appt);

        clinic.cancelAppointment(appt);
        assertEquals(AppointmentStatus.AVAILABLE, appt.getStatus());
    }

    @Test
    public void testCannotCancelAttendedAppointment() {
        Patient patient = clinic.getPatients().get(2);
        Physiotherapist pt = clinic.getPhysiotherapists().get(0);
        Appointment appt = pt.getAvailableAppointments().get(0);
        clinic.bookAppointment(patient, appt);
        appt.attend();

        clinic.cancelAppointment(appt);
        assertEquals(AppointmentStatus.ATTENDED, appt.getStatus(), "Attended appointments should not be cancelled");
    }

    @Test
    public void testAppointmentAvailability() {
        Physiotherapist pt = clinic.searchByName("Dr. Kevin");
        Appointment appt = pt.getAvailableAppointments().get(0);
        assertTrue(appt.isAvailable());

        clinic.bookAppointment(clinic.getPatients().get(0), appt);
        assertFalse(appt.isAvailable());
    }

    @Test
    public void testRebookCancelledAppointmentIfInFuture() {
        Patient p1 = clinic.getPatients().get(0);
        Patient p2 = clinic.getPatients().get(1);
        Physiotherapist pt = clinic.getPhysiotherapists().get(0);

        Appointment appt = pt.getAvailableAppointments().get(0);

        clinic.bookAppointment(p1, appt);
        clinic.cancelAppointment(appt);

        // Attempt to rebook with a different patient
        clinic.bookAppointment(p2, appt);
        assertEquals(AppointmentStatus.BOOKED, appt.getStatus());
        assertEquals(p2, appt.getPatient());
    }

    @Test
    public void testAttendAppointment() {
        Patient patient = clinic.getPatients().get(2);
        Physiotherapist pt = clinic.getPhysiotherapists().get(0);

        Appointment appt = pt.getAvailableAppointments().get(0);
        clinic.bookAppointment(patient, appt);
        appt.attend();
        assertEquals(AppointmentStatus.ATTENDED, appt.getStatus());
    }

    @Test
    public void testIsAvailableLogicWithFutureTime() {
        Physiotherapist pt = clinic.getPhysiotherapists().get(0);
        Appointment appt = pt.getAvailableAppointments().get(0);
        assertTrue(appt.isAvailable());

        // Cancel it and make sure it's still bookable
        clinic.bookAppointment(clinic.getPatients().get(0), appt);
        clinic.cancelAppointment(appt);
        assertTrue(appt.isAvailable());
    }

    @Test
    public void testIsAvailableFalseIfPastTime() {
        Appointment appt = new Appointment(LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1).plusHours(1));
        appt.cancel();
        assertFalse(appt.isAvailable());
    }

}
