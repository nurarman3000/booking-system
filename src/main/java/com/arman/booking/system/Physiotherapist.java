package com.arman.booking.system;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NurArman
 */
public class Physiotherapist extends Member {

    private final List<String> skill = new ArrayList<>();

    public Physiotherapist(String id, String fullName, String address, String phoneNumber) {
        super(id, fullName, address, phoneNumber);
    }

    public void addSkill(String area) {
        if (!skill.contains(area)) {
            skill.add(area);
        }
    }

    public List<String> getSkills() {
        return skill;
    }
}
