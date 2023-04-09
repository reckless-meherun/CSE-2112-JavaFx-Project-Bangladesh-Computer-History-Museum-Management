module application.museum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens application.museum to javafx.fxml;
    exports application.museum;
}