package com.example.hospital_info_system.dao;

import com.example.hospital_info_system.model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.hospital_info_system.config.DatabaseConfig;

public class PatientDAO implements DAO<Patient> {
    private Connection connection;

    public PatientDAO() throws SQLException {
        this.connection = DatabaseConfig.getConnection();
    }

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Patient patient) {
        String sql = "INSERT INTO PATIENT (Name, Surname, Address, TelephoneNumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, patient.getName());
            pstmt.setString(2, patient.getSurname());
            pstmt.setString(3, patient.getAddress());
            pstmt.setString(4, patient.getTelephoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient read(int id) {
        String sql = "SELECT * FROM PATIENT WHERE PatientNumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt("PatientNumber"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"), rs.getString("TelephoneNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Patient patient) {
        String sql = "UPDATE PATIENT SET Name = ?, Surname = ?, Address = ?, TelephoneNumber = ? WHERE PatientNumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, patient.getName());
            pstmt.setString(2, patient.getSurname());
            pstmt.setString(3, patient.getAddress());
            pstmt.setString(4, patient.getTelephoneNumber());
            pstmt.setInt(5, patient.getPatientNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM PATIENT WHERE PatientNumber = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM PATIENT";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                patients.add(new Patient(rs.getInt("PatientNumber"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"), rs.getString("TelephoneNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
}
