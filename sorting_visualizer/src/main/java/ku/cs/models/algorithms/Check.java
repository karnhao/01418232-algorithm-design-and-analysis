package ku.cs.models.algorithms;

import javafx.scene.paint.Color;
import ku.cs.models.FixedSelectedIndex;
import ku.cs.models.RangeSelectedIndex;
import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Check extends SortingAlgorithm {

    @Override
    public void sort() {
        if (this.array == null || this.array.length == 0) return;
        int previous = this.array[0];
        RangeSelectedIndex rangeSelectedIndex = new RangeSelectedIndex(0, 0);
        rangeSelectedIndex.setColor(Color.GREEN);
        for (int i = 1; i < array.length; i++) {
            if (previous > array[i]) {
                this.setSelectedIndices(new SelectedIndex[]{rangeSelectedIndex, new FixedSelectedIndex(i)});
                beep(i);
                onChange();
                return;
            }
            previous = this.array[i];
            rangeSelectedIndex.setEnd(i);
            this.setSelectedIndices(new SelectedIndex[]{rangeSelectedIndex});
            beep(i);
            onChange();
        }
    }
}
