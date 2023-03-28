package com.example.skjavafx.controller;

import com.example.skjavafx.dto.WarehouseDto;
import com.example.skjavafx.dto.WarehouseModuleDto;
import com.example.skjavafx.rest.ItemRestClient;
import com.example.skjavafx.rest.WarehouseRestClient;
import com.example.skjavafx.table.ItemTableModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WarehouseController implements Initializable {

    private static final String ADD_ITEM_FXML ="/com/example/skjavafx/add-item.fxml";
    private static final String VIEW_ITEM_FXML ="/com/example/skjavafx/view-item.fxml";
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
    private ComboBox<WarehouseDto> warehouseComboBox;

    @FXML
    private TableView<ItemTableModel> warehouseTableView;

    private final ItemRestClient itemRestClient;

    private final WarehouseRestClient warehouseRestClient;

    private ObservableList<ItemTableModel> data;


    public WarehouseController() {
        itemRestClient = new ItemRestClient();
        data = FXCollections.observableArrayList();
        warehouseRestClient = new WarehouseRestClient();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableView();
        initializeComboBox();
        initializeAddItemButton();
        initializeViewItemButton();

    }

    private void initializeViewItemButton() {
        viewButton.setOnAction(x-> {
            ItemTableModel selectedItem = warehouseTableView.getSelectionModel().getSelectedItem();
            if(selectedItem == null) {
                return;

            }
            try {
                Stage stage = createItemCrudStage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_ITEM_FXML));
                Scene scene = new Scene(loader.load(), 500, 400);
                stage.setScene(scene);
                ViewItemController controller = loader.getController();
                controller.loadItemData(selectedItem.getIdItem());
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initializeAddItemButton() {
        addButton.setOnAction(x-> {
            try {
                Stage stage = createItemCrudStage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ADD_ITEM_FXML));
                Scene scene = new Scene(loader.load(), 500, 400);
                stage.setScene(scene);
                AddItemController controller = loader.getController();
                WarehouseDto selectedWarehouseDto = warehouseComboBox.getSelectionModel().getSelectedItem();
                controller.setWarehouseDto(selectedWarehouseDto);
                controller.loadQuantityTypes();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Stage createItemCrudStage() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private void initializeComboBox() {
        warehouseComboBox.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue == null) {
                return;
            }
            if(!newValue.equals(oldValue) && oldValue != null) {
                WarehouseDto wareHouseDto = warehouseComboBox.getSelectionModel().getSelectedItem();
                loadItemData(wareHouseDto);
            }
        }));
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
            WarehouseModuleDto warehouseModuleDto = warehouseRestClient.getWarehouseModuleData();
            data.clear();
            setWarehouseComboBoxItems(warehouseModuleDto);
            data.addAll(warehouseModuleDto.getItemDtoList().stream().map(ItemTableModel::of).collect(Collectors.toList()));
        });
        thread.start();
    }

    private void loadItemData(WarehouseDto warehouseDto) {
        Thread thread = new Thread(() -> {
            WarehouseModuleDto warehouseModuleDto =
                    warehouseRestClient.getWarehouseModuleData(warehouseDto.getIdWarehouse());
            data.clear();
            setWarehouseComboBoxItems(warehouseModuleDto);
            data.addAll(warehouseModuleDto.getItemDtoList().stream().map(ItemTableModel::of).collect(Collectors.toList()));
        });
        thread.start();
    }

    private void setWarehouseComboBoxItems(WarehouseModuleDto warehouseModuleDto) {
        List<WarehouseDto> wareHouseDtoList = warehouseModuleDto.getWarehouseDtoList();
        ObservableList<WarehouseDto> warehouseComboBoxItems = FXCollections.observableArrayList();
        warehouseComboBoxItems.addAll(wareHouseDtoList);
        Platform.runLater(() -> {
            warehouseComboBox.setItems(warehouseComboBoxItems);
            warehouseComboBox.getSelectionModel().select(wareHouseDtoList.indexOf(warehouseModuleDto.getSelectedWarehouse()));
        });
    }
}
