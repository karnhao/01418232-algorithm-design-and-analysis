package ku.cs.models.algorithms;

import ku.cs.models.FixedSelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Debug extends SortingAlgorithm {

    @Override
    public void sort() {
        quickSort(0, array.length - 1);
    }

    private void quickSort(int l, int r) {
        if (l < r) {
            int i = partition(l, r);
            quickSort(l, i - 1);
            quickSort(i + 1, r);
        }
    }


    private int partition(int l, int r) {
        int p = array[l];
        int i = l;
        int j = r + 1;

        do {
            do i++; while (array[i] < p);
            do j--; while (array[j] > p);
            swap(i, j);

            setSelectedIndices(FixedSelectedIndex.as(i, j));
            onChange();
            beep(i, j);
        } while (i < j);
        swap(i, j);
        swap(l, j);

        return j;
    }

    private void swap(int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    
}
