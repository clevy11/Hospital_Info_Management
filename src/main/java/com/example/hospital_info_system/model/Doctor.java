package com.example.hospital_info_system.model;

public class Doctor extends Employee {
    private String speciality;

    public Doctor(int employeeNumber, String surname, String firstName, String address, String telephoneNumber, String speciality) {
        super(employeeNumber, surname, firstName, address, telephoneNumber, "DOCTOR");
        this.speciality = speciality;
    }

    // Getter and Setter
    public String getSpeciality() { return speciality; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }
}
