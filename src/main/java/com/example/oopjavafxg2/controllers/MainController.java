package com.example.oopjavafxg2.controllers;

import com.example.oopjavafxg2.models.User;
import com.example.oopjavafxg2.repositories.UserRepository;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class MainController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private DatePicker dpBirthdate;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private CheckBox chbAccept;

    @FXML
    private Button btnOK;

    public void cancel() {
        System.exit(0);
    }

    public void reset(ActionEvent actionEvent) {

    }

    private boolean isSame(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    public void register(ActionEvent actionEvent) {

        if (!isSame(txtPassword.getText(), txtConfirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User registration");
            alert.setHeaderText("Failed!");
            alert.setContentText("Password didn't match! Please check and try again!");
            alert.show();

            return;
        }

        User user = new User(
                txtName.getText(),
                txtSurname.getText(),
                dpBirthdate.getValue(),
                txtUserName.getText(),
                txtPassword.getText(),
                chbAccept.isSelected()
        );
        UserRepository repository = new UserRepository();
        boolean registered = repository.add(user);
        if (registered) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User registration");
            alert.setHeaderText("Result of registration");
            alert.setContentText(
                    String.format("Name: %s%nSurname: %s%nBirthdate: %s%nUsername: %s%nTerms: %b%n",
                            txtName.getText(),
                            txtSurname.getText(),
                            dpBirthdate.getValue(),
                            txtUserName.getText(),
                            chbAccept.isSelected()
                    )
            );
            alert.show();
        }
    }

    public void changeBtnOKAccess(ActionEvent actionEvent) {
        btnOK.setDisable(!chbAccept.isSelected());
    }

    public void isValidPassword(KeyEvent keyEvent) {
        if (isSame(txtPassword.getText(), txtConfirmPassword.getText())) {
            txtConfirmPassword.setStyle("-fx-background-color: green;");
        } else {
            txtConfirmPassword.setStyle("-fx-background-color: red");
        }
    }
}
