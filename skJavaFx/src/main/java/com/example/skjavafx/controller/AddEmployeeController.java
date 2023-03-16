package com.example.skjavafx.controller;

import com.example.skjavafx.dto.EmployeeDto;
import com.example.skjavafx.factory.PopupFactory;
import com.example.skjavafx.rest.EmployeeRestClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    private final PopupFactory popupFactory;
    private final EmployeeRestClient employeeRestClient;

    @FXML
    private BorderPane addEmployeeBorderPane;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private Button saveButton;

    public AddEmployeeController() {
        popupFactory = new PopupFactory();
        employeeRestClient = new EmployeeRestClient();
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        initializeCancelButton();
        initializeSaveButton();

    }

    private void initializeSaveButton() {
        saveButton.setOnAction(x -> {
            EmployeeDto dto = createEmployeeDto();
            Stage waitingPopup = popupFactory.createWaitingPopup("Connecting to the server...");
            waitingPopup.show();
            employeeRestClient.saveEmployee(dto, () -> {
                waitingPopup.close();
            });

        });
    }

    private EmployeeDto createEmployeeDto() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String salary = salaryTextField.getText();
        EmployeeDto dto = new EmployeeDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setSalary(salary);
        return dto;



    }

    private void initializeCancelButton() {
        cancelButton.setOnAction((x) -> {
            getStage().close();

        });
    }
    private Stage getStage() {
        return (Stage) addEmployeeBorderPane.getScene().getWindow();
    }
}

