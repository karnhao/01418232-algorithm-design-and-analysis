package ku.cs.exercises.greedy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main1 {
    
    private static final List<Integer> function(int[] start, int[] finish) {
        if (start.length != finish.length) throw new IllegalArgumentException();
        LinkedList<Integer> result = new LinkedList<Integer>();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < start.length; i++) {
            if (map.containsKey(finish[i]) // duplicate key : choose lower time range
            && finish[i] - start[i] > finish[i] - map.get(finish[i])) continue;
            map.put(finish[i], start[i]); // O(log(n))
        } // O(n * log(n))

        int old_finish = -1, count = 0;
        for (int finish_ : map.keySet()) {
            int start_ = map.get(finish_); // O(log(n))
            if (start_ > old_finish) {
                result.add(count);
                old_finish = finish_;
            }
            count++;
        } // O(n * log(n))
        // Time Complexity = O(n * log(n)) + O(n * log(n)) = O(n*log(n))
        return result;
    }

    public static void main(String[] args) {
        int start[] = {1, 3, 4, 6, 8, 5};
        int finish[] = {2, 6, 5, 8, 9, 9};

        function(start, finish).forEach((t)->{
            System.out.print(t + " ");
        });
        
    }

}
