package ku.cs.models.algorithms;

import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Selection extends SortingAlgorithm {
    @Override
    public void sort() {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[index])
                    index = j; // searching for lowest index
                setSelectedIndices(SelectedIndex.as(index, j));
                beep(j);
                onChange();
            }
            int smallerNumber = array[index];
            array[index] = array[i];
            array[i] = smallerNumber;
        }
    }
}
