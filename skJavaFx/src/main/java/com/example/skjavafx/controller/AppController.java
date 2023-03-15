package com.example.skjavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private Pane appPane;

    public AppController() {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialize");

    }

}
