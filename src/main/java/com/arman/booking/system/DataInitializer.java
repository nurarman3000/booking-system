package com.arman.booking.system;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NurArman
 */
class DataInitializer {
    public static Clinic initializeClinic() {
        Clinic clinic = new Clinic();

        List<Physiotherapist> physiotherapists = Arrays.asList(
                new Physiotherapist("P001", "Dr. Kevin", "1 Street", "1234", Arrays.asList("Massage", "Physiotherapy")),
                new Physiotherapist("P002", "Dr. Sara", "2 Avenue", "2345", Arrays.asList("Osteopathy", "Massage")),
                new Physiotherapist("P003", "Dr. John", "2 Lake road", "2355",
                        Arrays.asList("Electrotherapy", "Massage")));

        List<Treatment> treatments = Arrays.asList(
                new Treatment("Massage", "Massage", physiotherapists.get(0)), // Dr. Kevin
                new Treatment("Massage", "Massage", physiotherapists.get(1)), // Dr. Sara
                new Treatment("Electrotherapy", "Electrotherapy", physiotherapists.get(2)) // Dr. John
        );

        for (int i = 0; i < treatments.size(); i++) {
            Treatment treatment = treatments.get(i);
            addAppointmentsForTreatment(treatment);
            physiotherapists.get(i).addTreatment(treatment);
        }

        // Registering physio to Clinic
        for (int i = 0; i < physiotherapists.size(); i++) {
            clinic.addPhysiotherapist(physiotherapists.get(i));
        }

        // Add patients
        for (int i = 1; i <= 15; i++) {
            Patient patient = new Patient("PT00" + i, "Patient " + i, i + " Demo Road", "555-000" + i);
            clinic.addPatient(patient);
        }

        return clinic;
    }

    private static void addAppointmentsForTreatment(Treatment t) {
        // Start from today's date, at 10:00 AM
        LocalDateTime startDate = LocalDateTime.now().withHour(10).withMinute(0).withSecond(0).withNano(0);

        for (int i = 0; i < 4; i++) { // 4 weeks of appointments (30 days will cover 4 weeks roughly)
            // Set the appointment time for each week (adding 7 days per week)
            LocalDateTime appointmentDate = startDate.plusDays(i * 7); // Shift by a week for each iteration

            // Create 5 appointments per day (one per hour)
            for (int j = 0; j < 5; j++) { // 5 appointments per day
                // Each appointment time will be one hour after
                LocalDateTime appointmentSlot = appointmentDate.plusHours(j);
                Appointment appt = new Appointment(appointmentSlot, appointmentSlot.plusHours(1), t);
                t.addAppointment(appt);
            }
        }
    }
}
