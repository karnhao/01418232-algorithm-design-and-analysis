module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires transitive javafx.graphics;

    opens ku.cs to javafx.fxml;
    exports ku.cs;

    opens ku.cs.controllers;
    exports ku.cs.controllers;

    opens ku.cs.models;
    exports ku.cs.models;

    opens ku.cs.services.utils;
    exports ku.cs.services.utils;
}
