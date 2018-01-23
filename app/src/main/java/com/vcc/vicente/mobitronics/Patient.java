package com.vcc.vicente.mobitronics;

import java.util.GregorianCalendar;

public class Patient {

    enum Gender {
        Feminino,
        Masculino
    }

    private long id;
    private String name;
    private GregorianCalendar dateOfBirth;
    private GregorianCalendar dateOfLastTherapy = new GregorianCalendar(); // Holds time of construction
    private Gender gender;
    private long therapyCount = 0;

    public Patient(long id, String name, GregorianCalendar dateOfBirth, Gender gender) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public GregorianCalendar getDateOfLastTherapy() {
        return dateOfLastTherapy;
    }

    public void setDateOfLastTherapy(GregorianCalendar dateOfLastTherapy) {
        this.dateOfLastTherapy = dateOfLastTherapy;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getTherapyCount() {
        return therapyCount;
    }

    public void setTherapyCount(long therapyCount) {
        this.therapyCount = therapyCount;
    }
}
