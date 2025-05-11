package com.example.hospital_info_system.service;

import com.example.hospital_info_system.dao.PatientDAO;
import com.example.hospital_info_system.model.Patient;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PatientService {
    private PatientDAO patientDAO;

    public PatientService(Connection connection) throws SQLException {
        this.patientDAO = new PatientDAO(connection);
    }

    public void addPatient(Patient patient) {
        patientDAO.create(patient);
    }

    public Patient getPatient(int id) {
        return patientDAO.read(id);
    }

    public void updatePatient(Patient patient) {
        patientDAO.update(patient);
    }

    public void deletePatient(int id) {
        patientDAO.delete(id);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.findAll();
    }
}
