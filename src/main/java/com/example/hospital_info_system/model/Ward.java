package com.example.hospital_info_system.model;

public class Ward {
    private String wardNumber;
    private String departmentCode;
    private int numberOfBeds;
    private int supervisorEmployeeNumber;

    public Ward(String wardNumber, String departmentCode, int numberOfBeds, int supervisorEmployeeNumber) {
        this.wardNumber = wardNumber;
        this.departmentCode = departmentCode;
        this.numberOfBeds = numberOfBeds;
        this.supervisorEmployeeNumber = supervisorEmployeeNumber;
    }

    // Getters and Setters
    public String getWardNumber() { return wardNumber; }
    public void setWardNumber(String wardNumber) { this.wardNumber = wardNumber; }
    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }
    public int getNumberOfBeds() { return numberOfBeds; }
    public void setNumberOfBeds(int numberOfBeds) { this.numberOfBeds = numberOfBeds; }
    public int getSupervisorEmployeeNumber() { return supervisorEmployeeNumber; }
    public void setSupervisorEmployeeNumber(int supervisorEmployeeNumber) { this.supervisorEmployeeNumber = supervisorEmployeeNumber; }
}
