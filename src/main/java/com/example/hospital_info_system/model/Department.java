package com.example.hospital_info_system.model;

public class Department {
    private String code;
    private String name;
    private String building;
    private int directorEmployeeNumber;

    public Department(String code, String name, String building, int directorEmployeeNumber) {
        this.code = code;
        this.name = name;
        this.building = building;
        this.directorEmployeeNumber = directorEmployeeNumber;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
    public int getDirectorEmployeeNumber() { return directorEmployeeNumber; }
    public void setDirectorEmployeeNumber(int directorEmployeeNumber) { this.directorEmployeeNumber = directorEmployeeNumber; }
}
