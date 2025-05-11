package com.example.hospital_info_system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/Hospital_Infomanagement_System";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "728728";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
