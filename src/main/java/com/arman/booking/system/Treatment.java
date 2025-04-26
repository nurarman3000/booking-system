package com.arman.booking.system;

/**
 *
 * @author NurArman
 */
public class Treatment {
    private final String name;
    private final String expertiseArea;
    private final Physiotherapist physiotherapist;

    public Treatment(String name, String expertiseArea, Physiotherapist physiotherapist) {
        this.name = name;
        this.expertiseArea = expertiseArea;
        this.physiotherapist = physiotherapist;
    }

    public String getName() {
        return name;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
}
