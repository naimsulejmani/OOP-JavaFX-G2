package com.example.oopjavafxg2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private CheckBox chbSave;

    public void login() {
        System.out.printf("%s => %s %b %n",
                txtUserName.getText(),
                txtPassword.getText(),
                chbSave.isSelected()
        );
    }

    public void cancel() {
        System.exit(0);
    }

}
