package ku.cs.week01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Intersection1 {

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> result = new LinkedList<>();
        List<T> tempList = new LinkedList<>();
        tempList.addAll(list2);
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < tempList.size(); j++) {
                if (list1.get(i).equals(tempList.get(j))) {
                    result.add(list1.get(i));
                    tempList.remove(j);
                    break;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.addAll(Arrays.asList(2, 5, 5, 5));

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.addAll(Arrays.asList(2, 2, 3, 5, 5, 7));

        List<Integer> result = intersection(list1, list2);
        result.forEach(System.out::println);
    }
}
