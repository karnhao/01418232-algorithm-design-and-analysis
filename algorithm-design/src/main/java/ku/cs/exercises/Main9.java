package ku.cs.exercises;

import java.util.Arrays;
import java.util.Scanner;

public class Main9 {

    public static int maxSatisfiedPoint(int[] order) {

        Arrays.sort(order); // n*log_2(n)

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < order.length - 1; i++) { // O(n ^ 2)
            int total = order[i];
            if (total > 0) break;
            for (int j = i + 1; j < order.length; j++) 
                total += order[j] * (j - i + 1);
            max = Math.max(max, total);
        }

        return max;
    }

    public static void main(String[] args) {
        int size;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Size: ");
        size = sc.nextInt();

        int[] array = new int[size];
        System.out.print("Enter the elements of the array (ex. 1 2 3 4 5 6): ");
        for (int i = 0; i < size; i++)
            array[i] = sc.nextInt();

        sc.close();

        System.out.print("The elements of the array are: ");
        for (int i = 0; i < size; i++)
            System.out.print(array[i] + " ");
        System.out.println();

        System.out.printf("Max satisfy: %d\n", maxSatisfiedPoint(array));

    }
}
