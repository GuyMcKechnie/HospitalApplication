module ha.hospitalapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ha.hospitalapplication to javafx.fxml;
    exports ha.hospitalapplication;
}
