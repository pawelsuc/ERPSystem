package com.example.skjavafx.controller;

import com.example.skjavafx.factory.PopupFactory;
import com.example.skjavafx.dto.OperatorCredentialsDto;
import com.example.skjavafx.rest.Authenticator;
import com.example.skjavafx.rest.AuthenticatorImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final String APP_FXML = "/com/example/skjavafx/app.fxml";
    private static final String APP_TITLE = "ERP system";

    private PopupFactory popupFactory;
    private Authenticator authenticator;

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    public LoginController() {
        popupFactory = new PopupFactory();
        authenticator = new AuthenticatorImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeExitButton();
        initializeLoginButton();

    }

    private void initializeLoginButton() {
        loginButton.setOnAction((x) -> {
            performAuthentication();
        });
    }

    private void performAuthentication() {
        Stage waitingPopup = popupFactory.createWaitingPopup("Connecting to the server...");
        waitingPopup.show();
        String login = loginTextField.getText();
        String password = passwordTextField.getText();
        OperatorCredentialsDto dto = new OperatorCredentialsDto();
        dto.setLogin(login);
        dto.setPassword(password);
        authenticator.authenticate(dto, (authenticationResult) -> {
            Platform.runLater(() -> {
                waitingPopup.close();
                if(authenticationResult.isAuthenticated()) {
                    openAppAndCloseLoginStage();
                } else {
                    showIncorrectCredentialsMessage();

                }
            });
        });

    }

    private void showIncorrectCredentialsMessage() {
//        TODO
        System.out.println("Invalid credentials");
    }

    private void openAppAndCloseLoginStage() {
        Stage appStage = new Stage();
        Parent appRoot = null;

        try {
           appRoot = FXMLLoader.load(getClass().getResource(APP_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(appRoot, 1024, 768);
        appStage.setTitle(APP_TITLE);
        appStage.setScene(scene);
        appStage.show();
        getStage().close();
    }

    private void initializeExitButton() {
        exitButton.setOnAction((x) -> {
            getStage().close();
        });
    }


    private Stage getStage() {
        return (Stage)loginAnchorPane.getScene().getWindow();
    }
}