package com.example.hospital_info_system.model;

public class Employee {
    private int employeeNumber;
    private String surname;
    private String firstName;
    private String address;
    private String telephoneNumber;
    private String employeeType;

    public Employee(int employeeNumber, String surname, String firstName, String address, String telephoneNumber, String employeeType) {
        this.employeeNumber = employeeNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.employeeType = employeeType;
    }

    // Getters and Setters
    public int getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(int employeeNumber) { this.employeeNumber = employeeNumber; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }
    public String getEmployeeType() { return employeeType; }
    public void setEmployeeType(String employeeType) { this.employeeType = employeeType; }
}
