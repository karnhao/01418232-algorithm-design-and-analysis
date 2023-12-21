package ku.cs.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ku.cs.App;
import ku.cs.models.algorithms.Check;
import ku.cs.models.algorithms.Merge;
import ku.cs.models.algorithms.Selection;
import ku.cs.models.algorithms.Shuffle;
import ku.cs.services.utils.Beep;

public class PrimaryController {

    public Button primaryButton;
    public VBox vBox;

    private VisualizerController controller;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void initialize() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ku/cs/views/visualizer.fxml"));
        VBox visualizer;
        try {
            visualizer = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        visualizer.prefWidthProperty().bind(vBox.widthProperty());
        visualizer.prefHeightProperty().bind(vBox.heightProperty());
        controller = loader.getController();
        vBox.getChildren().add(visualizer);

        visualizer.prefWidthProperty().bind(vBox.widthProperty());
        visualizer.prefHeightProperty().bind(vBox.heightProperty());
    }

    public void test1() {
        Beep.tone(523, 100, 0.125);
        controller.runVisualize(32, 10, new Shuffle(), new Selection(), new Check());
    }

    public void test2() {
        Beep.tone(587, 100, 0.125);
        controller.runVisualize(64, 10, new Shuffle(), new Merge(), new Check());
    }

    public void test3() {
        Beep.tone(659, 100, 0.125);
        controller.runVisualize(256, 5, new Shuffle(), new Merge(), new Check());
    }
}
