module ha.hospitalapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;

    opens ha.hospitalapplication to javafx.fxml;

    exports ha.hospitalapplication;
}
