package ku.cs.exercises;

import java.util.ArrayList;
import java.util.List;

public class Main4 {

    public static <T extends Comparable<T>> void merge(T[] comparables, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<T> L = new ArrayList<>(n1);
        List<T> R = new ArrayList<>(n2);

        for (int i = 0; i < n1; i++) L.add(comparables[left + i]);
        for (int i = 0; i < n2; i++) R.add(comparables[mid + i + 1]);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(R.get(j)) <= 0) comparables[k] = L.get(i++);
            else comparables[k] = R.get(j++);
            k++;
        }

        while (i < n1) comparables[k++] = L.get(i++);
        while (j < n2) comparables[k++] = R.get(j++);
        
    }

    public static <T extends Comparable<T>> void mergeSort(T[] comparables, int left, int right) {
        if (left >= right) return;
        int m = (left + right) / 2;

        mergeSort(comparables, left, m);
        mergeSort(comparables, m + 1, right);

        merge(comparables, left, m, right);
    }


    public static int findMissing(Integer[] array) {
        if (array.length == 0) throw new IllegalArgumentException();
        mergeSort(array, 0, array.length - 1);
        int prev = 0;
        for (int i : array) {
            if (i - prev == 2) return i - 1;
            else if (i - prev == 1) prev = i;
            else throw new IllegalArgumentException(i + " - " + prev);
        }
        return array.length + 1;
    }

    private static void printArrayAndMissing(Integer[] array) {
        System.out.print("Array ");
        for (int i : array) System.out.printf("%d ", i);
        System.out.println("; Missing Value = " + findMissing(array));
    }

    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 3, 4, 6, 7, 8};
        printArrayAndMissing(array1);

        Integer[] array2 = {1, 2, 3, 4, 5, 6, 7, 8};
        printArrayAndMissing(array2);

        Integer[] array3 = {1};
        printArrayAndMissing(array3);

        Integer[] array4 = {2};
        printArrayAndMissing(array4);
    }
}
