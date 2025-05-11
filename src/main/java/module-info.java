module com.example.hospital_info_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.hospital_info_system to javafx.fxml;
    opens com.example.hospital_info_system.model to javafx.base, javafx.fxml;
    exports com.example.hospital_info_system;
    exports com.example.hospital_info_system.controller;
    opens com.example.hospital_info_system.controller to javafx.fxml;
    exports com.example.hospital_info_system.model;
}