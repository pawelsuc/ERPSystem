package com.example.skjavafx.controller;

import com.example.skjavafx.dto.ItemDto;
import com.example.skjavafx.rest.ItemRestClient;
import com.example.skjavafx.table.ItemTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WarehouseController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button viewButton;

    @FXML
    private BorderPane warehouseBorderPane;

    @FXML
    private TableView<ItemTableModel> warehouseTableView;

    private final ItemRestClient itemRestClient;

    private ObservableList<ItemTableModel> data;


    public WarehouseController() {
        itemRestClient = new ItemRestClient();
        data = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableView();

    }

    private void initializeTableView() {
        warehouseTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<ItemTableModel, String>("name"));

        TableColumn quantityColumn = new TableColumn("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<ItemTableModel, Double>("quantity"));

        TableColumn quantityTypeColumn = new TableColumn("Quantity Type");
        quantityTypeColumn.setMinWidth(100);
        quantityTypeColumn.setCellValueFactory(new PropertyValueFactory<ItemTableModel, String>("quantityType"));

        warehouseTableView.getColumns().addAll(nameColumn, quantityColumn, quantityTypeColumn);
        warehouseTableView.setItems(data);
        loadItemData();
    }

    private void loadItemData() {
        Thread thread = new Thread(() -> {
            List<ItemDto> items = itemRestClient.getItems();
            data.clear();
            data.addAll(items.stream().map(ItemTableModel::of).collect(Collectors.toList()));
        });
        thread.start();

    }
}
