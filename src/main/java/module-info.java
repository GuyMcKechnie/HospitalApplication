module ha.hospitalapplication {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires transitive javafx.graphics;
    requires java.desktop;

    opens ha.hospitalapplication to javafx.fxml;

    exports ha.hospitalapplication;
}
