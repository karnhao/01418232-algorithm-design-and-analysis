package ku.cs.exercises;

import java.util.Scanner;

public class Main8 {

    /**
     * รับ array จำนวนเต็ม ที่เรียงจากน้อยไปมากแล้วจะเก็บผลลัพท์การวางค่าในต้นไม้ทวิภาคไว้ที่ target
     * 
     * Time Complexity อยู่ภายใน O(n)
     * 
     * @param array array จำนวนเต็มที่เรียงแล้วจากน้อยไปมาก
     * @param start
     * @param end
     */
    private static void iDontHaveAnyIdeaToNameThisMethod(int[] array, int[] target, int start, int end, int index) {
        if (end < start) return;
        if (end == start) {
            target[index] = array[end];
            return;
        }

        int mid = (int) Math.ceil((start + end) / 2.0);
        target[index] = array[mid];
        iDontHaveAnyIdeaToNameThisMethod(array, target, start, mid - 1, index * 2);
        iDontHaveAnyIdeaToNameThisMethod(array, target, mid + 1, end, index * 2 + 1);
    }

    // T(1) = 1
    // T(n) = 2T(n / 2) + 1
    //      = 2 * 2(T(n / 2) / 2) + 1 + 1
    //      = 2 ** i * T(n / n ** i) + i
    //      = 2 ** log_2(n) * T(1) + log_2(n)
    //      = n + log_2(n)
    // Time complexity is in O(n)

    public static int[] toTreeArray(int[] array) {
        int treeSize = 1;
        while (treeSize < array.length) treeSize <<= 1;
        System.out.println("Tree Size = " + treeSize);
        int[] result = new int[treeSize];

        // O(n)
        iDontHaveAnyIdeaToNameThisMethod(array, result, 0, array.length - 1, 1);

        return result;
    }

    public static void main(String[] args) {
        int size;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Size: ");
        size = sc.nextInt();

        int[] array = new int[size];
        System.out.print("Enter the elements of the array (ex. 1 2 3 4 5 6): ");
        for (int i = 0; i < size; i++) array[i] = sc.nextInt();
        
        sc.close();

        System.out.print("The elements of the array are: ");
        for (int i = 0; i < size; i++)
            System.out.print(array[i] + " ");
        System.out.println();


        int[] treeArray = toTreeArray(array);
        for (int i = 0; i < treeArray.length; i++) 
            System.out.printf("%d ", treeArray[i]);


    }
}
