package ku.cs.models;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ku.cs.controllers.VisualizerController;
import java.util.Iterator;
import java.util.List;

/**
 * Modify from <a href=
 * "https://github.com/beingmartinbmc/SortMe/blob/master/src/frames/MyPanel.java">this
 * code</a>
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
        double width = getWidth() - 1;
        double height = getHeight() - 1;
        double colWidth = width / values.length;
        double x = 0;
        colorBars(graphicsContext, values, height, colWidth, x,
                new SelectedIndexCollection(sorter.getSelectedIndices()));

    }

    private void colorBars(GraphicsContext graphicsContext, int[] values, double height, double colWidth, double x,
            SelectedIndexCollection selectedIndices) {
        double margin = values.length > 128 ? 0 : 1;
        for (int index = 0; index < values.length; index++) {
            graphicsContext.setFill(Color.valueOf("#FFFFFF"));

            List<SelectedIndex> selectedIndexList = selectedIndices.getList();

            if (selectedIndexList != null) {
                Iterator<SelectedIndex> iterator = selectedIndices.getList().iterator();
                while (iterator.hasNext()) {
                    SelectedIndex selectedIndex = iterator.next();
                    if (selectedIndex == null)
                        continue;
                    if (selectedIndex instanceof FixedSelectedIndex) {
                        FixedSelectedIndex fixedSelectedIndex = (FixedSelectedIndex) selectedIndex;
                        if (fixedSelectedIndex.getIndex() == index) {
                            graphicsContext.setFill(fixedSelectedIndex.getColor());
                            break;
                        }
                    } else if (selectedIndex instanceof RangeSelectedIndex) {
                        RangeSelectedIndex selectedIndexRange = (RangeSelectedIndex) selectedIndex;
                        if (index <= selectedIndexRange.getEnd() && index >= selectedIndexRange.getStart()) {
                            graphicsContext.setFill(selectedIndexRange.getColor());
                            break;
                        }
                    }
                }
            }
            int value = values[index];
            double colHeight = (height * (1.0 * value / maxValue));
            graphicsContext.fillRect(x, height - colHeight, colWidth - margin, colHeight);
            x += colWidth;
        }
    }

    private void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("Delay = " + sorter.getDelay(), 8, 25);
    }

    public DefaultSorter setSorter(SequenceSorter sorter) {
        if (sorter == null || this.sorter == sorter)
            return null;

        if (this.sorter != null)
            sorter.clearChangeListener();

        this.sorter = sorter;
        this.sorter.addChangeListener((event) -> Platform.runLater(this::paint));
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
