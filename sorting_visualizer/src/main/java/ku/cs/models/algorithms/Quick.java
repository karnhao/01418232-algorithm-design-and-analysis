package ku.cs.models.algorithms;

import ku.cs.models.FixedSelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Quick extends SortingAlgorithm {

    @Override
    public void sort() {
        this.sort(0, array.length - 1);
    }

    private void sort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                beep(i, j);
                setSelectedIndices(FixedSelectedIndex.as(i, j));
                onChange();
                
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
    
}
