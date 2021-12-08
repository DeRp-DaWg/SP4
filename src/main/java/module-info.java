module com.example.sp4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sp4 to javafx.fxml;
    exports com.example.sp4;
    exports com.example.sp4.IO;
    opens com.example.sp4.IO to javafx.fxml;
    exports com.example.sp4.UI;
    opens com.example.sp4.UI to javafx.fxml;
    exports com.example.sp4.Question;
    opens com.example.sp4.Question to javafx.fxml;
    exports com.example.sp4.UI.JavaFX;
    opens com.example.sp4.UI.JavaFX to javafx.fxml;
    exports com.example.sp4.UI.CommandLine;
    opens com.example.sp4.UI.CommandLine to javafx.fxml;
}