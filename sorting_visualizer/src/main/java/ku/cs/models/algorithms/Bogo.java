package ku.cs.models.algorithms;

import java.util.Random;

import javafx.scene.paint.Color;
import ku.cs.models.FixedSelectedIndex;
import ku.cs.models.RangeSelectedIndex;
import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Bogo extends SortingAlgorithm {

    @Override
    public void sort() {
        Random rand = new Random();
        while (!isSorted(array)) {
            for (int i = 0; i < array.length; i++) {
                int randomIndexToSwap = rand.nextInt(array.length);
                int temp = array[randomIndexToSwap];
                array[randomIndexToSwap] = array[i];
                array[i] = temp;

                setSelectedIndices(FixedSelectedIndex.as(i, randomIndexToSwap));
                this.onChange();
            }
        }
        
    }

    private boolean isSorted(int[] arr) {
        RangeSelectedIndex rangeSelectedIndex = new RangeSelectedIndex(0, 0);
        rangeSelectedIndex.setColor(Color.GREEN);
        for (int i = 0; i < arr.length - 1; i++) {
            rangeSelectedIndex.setEnd(i);
            if (arr[i] > arr[i + 1]) {
                this.setSelectedIndices(new SelectedIndex[]{rangeSelectedIndex, new FixedSelectedIndex(i)});
                beep(i);
                onChange();
                return false;
            }
            this.setSelectedIndices(new SelectedIndex[]{rangeSelectedIndex});
            beep(i);
            onChange();
        }
        return true;
    }
    
}
