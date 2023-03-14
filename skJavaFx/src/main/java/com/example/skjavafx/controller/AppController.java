package com.example.skjavafx.controller;

import com.example.skjavafx.dto.EmployeeDto;
import com.example.skjavafx.rest.EmployeeRestClient;
import com.example.skjavafx.table.EmployeeTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AppController implements Initializable {

    private final EmployeeRestClient employeeRestClient;

    public AppController() {
        employeeRestClient = new EmployeeRestClient();
    }

    @FXML
    private TableView<EmployeeTableModel> employeeTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("firstName"));

        TableColumn lastNameColumn = new TableColumn("Last Name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("lastName"));

        TableColumn salaryColumn = new TableColumn("Salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<EmployeeTableModel, String>("salary"));

        employeeTableView.getColumns().addAll(firstNameColumn,lastNameColumn,salaryColumn);

        ObservableList<EmployeeTableModel> data = FXCollections.observableArrayList();

        loadEmployeeData(data);

        employeeTableView.setItems(data);
    }

    private void loadEmployeeData(ObservableList<EmployeeTableModel> data) {
        Thread thread = new Thread(() -> {
            List<EmployeeDto> employees = employeeRestClient.getEmployees();
           data.addAll( employees.stream().map(EmployeeTableModel::of).collect(Collectors.toList()));
        });
        thread.start();
    }

}
