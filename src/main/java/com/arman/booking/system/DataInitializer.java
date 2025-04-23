package com.arman.booking.system;


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

        // Add patients
        for (int i = 1; i <= 10; i++) {
            Patient patient = new Patient("PT00" + i, "Patient " + i, i + " Demo Road", "555-000" + i);
            clinic.addPatient(patient);
        }

        return clinic;
    }
}
