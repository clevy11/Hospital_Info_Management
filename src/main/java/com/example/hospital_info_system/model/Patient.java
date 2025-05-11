package com.example.hospital_info_system.model;

public class Patient {
    private int patientNumber;
    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;

    public Patient(int patientNumber, String name, String surname, String address, String telephoneNumber) {
        this.patientNumber = patientNumber;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    // Getters and Setters
    public int getPatientNumber() { return patientNumber; }
    public void setPatientNumber(int patientNumber) { this.patientNumber = patientNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }
}