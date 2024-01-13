package ku.cs.models.algorithms;

import java.util.Arrays;

import ku.cs.models.FixedSelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Radix extends SortingAlgorithm {

    @Override
    public void sort() {
        final int base = 10;
        int maxVal = Arrays.stream(array).max().getAsInt();
        int exp = 1;
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[base];

        while (maxVal / exp > 0) {
            for (int i = 0; i < base; i++) {
                count[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                int index = (array[i] / exp) % base;
                count[index]++;
                
                beep(i);
                setSelectedIndices(FixedSelectedIndex.as(i));
                onChange();
            }

            for (int i = 1; i < base; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int index = (array[i] / exp) % base;
                output[count[index] - 1] = array[i];
                count[index]--;
            }

            for (int i = 0; i < n; i++) {
                array[i] = output[i];
                beep(i);
                setSelectedIndices(FixedSelectedIndex.as(i));
                onChange();
            }

            exp *= base;
        }
    }
}
