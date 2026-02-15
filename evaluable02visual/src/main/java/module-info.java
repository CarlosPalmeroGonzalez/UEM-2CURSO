module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens main to javafx.fxml;
    opens controller to javafx.fxml;
    exports main;
}
