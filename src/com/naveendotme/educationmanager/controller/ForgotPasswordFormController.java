package com.naveendotme.educationmanager.controller;

import com.naveendotme.educationmanager.util.tools.VerificationCodeGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class ForgotPasswordFormController {
    public AnchorPane context;
    public TextField txtEmail;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void verificationCodeOnAction(ActionEvent actionEvent) {
        try {
            int verification = new VerificationCodeGenerator().getCode(5);
            String fromEmail = "naveenpabhath123@gmail.com";
            String toEmail = txtEmail.getText();
            String host = "localhost";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            // => node -> NodeMailer , SendGrid
            // => mobile -> Twilio

            Session session = Session.getDefaultInstance(properties);

            //----------------------------------------
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.setSubject("Verification Code");
            mimeMessage.setText("Verification Code : " + verification);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            Transport.send(mimeMessage);
            System.out.println("Completed");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}
