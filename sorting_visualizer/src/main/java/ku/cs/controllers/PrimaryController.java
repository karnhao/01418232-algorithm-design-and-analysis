package ku.cs.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ku.cs.App;
import ku.cs.services.utils.Beep;

import javax.sound.sampled.LineUnavailableException;

public class PrimaryController {

    public Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void test1() {
        Beep.tone(523, 100, 0.125);
    }

    public void test2() {
        Beep.tone(587, 100, 0.125);
    }

    public void test3() {
        Beep.tone(659, 100, 0.125);
    }
}
