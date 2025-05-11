package com.example.hospital_info_system.model;

public class Nurse extends Employee {
    private String rotation;
    private double salary;

    public Nurse(int employeeNumber, String surname, String firstName, String address, String telephoneNumber, String rotation, double salary) {
        super(employeeNumber, surname, firstName, address, telephoneNumber, "NURSE");
        this.rotation = rotation;
        this.salary = salary;
    }

    // Getters and Setters
    public String getRotation() { return rotation; }
    public void setRotation(String rotation) { this.rotation = rotation; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}
