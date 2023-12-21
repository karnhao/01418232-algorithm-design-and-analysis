package ku.cs.models;
import javafx.scene.paint.Color;
public class SelectedIndex {

    private static final Color DEFAULT_SELECTED_COLOR = Color.RED;
    private int index;
    private Color color;

    public SelectedIndex(int index, Color color) {
        this.index = index;
        this.color = color;
    }

    public SelectedIndex(int index) {
        this(index, Color.RED);
    }

    public static SelectedIndex[] as(Color color, int... indices) {
        SelectedIndex[] selectedIndices = new SelectedIndex[indices.length];
        for (int i = 0; i < indices.length; i++)
            selectedIndices[i] = new SelectedIndex(indices[i], color);
        return selectedIndices;
    }

    public static SelectedIndex[] as(int... indices) {
        return SelectedIndex.as(DEFAULT_SELECTED_COLOR, indices);
    }

    public int getIndex() {
        return index;
    }

    public Color getColor() {
        return color;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
