package ku.cs.exercises;

public class Main6 {

    public static boolean containsIndexValue(int[] array, int start, int end) {
        int mid = (start + end) / 2;
        if (array[mid] == mid) return true;
        if (start >= end) return false;

        if (array[mid] > mid) return containsIndexValue(array, start, mid); // หารครึ่ง
        return containsIndexValue(array, mid + 1, end); // หารครึ่ง
    }

    // containsIndexValue(1)     = 1
    // containsIndexValue(n)     = containsIndexValue(n / 2) + 1
    // containsIndexValue(n / 2) = containsIndexValue(n / 2 / 2) + 1 + 1
    // containsIndexValue(n / 4) = containsIndexValue(n / 2 / 2 / 2) + 1 + 1 + 1
    // ...
    // containsIndexValue(n)     = containsIndexValue(n / (2 ** i)) + i
    // แทน i = log_2(n)
    // containsIndexValue(n)     = containsIndexValue(n / (2 ** log_2(n))) + log_2(n)
    //                           = containsIndexValue(n / n) + log_2(n)
    //                           = containsIndexValue(1) + log_2(n)
    //                           = 1 + log_2(n) is in O(log(n))
    //
    // Time Complexity ของ function คือ O(log(n))

    public static boolean containsIndexValue(int[] array) {
        return containsIndexValue(array, 0, array.length - 1);
    }

    public static void printArray(int[] array) { // print all elements in an array for debugging
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) System.out.printf("%d", array[i]);
            else System.out.printf("%d ", array[i]);
        }
    }
    

    public static void main(String[] args) {
        int[][] arrays = new int[][]{ // testcases
            {-9, -6, 0, 1, 4, 10}, // testcase 1
            {0, 2, 3, 4, 5, 6}, // testcase 2
            {1, 2, 3, 4, 5, 6}, // testcase 3
            {-10, 0, 2}, // ...
            {0},
            {1},
            {2},
            {1, 2},
            {-1, 0, 1, 3}
        };

        for (int[] array : arrays) { // ทดสอบทุก testcase
            printArray(array);
            System.out.print(" : ");
            System.out.print(containsIndexValue(array));
            System.out.println();
        }
    }
}
