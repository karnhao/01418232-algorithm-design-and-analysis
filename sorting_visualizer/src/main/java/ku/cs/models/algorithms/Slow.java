package ku.cs.models.algorithms;

import ku.cs.models.FixedSelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Slow extends SortingAlgorithm {
    @Override
    public void sort() {
        slowSort(0, array.length - 1);
    }

    private void slowSort(int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            slowSort(l, m);
            slowSort(m + 1, r);

            if (array[r] < array[m]) {
                int temp = array[r];
                array[r] = array[m];
                array[m] = temp;
            }

            setSelectedIndices(FixedSelectedIndex.as(r, m));
            beep(r, m);
            onChange();

            slowSort(l, r - 1);
        }
    }
}
