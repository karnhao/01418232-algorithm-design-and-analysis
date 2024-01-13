package ku.cs.models;
import javafx.scene.paint.Color;
public class FixedSelectedIndex extends SelectedIndex {

    private int index;

    public FixedSelectedIndex(int index, Color color) {
        super(color);
        this.index = index;
    }

    public FixedSelectedIndex(int index) {
        this(index, Color.RED);
    }

    public static FixedSelectedIndex[] as(Color color, int... indices) {
        FixedSelectedIndex[] selectedIndices = new FixedSelectedIndex[indices.length];
        for (int i = 0; i < indices.length; i++)
            selectedIndices[i] = new FixedSelectedIndex(indices[i], color);
        return selectedIndices;
    }

    public static FixedSelectedIndex[] as(int... indices) {
        return FixedSelectedIndex.as(DEFAULT_SELECTED_COLOR, indices);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
