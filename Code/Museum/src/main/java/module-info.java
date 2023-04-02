module application.museum {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.museum to javafx.fxml;
    exports application.museum;
}