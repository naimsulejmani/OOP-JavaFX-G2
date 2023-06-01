package com.example.oopjavafxg2.controllers;

import com.example.oopjavafxg2.models.User;
import com.example.oopjavafxg2.repositories.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;

public class UserController {

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
    @FXML
    private Button btnDelete;

    @FXML
    private ListView<User> lvUsers;

    @FXML
    private TableView<User> tvUsers;


    private List<User> userList;
    private ObservableList<User> userObservableList;
    private UserRepository repository;
    private User selectedUser = null;


    public void initialize() {
        repository = new UserRepository();
        userObservableList = FXCollections.observableArrayList();
        initializeTableView();
        refresh();
    }

    private void initializeTableView() {
        TableColumn<User, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> surnameCol = new TableColumn<>("Surname");
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, LocalDate> birthdateCol = new TableColumn<>("Birthdate");
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

        TableColumn<User, Boolean> acceptedCol = new TableColumn<>("Accepted");
        acceptedCol.setCellValueFactory(new PropertyValueFactory<>("accepted"));

        tvUsers.getColumns().addAll(idCol, nameCol, surnameCol, usernameCol, birthdateCol, acceptedCol);

        tvUsers.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                User user = tvUsers.getSelectionModel().getSelectedItem();
                if (user != null) {
                    txtName.setText(user.getName());
                    txtSurname.setText(user.getSurname());
                    txtUserName.setText(user.getUsername());
                    dpBirthdate.setValue(user.getBirthdate());
                    txtPassword.setText(user.getPassword());
                    txtConfirmPassword.setText(user.getPassword());
                    chbAccept.setSelected(user.isAccepted());
                    if (user.isAccepted()) {
                        btnOK.setDisable(false);
                    }
                    btnDelete.setVisible(true);
                    selectedUser = user;
                }
            }
        });
    }

    private void refresh() {
        userList = repository.findAll();
        userObservableList.clear();
        userObservableList.addAll(userList);
        lvUsers.setItems(userObservableList);
        tvUsers.setItems(userObservableList);
    }


    public void cancel() {
        System.exit(0);
    }

    public void reset() {
        txtName.setText("");
        txtSurname.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtConfirmPassword.setStyle("");
        txtUserName.setText("");
        dpBirthdate.setValue(null);
        chbAccept.setSelected(false);
        btnOK.setDisable(true);
        btnDelete.setVisible(false);
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
        boolean registered = false;

        if (selectedUser == null)
            registered = repository.add(user);
        else registered = repository.modify(selectedUser.getId(), user);


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
            refresh();
            reset();
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

    public void delete(ActionEvent actionEvent) {
        if (selectedUser != null) {
            repository.remove(selectedUser.getId());
            refresh();
            reset();
        }
    }
}
