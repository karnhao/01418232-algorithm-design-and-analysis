package ku.cs.models.algorithms;

import javafx.scene.paint.Color;
import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Check extends SortingAlgorithm {

    @Override
    public void sort() {
        if (this.array == null || this.array.length == 0) return;
        int previous = this.array[0];
        SelectedIndex[] selectedIndices = new SelectedIndex[array.length];
        selectedIndices[0] = new SelectedIndex(0, Color.GREEN);
        for (int i = 1; i < array.length; i++) {
            selectedIndices[i] = new SelectedIndex(i, previous > this.array[i] ? Color.RED : Color.GREEN);
            previous = this.array[i];
            this.setSelectedIndices(selectedIndices);
            beep(i);
            onChange();
        }
    }
}
