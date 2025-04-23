package com.arman.booking.system;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author NurArman
 */
public class Treatment {
    private final String name;
    private final String expertiseArea;
    private final Physiotherapist physiotherapist;
    private final List<Appointment> appointments = new ArrayList<>();

    public Treatment(String name, String expertiseArea, Physiotherapist physiotherapist) {
        this.name = name;
        this.expertiseArea = expertiseArea;
        this.physiotherapist = physiotherapist;
    }

    public String getName() { return name; }
    public String getExpertiseArea() { return expertiseArea; }
    public Physiotherapist getPhysiotherapist() { return physiotherapist; }

    public List<Appointment> getAppointments() { return appointments; }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAvailableAppointments() {
        return appointments.stream().filter(Appointment::isAvailable).collect(Collectors.toList());
    }
}
