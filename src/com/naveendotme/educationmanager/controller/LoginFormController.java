package com.naveendotme.educationmanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {
    public TextField txtEmail;
    public PasswordField txtPassword;
    public AnchorPane context;

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignUpForm");
    }

    public void loginOnAction(ActionEvent actionEvent) {

    }

    public void forgotPwOnAction(ActionEvent actionEvent) {
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}
