module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires transitive javafx.graphics;

    opens ku.cs to javafx.fxml;
    exports ku.cs;

    opens ku.cs.controllers;
    exports ku.cs.controllers;
}
