module com.example.skjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires spring.web;


    opens com.example.skjavafx to javafx.fxml;
    exports com.example.skjavafx;
    exports com.example.skjavafx.rest;
    opens com.example.skjavafx.rest to javafx.fxml;
    exports com.example.skjavafx.controller;
    opens com.example.skjavafx.controller to javafx.fxml;
    exports com.example.skjavafx.dto;
    opens com.example.skjavafx.dto to javafx.fxml;
    exports com.example.skjavafx.factory;
    opens com.example.skjavafx.factory to javafx.fxml;
    exports com.example.skjavafx.table;
    opens com.example.skjavafx.table to javafx.fxml;
    exports com.example.skjavafx.handler;
    opens com.example.skjavafx.handler to javafx.fxml;
}