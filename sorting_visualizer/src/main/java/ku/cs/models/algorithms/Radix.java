package ku.cs.models.algorithms;

import java.util.Arrays;

import ku.cs.models.SelectedIndex;
import ku.cs.models.SortingAlgorithm;

public class Radix extends SortingAlgorithm {
    private int getMax() 
    { 
        int mx = array[0]; 
        for (int i = 1; i < array.length; i++) {
            if (array[i] > mx) 
                mx = array[i]; 
            beep(i);
            setSelectedIndices(SelectedIndex.as(i));
            onChange();
        }
        return mx; 
    } 
 
    // A function to do counting sort of arr[] according to 
    // the digit represented by exp. 
    private void countSort(int exp) 
    { 
        int output[] = new int[array.length]; // output array 
        int i; 
        int count[] = new int[10]; 
        Arrays.fill(count,0); 
 
        // Store count of occurrences in count[] 
        for (i = 0; i < array.length; i++) {
            count[ (array[i]/exp)%10 ]++; 
            beep(i);
            setSelectedIndices(SelectedIndex.as(i));
            onChange();
        }
 
        // Change count[i] so that count[i] now contains 
        // actual position of this digit in output[] 
        for (i = 1; i < 10; i++) 
            count[i] += count[i - 1]; 
 
        // Build the output array 
        for (i = array.length - 1; i >= 0; i--) 
        { 
            output[count[ (array[i]/exp)%10 ] - 1] = array[i]; 
            count[ (array[i]/exp)%10 ]--; 
            beep(i);
            setSelectedIndices(SelectedIndex.as(i));
            onChange();
        } 
 
        // Copy the output array to arr[], so that arr[] now 
        // contains sorted numbers according to current digit 
        for (i = 0; i < array.length; i++) {
            array[i] = output[i]; 
            beep(i);
            setSelectedIndices(SelectedIndex.as(i));
            onChange();
        }
    } 

    @Override
    public void sort() {
        // Find the maximum number to know number of digits 
        int m = getMax(); 
 
        // Do counting sort for every digit. Note that instead 
        // of passing digit number, exp is passed. exp is 10^i 
        // where i is current digit number 
        for (int exp = 1; m/exp > 0; exp *= 10) 
            countSort(exp); 
    } 
}
