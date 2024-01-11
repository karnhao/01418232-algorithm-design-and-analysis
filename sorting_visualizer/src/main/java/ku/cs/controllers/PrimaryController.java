package ku.cs.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ku.cs.models.SortingAlgorithm;
import ku.cs.models.algorithms.*;

public class PrimaryController {
    public VBox vBox;
    public ComboBox<SortingAlgorithm> comboBox;
    public VBox scrollVBox;
    public Label lengthLabel;
    public TextField lengthTextField;
    public Slider delaySlider;
    public Label delayLabel;

    private AlgorithmSequenceListController algorithmSequenceListController;

    private VisualizerController visualizerController;

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
        visualizerController = loader.getController();
        vBox.getChildren().add(visualizer);

        visualizer.prefWidthProperty().bind(vBox.widthProperty());
        visualizer.prefHeightProperty().bind(vBox.heightProperty());

        comboBox.getItems().addAll(
            new Shuffle(),
            new Selection(),
            new Merge(),
            new TopUpNonRecursiveMerge(),
            new Heap(),
            new Radix(),
            new Check()
        );

        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ku/cs/views/algorithm_sequence_list.fxml"));
        Node node;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        algorithmSequenceListController = loader.getController();
        scrollVBox.getChildren().add(node);


        delaySlider.valueProperty().addListener((observableValue, number, t1) ->
                delayLabel.setText("Delay : " + t1.intValue() + " ms"));
    }

    public void onAddAlgorithmButton() {
        if (comboBox.getValue() == null) return;
        algorithmSequenceListController.addAlgorithm(comboBox.getValue());
    }

    public void onStartVisualizeButton() {
        int size = Integer.parseInt(lengthTextField.getText().trim());
        if (size < 2) throw new IllegalArgumentException("Size must bigger than 2");

        int delay = (int) delaySlider.getValue();
        if (delay < 1) throw new IllegalArgumentException("Delay must greater than 1");
        try {
            visualizerController.runVisualize(size, delay, algorithmSequenceListController.getSortingAlgorithmList().toArray(SortingAlgorithm[]::new));
        } catch (Exception e) {
            System.out.println("An error has occur : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
