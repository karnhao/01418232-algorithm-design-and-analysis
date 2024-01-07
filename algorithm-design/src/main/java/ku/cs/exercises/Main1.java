package ku.cs.exercises;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Main1 {

    // ข้อมูลเป็นชนิดอะไรก็ได้เช่น Integer, Double, ... จึงไม่สามารถใช้วิธีการทำงานแบบแทนค่าใน Array เป็น Index เหมือน Counting Sort ได้
    public static <T> List<T> f(T[] array) {
        List<T> result = new LinkedList<>();

        // Unique elements ////////////////////////
        HashSet<T> uniqueElements = new HashSet<>();
        for (T t : array) uniqueElements.add(t);
        /////////////////////////////////////////// มี Time Complexity เป็น O(n)

        float half_length = array.length / 2.0f;
        
        // O(n^2)
        for (T i : uniqueElements) {
            int count = 0;
            for (T j : array) if (i.equals(j)) count++;
            if (count > half_length) result.add(i);
        }

        return result;
    }
    public static void main(String[] args) {
        Integer[] array1 = {3, 3, 4, 2, 4, 4, 2, 4, 4};
        Float[] array2 = {1.3f, 1.3f, 4.26f};
        Boolean[] array3 = {false, true, false, true};

        System.out.println("Array 1");
        f(array1).forEach(System.out::println);
        System.out.println();

        System.out.println("Array 2");
        f(array2).forEach(System.out::println);
        System.out.println();

        System.out.println("Array 3");
        f(array3).forEach(System.out::println);
        System.out.println();
    }
}
