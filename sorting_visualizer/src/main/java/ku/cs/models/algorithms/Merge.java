package ku.cs.models.algorithms;

import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;
import ku.cs.services.utils.Beep;

public class Merge extends SortingAlgorithm {
    @Override
    public void sort() {
        mergeSort(0, array.length - 1);
    }

    private void mergeSort(int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(l, m);
            mergeSort(m + 1, r);

            merge(l, m, r);
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
