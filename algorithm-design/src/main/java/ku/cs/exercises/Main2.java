package ku.cs.exercises;

public class Main2 {

    public static int[] dividePositiveAndNegative(int[] array) {
        int[] result = new int[array.length];
        int temp = 0;

        for (int i = 0; i < array.length; i++) // O(n)
            if (array[i] < 0) result[temp++] = array[i];

        for (int i = 0; i < array.length; i++) // O(n)
            if (array[i] >= 0) result[temp++] = array[i];
        
        // O(n) + O(n) is in O(n)
        return result;
    }
    
    public static void main(String[] args) {
        int[] array = {7, -3, 5, -1, -8, 6, 2, -4};
        int[] result = dividePositiveAndNegative(array);
        for (int i : result) System.out.printf("%d ", i);
    }
}
