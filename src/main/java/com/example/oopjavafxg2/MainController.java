package com.example.oopjavafxg2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    public void cancel() {
        System.exit(0);
    }

    public void reset(ActionEvent actionEvent) {

    }

    public void register(ActionEvent actionEvent) {
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
