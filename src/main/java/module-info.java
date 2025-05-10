module com.example.hospital_info_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.hospital_info_system to javafx.fxml;
    exports com.example.hospital_info_system;
}