package ku.cs.exercises;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class Main7 {

    private static int illuminati(String text, int k, int start, int end) {
        // Assume that text is lowercase (ตัวพิมพ์เล็กทั้งหมด)

        if (start < 0 || end <= start) return 0;
        
        // นับจำนวน character
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = start; i <= end; i++) // O(n)
            map.put(text.charAt(i), map.getOrDefault(text.charAt(i), 0) + 1); // O(1)

        // หาตัวอักษรที่มีจำนวนไม่เกิน k
        char c_fail = 0;
        for (Entry<Character, Integer> i : map.entrySet())  // O(n)
            if (i.getValue() < k) c_fail = i.getKey();
        if (c_fail == 0) return end - start + 1;

        int fail_index = -1; // หา index เพื่อเป็นจุดตัด
        for (int i = start; i <= end; i++) { // O(n)
            if (text.charAt(i) == c_fail) {
                fail_index = i;
                break;
            }
        }

        if (fail_index == -1) throw new RuntimeException("");

        return Math.max(illuminati(text, k, start, fail_index - 1), illuminati(text, k, fail_index + 1, end)); // 2 * T(n / 2)
    }

    // วิเคราะห์ Time Complexity :
    // แทน illuminati(text, ...) เป็น T(n) โดย n คือความยาวของ text
    // T(1) = 1
    // T(n) = 3n + 2T(n / 2)        -----------> T(n / 2) = 3(n / 2) + 2T((n / 2) / 2)
    //      = 3n + 2(3(n / 2) + 2T((n / 2) / 2))
    //      = 3n + 2(3(n / 2) + 2T(n / 4))
    //      = 3n + 3n + 4T(n / 4)   -----------> T(n / 4) = 3(n / 4) + 2T(n / 8)
    //      = 3n + 3n + 4(3(n / 4) + 2T(n / 8))
    //      = 3n + 3n + 3n + 8T(n / 8)
    //      = 3ni + ((2 ** i) * T(n / (2 ** i)))
    //          แทน i = log_2(n) เพื่อสร้าง T(1) :
    //      = 3n*log_2(n) + ((2 ** log_2(n)) * T(n / (2 ** log_2(n))))
    //      = 3n*log(n) + n + T(n / n)
    //      = 3n*log(n) + n + 1
    //      = O(n*log(n))

    public static int illuminati(String text, int k) {
        return illuminati(text, k, 0, text.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println("ababcaaadc (k = 2) = " + illuminati("ababcaaadc", 2));
        System.out.println("acbcbdbdbdac (k = 3) = " + illuminati("acbcbdbdbdac", 3));
    }
}
