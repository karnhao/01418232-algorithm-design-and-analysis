package ku.cs.models;

import javafx.scene.paint.Color;

public abstract class SelectedIndex {
    protected static final Color DEFAULT_SELECTED_COLOR = Color.RED;
    private Color color;

    public SelectedIndex() {
        this.color = DEFAULT_SELECTED_COLOR;
    }

    public SelectedIndex(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
