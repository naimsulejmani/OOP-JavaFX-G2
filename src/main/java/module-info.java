module com.example.oopjavafxg2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.oopjavafxg2 to javafx.fxml;
    exports com.example.oopjavafxg2;
}