module com.example.sp4 {
    requires javafx.controls;
    requires javafx.fxml;
    
    
    opens com.example.sp4 to javafx.fxml;
    exports com.example.sp4;
}