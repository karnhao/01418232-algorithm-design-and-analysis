package ku.cs.controllers;

import javafx.scene.layout.VBox;
import ku.cs.models.SequenceSorter;
import ku.cs.models.SortingAlgorithm;
import ku.cs.models.VisualizerCanvas;
import ku.cs.services.utils.ArrayCreator;

public class VisualizerController {
    public VBox vBox;
    private SequenceSorter sorter;
    private VisualizerCanvas canvas;

    public void updateCanvas() {
        System.out.println(vBox.getBoundsInLocal().getWidth() + ", " + vBox.getBoundsInLocal().getHeight());
        canvas = new VisualizerCanvas(this, vBox.getBoundsInLocal().getWidth(), vBox.getBoundsInLocal().getHeight());
        vBox.getChildren().setAll(canvas);
    }

    public void runVisualize(int size, int delay, SortingAlgorithm... sortingAlgorithms) {
        updateCanvas();
        if (sorter != null && sorter.isAlive()) {
            sorter.interrupt();
            sorter = null;
            return;
        }

        System.out.println(vBox.getBoundsInParent().getWidth() + ", " + vBox.getBoundsInParent().getHeight());
        int[] array = ArrayCreator.createSequence(size);
        SequenceSorter sequenceSorter = new SequenceSorter(array, delay);
        for (SortingAlgorithm sortingAlgorithm : sortingAlgorithms)
            sequenceSorter.addAlgorithm(sortingAlgorithm);

        this.sorter = sequenceSorter;
        canvas.setSorter(sequenceSorter).start();
        canvas.paint();
    }
}
