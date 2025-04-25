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
        return treatments.stream()
                .flatMap(t -> t.getAppointments().stream())
                .collect(Collectors.toList());
    }
}
