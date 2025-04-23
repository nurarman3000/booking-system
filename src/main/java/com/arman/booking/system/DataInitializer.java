package com.arman.booking.system;

import java.time.LocalDateTime;

/**
 *
 * @author NurArman
 */
class DataInitializer {
    public static Clinic initializeClinic() {
        Clinic clinic = new Clinic();

        // Add physiotherapists and skills
        Physiotherapist pt1 = new Physiotherapist("P001", "Dr. Kevin", "1 Street", "1234");
        pt1.addSkill("Massage");
        pt1.addSkill("Physiotherapy");

        Physiotherapist pt2 = new Physiotherapist("P002", "Dr. Sara", "2 Avenue", "2345");
        pt2.addSkill("Osteopathy");
        pt2.addSkill("Massage");

        Physiotherapist pt3 = new Physiotherapist("P003", "Dr. John", "2 Lake road", "2355");
        pt3.addSkill("Electrotherapy");
        pt3.addSkill("Massage");
        
        // Create treatments and appointments
        Treatment t1 = new Treatment("Massage", "Massage", pt1);
        addAppointmentsForTreatment(t1);
        pt1.addTreatment(t1);

        Treatment t2 = new Treatment("Massage", "Massage", pt2);
        addAppointmentsForTreatment(t2);
        pt2.addTreatment(t2);

        Treatment t3 = new Treatment("Electrotherapy", "Electrotherapy", pt3);
        addAppointmentsForTreatment(t3);
        pt3.addTreatment(t3);

        // Registering physio to Clinic
        clinic.addPhysiotherapist(pt1);
        clinic.addPhysiotherapist(pt2);
        clinic.addPhysiotherapist(pt3);

        // Add patients
        for (int i = 1; i <= 10; i++) {
            Patient patient = new Patient("PT00" + i, "Patient " + i, i + " Demo Road", "555-000" + i);
            clinic.addPatient(patient);
        }

        return clinic;
    }

    private static void addAppointmentsForTreatment(Treatment t) {
        for (int i = 0; i < 4; i++) {
            LocalDateTime start = LocalDateTime.of(2025, 5, (i+1) + i * 7, 10 + i, 0);
            Appointment appt = new Appointment(start, start.plusHours(1), t);
            t.addAppointment(appt);
        }
    }
}
