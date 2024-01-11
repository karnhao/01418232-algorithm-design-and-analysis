package ku.cs.models.algorithms;

import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class TopUpNonRecursiveMerge extends SortingAlgorithm {
    @Override
    public void sort() {
        int currentSize;
        int leftStart;

        for (currentSize = 1; currentSize <= array.length; currentSize *= 2) {

            for (leftStart = 0; leftStart < array.length; leftStart += 2 * currentSize) {
                int mid = leftStart + currentSize - 1;

                int rightEnd = leftStart + 2 * currentSize - 1;
                if (rightEnd > array.length - 1) rightEnd = array.length - 1;
                if (mid > array.length - 1) mid = array.length - 1;
                    
                merge(leftStart, mid, rightEnd);
            }
        }
    }



    private void merge(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = array[l + i];
            setSelectedIndices(SelectedIndex.as(l + i));
            beep(l + i);
            this.onChange();
        }
        for (int i = 0; i < n2; i++) {
            R[i] = array[m + 1 + i];
            setSelectedIndices(SelectedIndex.as(m + 1 + i));
            beep(m + 1 + i);
            this.onChange();
        }

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                setSelectedIndices(SelectedIndex.as(k));
                beep(k);
                this.onChange();
                i++;
            } else {
                array[k] = R[j];
                setSelectedIndices(SelectedIndex.as(k));
                beep(k);
                this.onChange();
                j++;
            }
            k++;
        }

        // remaining elements
        while (i < n1) {
            array[k] = L[i];
            setSelectedIndices(SelectedIndex.as(k));
            beep(k);
            this.onChange();
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            setSelectedIndices(SelectedIndex.as(k));
            beep(k);
            this.onChange();
            j++;
            k++;
        }
    }
}
