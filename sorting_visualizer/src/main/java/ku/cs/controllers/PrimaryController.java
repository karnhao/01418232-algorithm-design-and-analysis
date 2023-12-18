package ku.cs.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import ku.cs.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
