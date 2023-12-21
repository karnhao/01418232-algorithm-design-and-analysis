package ku.cs.services.utils;

public class ArrayCreator {
    public static int[] createSequence(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = i + 1;
        return array;
    }
}
