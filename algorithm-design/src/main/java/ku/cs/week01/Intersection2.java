package ku.cs.week01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Intersection2 {

    /**
     * @param list1 must be a sorted integer list
     * @param list2 must be a sorted integer list
     * @return list of intersection integers between list1 and list2
     */
    public static List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
        // get the max last value from the sorted list
        int max = Math.max(list1.get(list1.size() - 1), list2.get(list2.size() - 1));
        
        int[] counting = new int[max + 1];
        LinkedList<Integer> result = new LinkedList<Integer>();

        list1.forEach(t -> counting[t]++);
        list2.forEach(t -> {
            if (counting[t] > 0) { result.add(t); counting[t]--; }
        });


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
