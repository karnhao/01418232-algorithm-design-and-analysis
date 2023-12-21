package ku.cs.models.algorithms;

import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;
import ku.cs.services.utils.Beep;

public class Shuffle extends SortingAlgorithm {
    @Override
    public void sort() {
        for (int i = array.length - 1; i > 0; i--) {
            // random index to swap
            int random_index = (int)Math.floor(Math.random() * array.length);
            int temp = array[random_index];
            array[random_index] = array[i];
            array[i] = temp;
            Beep.tone(sortingSound.getHz(i), beep_mSec, volume);
            Beep.tone(sortingSound.getHz(temp), beep_mSec, volume);
            this.setSelectedIndices(SelectedIndex.as(i, random_index));
            this.onChange();
        }
    }
}
