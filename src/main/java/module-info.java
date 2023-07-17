module com.example.coursework_task_04 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.example.coursework_task_04 to javafx.fxml;
    exports com.example.coursework_task_04;
}