package com.arman.booking.system;

/**
 *
 * @author NurArman
 */
abstract class Member {
    protected String id;
    protected String fullName;
    protected String address;
    protected String phoneNumber;

    public Member(String id, String fullName, String address, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
}