package com.naveendotme.educationmanager.controller;

import com.naveendotme.educationmanager.model.User;
import db.Database;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpFormController {

    public TextField txtFirstName;
    public PasswordField txtPassword;
    public TextField txtLastName;
    public TextField txtEmail;

    public AnchorPane context;

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().toLowerCase();
        String firstname = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String password = txtPassword.getText().trim();

        try {
            boolean isSaved = signup(new User(firstname, lastName, email, password));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Welcome").show();
                setUi("LoginForm");
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException | ClassNotFoundException exception) {
            new Alert(Alert.AlertType.WARNING, exception.toString()).show();
        }
    }

    public void backToLoginOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    private boolean signup(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO user VALUES (?,?,?,?)";
        //Step 04 : Create Statement
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,user.getEmail());
        statement.setString(2,user.getFirstName());
        statement.setString(3,user.getLastName());
        statement.setString(4,user.getPassword());
        //Step 05 : Set SQL into the Statement
        return statement.executeUpdate() > 0;
        //return rowCount>0;
    }
}
