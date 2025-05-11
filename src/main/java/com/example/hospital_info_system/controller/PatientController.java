package com.example.hospital_info_system.controller;

import com.example.hospital_info_system.model.Patient;
import com.example.hospital_info_system.service.PatientService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PatientController {
    private PatientService patientService;
    private ObservableList<Patient> patientList;

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Patient> patientTableView;
    @FXML
    private TableColumn<Patient, Integer> idColumn;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    @FXML
    private TableColumn<Patient, String> surnameColumn;
    @FXML
    private TableColumn<Patient, String> addressColumn;
    @FXML
    private TableColumn<Patient, String> telephoneColumn;
    @FXML
    private TableColumn<Patient, Void> actionsColumn;

    public void initialize() {
        try {
            Connection connection = com.example.hospital_info_system.config.DatabaseConfig.getConnection();
            patientService = new PatientService(connection);
            setupTableColumns();
            loadPatients();
            setupSearch();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Failed to connect to the database: " + e.getMessage());
        }
    }

    private void setupTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("patientNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
        
        // Setup the action column with edit and delete buttons
        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                return new TableCell<>() {
                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");
                    private final HBox pane = new HBox(5, editButton, deleteButton);

                    {
                        editButton.getStyleClass().add("edit-button");
                        deleteButton.getStyleClass().add("delete-button");
                        
                        editButton.setOnAction(event -> {
                            Patient patient = getTableView().getItems().get(getIndex());
                            showEditDialog(patient);
                        });
                        
                        deleteButton.setOnAction(event -> {
                            Patient patient = getTableView().getItems().get(getIndex());
                            showDeleteConfirmation(patient);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                        }
                    }
                };
            }
        };
        
        actionsColumn.setCellFactory(cellFactory);
    }

    private void setupSearch() {
        if (searchField != null && patientList != null) {
            FilteredList<Patient> filteredData = new FilteredList<>(patientList, p -> true);
            
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(patient -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (patient.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getSurname().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (patient.getTelephoneNumber().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            
            patientTableView.setItems(filteredData);
        }
    }

    @FXML
    private void addPatient() {
        try {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String address = addressField.getText().trim();
            String telephone = telephoneField.getText().trim();
            
            // Validate input fields
            if (name.isEmpty()) {
                showAlert("Validation Error", "Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            
            if (surname.isEmpty()) {
                showAlert("Validation Error", "Surname cannot be empty");
                surnameField.requestFocus();
                return;
            }
            
            if (address.isEmpty()) {
                showAlert("Validation Error", "Address cannot be empty");
                addressField.requestFocus();
                return;
            }
            
            if (telephone.isEmpty()) {
                showAlert("Validation Error", "Telephone number cannot be empty");
                telephoneField.requestFocus();
                return;
            }
            
            if (!telephone.matches("^[0-9\\-\\+\\s]+$")) {
                showAlert("Validation Error", "Telephone number must contain only digits, spaces, plus, or minus signs");
                telephoneField.requestFocus();
                return;
            }

            Patient patient = new Patient(0, name, surname, address, telephone);
            patientService.addPatient(patient);
            clearFields();
            loadPatients();
            
            showAlert(Alert.AlertType.INFORMATION, "Success", "Patient added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add patient: " + e.getMessage());
        }
    }
    
    @FXML
    private void clearFields() {
        nameField.clear();
        surnameField.clear();
        addressField.clear();
        telephoneField.clear();
        nameField.requestFocus();
    }

    private void loadPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            patientList = FXCollections.observableArrayList(patients);
            patientTableView.setItems(patientList);
            
            if (searchField != null && !searchField.getText().isEmpty()) {
                setupSearch();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load patients: " + e.getMessage());
        }
    }
    
    private void showEditDialog(Patient patient) {
        Dialog<Patient> dialog = new Dialog<>();
        dialog.setTitle("Edit Patient");
        dialog.setHeaderText("Edit Patient Information");
        
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField nameField = new TextField(patient.getName());
        TextField surnameField = new TextField(patient.getSurname());
        TextField addressField = new TextField(patient.getAddress());
        TextField telephoneField = new TextField(patient.getTelephoneNumber());
        
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Surname:"), 0, 1);
        grid.add(surnameField, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(addressField, 1, 2);
        grid.add(new Label("Telephone:"), 0, 3);
        grid.add(telephoneField, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        // Request focus on the name field by default
        nameField.requestFocus();
        
        // Convert the result to a patient when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String name = nameField.getText().trim();
                String surname = surnameField.getText().trim();
                String address = addressField.getText().trim();
                String telephone = telephoneField.getText().trim();
                
                // Validate input fields
                if (name.isEmpty()) {
                    showAlert("Validation Error", "Name cannot be empty");
                    return null;
                }
                
                if (surname.isEmpty()) {
                    showAlert("Validation Error", "Surname cannot be empty");
                    return null;
                }
                
                if (address.isEmpty()) {
                    showAlert("Validation Error", "Address cannot be empty");
                    return null;
                }
                
                if (telephone.isEmpty()) {
                    showAlert("Validation Error", "Telephone number cannot be empty");
                    return null;
                }
                
                if (!telephone.matches("^[0-9\\-\\+\\s]+$")) {
                    showAlert("Validation Error", "Telephone number must contain only digits, spaces, plus, or minus signs");
                    return null;
                }
                
                return new Patient(patient.getPatientNumber(), name, surname, address, telephone);
            }
            return null;
        });
        
        Optional<Patient> result = dialog.showAndWait();
        
        result.ifPresent(updatedPatient -> {
            try {
                patientService.updatePatient(updatedPatient);
                loadPatients();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Patient updated successfully");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to update patient: " + e.getMessage());
            }
        });
    }
    
    private void showDeleteConfirmation(Patient patient) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Patient");
        alert.setHeaderText("Delete Patient Record");
        alert.setContentText("Are you sure you want to delete patient: " + patient.getName() + " " + patient.getSurname() + "?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                patientService.deletePatient(patient.getPatientNumber());
                loadPatients();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Patient deleted successfully");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to delete patient: " + e.getMessage());
            }
        }
    }
    
    private void editPatient() {
        Patient selectedPatient = patientTableView.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showAlert("Error", "Please select a patient to edit");
            return;
        }
        
        showEditDialog(selectedPatient);
    }
    
    private void showAlert(String title, String content) {
        showAlert(Alert.AlertType.ERROR, title, content);
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
