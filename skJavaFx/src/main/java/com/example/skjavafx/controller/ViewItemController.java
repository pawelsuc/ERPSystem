package com.example.skjavafx.controller;

import com.example.skjavafx.dto.ItemDto;
import com.example.skjavafx.rest.ItemRestClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewItemController implements Initializable {
    @FXML
    private TextField nameTextField;

    @FXML
    private Button okButton;

    @FXML
    private ComboBox<String> quantityTypeComboBox;

    @FXML
    private TextField quatityTextField;

    @FXML
    private BorderPane viewItemBorderPane;

    private final ItemRestClient itemRestClient;

    public ViewItemController() {
        itemRestClient = new ItemRestClient();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeControls();
        initializeOkButton();

    }

    public void loadItemData(Long idItem) {
        Thread thread = new Thread(() -> {
            ItemDto itemDto = itemRestClient.getItem(idItem);
            Platform.runLater(() -> {
                nameTextField.setText(itemDto.getName());
                quatityTextField.setText(itemDto.getQuantity().toString());
                quantityTypeComboBox.setItems(FXCollections.observableArrayList(itemDto.getQuantityType()));
                quantityTypeComboBox.getSelectionModel().select(0);

            });

        });
        thread.start();

    }


    private void initializeControls() {
        nameTextField.setEditable(false);
        quatityTextField.setEditable(false);
        quantityTypeComboBox.setEditable(false);

    }

    private void initializeOkButton() {
        okButton.setOnAction(x -> {
            getStage().close();
        });
    }

    private Stage getStage() {
        return (Stage) viewItemBorderPane.getScene().getWindow();
    }

}
