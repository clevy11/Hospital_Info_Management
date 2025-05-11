package com.example.hospital_info_system.model;

import java.util.Date;

public class Hospitalization {
    private int hospitalizationID;
    private int patientNumber;
    private String wardNumber;
    private String departmentCode;
    private String diagnosis;
    private int treatingDoctorEmployeeNumber;
    private Date admissionDate;
    private Date dischargeDate;
    private boolean isCurrent;

    public Hospitalization(int hospitalizationID, int patientNumber, String wardNumber, String departmentCode, String diagnosis, int treatingDoctorEmployeeNumber, Date admissionDate, Date dischargeDate, boolean isCurrent) {
        this.hospitalizationID = hospitalizationID;
        this.patientNumber = patientNumber;
        this.wardNumber = wardNumber;
        this.departmentCode = departmentCode;
        this.diagnosis = diagnosis;
        this.treatingDoctorEmployeeNumber = treatingDoctorEmployeeNumber;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.isCurrent = isCurrent;
    }

    // Getters and Setters
    public int getHospitalizationID() { return hospitalizationID; }
    public void setHospitalizationID(int hospitalizationID) { this.hospitalizationID = hospitalizationID; }
    public int getPatientNumber() { return patientNumber; }
    public void setPatientNumber(int patientNumber) { this.patientNumber = patientNumber; }
    public String getWardNumber() { return wardNumber; }
    public void setWardNumber(String wardNumber) { this.wardNumber = wardNumber; }
    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public int getTreatingDoctorEmployeeNumber() { return treatingDoctorEmployeeNumber; }
    public void setTreatingDoctorEmployeeNumber(int treatingDoctorEmployeeNumber) { this.treatingDoctorEmployeeNumber = treatingDoctorEmployeeNumber; }
    public Date getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(Date admissionDate) { this.admissionDate = admissionDate; }
    public Date getDischargeDate() { return dischargeDate; }
    public void setDischargeDate(Date dischargeDate) { this.dischargeDate = dischargeDate; }
    public boolean isCurrent() { return isCurrent; }
    public void setCurrent(boolean current) { isCurrent = current; }
}
