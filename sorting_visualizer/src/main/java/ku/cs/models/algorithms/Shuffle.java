package ku.cs.models.algorithms;

import ku.cs.models.FixedSelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Shuffle extends SortingAlgorithm {
    @Override
    public void sort() {
        for (int i = array.length - 1; i > 0; i--) {
            // random index to swap
            int random_index = (int)Math.floor(Math.random() * array.length);
            int temp = array[random_index];
            array[random_index] = array[i];
            array[i] = temp;
            this.beepValue(temp, i);
            this.setSelectedIndices(FixedSelectedIndex.as(i, random_index));
            this.onChange();
        }
    }
}
