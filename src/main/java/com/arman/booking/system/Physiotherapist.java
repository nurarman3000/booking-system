package com.arman.booking.system;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author NurArman
 */
public class Physiotherapist extends Member {

    private final List<String> skill = new ArrayList<>();
    private final List<Treatment> treatments = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    public Physiotherapist(String id, String fullName, String address, String phoneNumber, List<String> skills) {
        super(id, fullName, address, phoneNumber);
        this.skill.addAll(skills);
    }

    public void addSkill(String area) {
        if (!skill.contains(area)) {
            skill.add(area);
        }
    }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public List<String> getSkills() {
        return skill;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public List<Appointment> getAvailableAppointments() {
        return appointments.stream()
                .filter(Appointment::isAvailable)
                .collect(Collectors.toList());
    }

    public void addAppointment(Appointment appointment) {
        appointment.setPhysiotherapist(this);
        appointments.add(appointment);
    }
}
