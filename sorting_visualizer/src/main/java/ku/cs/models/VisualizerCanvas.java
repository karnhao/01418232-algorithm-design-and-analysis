package ku.cs.models;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ku.cs.controllers.VisualizerController;

/**
 * Modify from <a href="https://github.com/beingmartinbmc/SortMe/blob/master/src/frames/MyPanel.java">this code</a>
 */
public class VisualizerCanvas extends Canvas {
    private SequenceSorter sorter;
    private int maxValue;

    private final VisualizerController parent;

    public VisualizerCanvas(VisualizerController parent, double width, double height) {
        this.parent = parent;
        setWidth(width);
        setHeight(height);
        this.paint();
    }

    public void paint() {
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        setWidth(parent.vBox.getWidth());
        setHeight(parent.vBox.getHeight());
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        if (sorter == null) {
            setWidth(100);
            setHeight(100);
            graphicsContext.fillText("No Sorter", getWidth() / 2, getHeight() / 2);
            return;
        }

        render(graphicsContext);
        int[] values = sorter.getArray();
        int width = (int) (getWidth() - 1);
        int height = (int) (getHeight() - 1);
        int colWidth = Math.round((float) width / (float) values.length);
        int x = 0;
        colorBars(graphicsContext, values, height, colWidth, x, sorter.getSelectedIndices());

    }

    private void colorBars(GraphicsContext graphicsContext, int[] values, int height, int colWidth, int x, SelectedIndex... selectedIndices) {
        for (int index = 0; index < values.length; index++) {
            graphicsContext.setFill(Color.valueOf("#006664"));
            if (selectedIndices != null)
                for (SelectedIndex i : selectedIndices) {
                    if (i == null) continue;
                    if (i.getIndex() == index) {
                        graphicsContext.setFill(i.getColor());
                        break;
                    }
                }
            int value = values[index];
            int colHeight = (int) ((float) height * ((float) value / (float) maxValue));
            graphicsContext.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            x += colWidth;
        }
    }

    private void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Delay = " + sorter.getDelay(), 8, 25);
    }

    public DefaultSorter setSorter(SequenceSorter sorter) {
        if (sorter == null || this.sorter == sorter) return null;
        if (this.sorter != null)
            sorter.clearChangeListener();
        this.sorter = sorter;
        this.sorter.addChangeListener((event)-> Platform.runLater(this::paint) );
        maxValue = 0;
        for (int intValue : this.sorter.getArray())
            maxValue = Math.max(maxValue, intValue);
        return sorter;
    }

    public DefaultSorter setSorter(SortingAlgorithm sorter) {
        SequenceSorter sequenceSorter = new SequenceSorter(sorter.getArray(), sorter.getDelay());
        sequenceSorter.addAlgorithm(sorter);
        this.setSorter(sequenceSorter);
        return sequenceSorter;
    }
}
