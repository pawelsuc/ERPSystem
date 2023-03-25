package com.example.skjavafx.controller;

import com.example.skjavafx.dto.ItemSaveDto;
import com.example.skjavafx.dto.QuantityTypeDto;
import com.example.skjavafx.dto.WarehouseDto;
import com.example.skjavafx.handler.ProcessFinishedHandler;
import com.example.skjavafx.rest.ItemRestClient;
import com.example.skjavafx.rest.QuantityTypeRestClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {
    @FXML
    private BorderPane addItemBorderPane;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private ComboBox<QuantityTypeDto> quantityTypeComboBox;

    @FXML
    private TextField quatityTextField;

    @FXML
    private Button saveButton;

    private WarehouseDto selectedWarehouseDto;

    private final ItemRestClient itemRestClient;

    public AddItemController() {
        itemRestClient = new ItemRestClient();
        quantityTypeRestClient = new QuantityTypeRestClient();
    }

    private final QuantityTypeRestClient quantityTypeRestClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCancelButton();
        initializeSaveButton();

    }

    private void initializeSaveButton() {
        saveButton.setOnAction(x -> {
            String name = nameTextField.getText();
            double quantity = Double.parseDouble(quatityTextField.getText());
            Long idQuantityType = quantityTypeComboBox.getSelectionModel().getSelectedItem().getIdQuantityType();
            Long idWarehouse = selectedWarehouseDto.getIdWarehouse();
            ItemSaveDto dto = new ItemSaveDto(name, quantity, idQuantityType, idWarehouse);
            Thread thread = new Thread(() -> {
                itemRestClient.saveItem(dto, () ->{
                    getStage().close();
                });
            });
        });
    }

    private void initializeCancelButton() {
        cancelButton.setOnAction((x) -> {
            getStage().close();

        });
    }
    private Stage getStage() {
        return (Stage) addItemBorderPane.getScene().getWindow();
    }
}
