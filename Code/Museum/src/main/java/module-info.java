module application.museum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;

    opens application.museum to javafx.fxml;
    exports application.museum;
    exports application.museum.People;
    opens application.museum.People to javafx.fxml;
    opens application.museum.Departments to javafx.base;
}