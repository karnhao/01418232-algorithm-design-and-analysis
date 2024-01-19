package ku.cs.controllers;

import javafx.scene.layout.VBox;
import ku.cs.models.SequenceSorter;
import ku.cs.models.SortingAlgorithm;
import ku.cs.models.VisualizerCanvas;
import ku.cs.services.utils.ArrayCreator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class VisualizerController {
    public VBox vBox;
    private SequenceSorter sorter;
    private VisualizerCanvas canvas;

    public void updateCanvas() {
        canvas = new VisualizerCanvas(this, vBox.getBoundsInLocal().getWidth(), vBox.getBoundsInLocal().getHeight());
        vBox.getChildren().setAll(canvas);
    }

    public void runVisualize(int size, int delay, SortingAlgorithm... sortingAlgorithms) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        updateCanvas();
        if (sorter != null && sorter.isAlive()) {
            sorter.interrupt();
            sorter = null;
            return;
        }

        int[] array = ArrayCreator.createSequence(size);
        SequenceSorter sequenceSorter = new SequenceSorter(array, delay);
        for (SortingAlgorithm sortingAlgorithm : sortingAlgorithms)
            sequenceSorter.addAlgorithm(sortingAlgorithm.getClass().getDeclaredConstructor().newInstance());

        this.sorter = sequenceSorter;
        canvas.setSorter(sequenceSorter).start();
        canvas.paint();
    }

    public void runVisualize(int size, int delay, List<SortingAlgorithm> sortingAlgorithms) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        updateCanvas();
        if (sorter != null && sorter.isAlive()) {
            sorter.interrupt();
            sorter = null;
            return;
        }

        int[] array = ArrayCreator.createSequence(size);
        SequenceSorter sequenceSorter = new SequenceSorter(array, delay);
        for (SortingAlgorithm sortingAlgorithm : sortingAlgorithms)
            sequenceSorter.addAlgorithm(sortingAlgorithm.getClass().getDeclaredConstructor().newInstance());

        this.sorter = sequenceSorter;
        canvas.setSorter(sequenceSorter).start();
        canvas.paint();
    }
}
