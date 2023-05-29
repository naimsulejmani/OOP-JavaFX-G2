module com.example.oopjavafxg2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;


    opens com.example.oopjavafxg2 to javafx.fxml;
    exports com.example.oopjavafxg2;
    exports com.example.oopjavafxg2.controllers;
    opens com.example.oopjavafxg2.controllers to javafx.fxml;
}