package ku.cs.models;

import javafx.scene.paint.Color;

public class RangeSelectedIndex extends SelectedIndex {
    private int start;
    private int end;

    public RangeSelectedIndex(int start, int end) {
        this(start, end, DEFAULT_SELECTED_COLOR);
    }

    public RangeSelectedIndex(int start, int end, Color color) {
        super(color);
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return end - start + 1;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
