package ku.cs.exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main10 {

    /**
     * compare function is in O(1)
     */
    public static class GregComparator implements Comparator<Character> {

        private Map<Character, Integer> characterOrder;
        public GregComparator(Map<Character, Integer> characterOrder) {
            this.characterOrder = characterOrder;
        }

        @Override
        public int compare(Character c1, Character c2) {
            Integer value1 = characterOrder.get(c1); // O(1)
            Integer value2 = characterOrder.get(c2);

            if (value1 == null && value2 == null) return c1.compareTo(c2); // c1 and c2 is not in character order map : ทำการ compare แบบปกติ
            else if (value1 == null) return  1;  // c1 is not in character order map
            else if (value2 == null) return -1;  // c2 is not in character order map
            
            return value1 - value2;
        }
        
    }
    
    /**
     * Time complexity is in O(n * log_2(n))
     * @param order
     * @param str
     * @return
     */
    public static String greg(String order, String str) {
        Map<Character, Integer> orderMap = new HashMap<Character, Integer>();

        // เก็บ order data ที่ตัวแปร orderMap
        // key จะเป็น character และ value จะเป็นค่าสำหรับเปรียบเทียบ
        for (int i = 0; i < order.length(); i++) // O(n)
            orderMap.put(order.charAt(i), i);

        // แปลง String เป็น List<Character>
        List<Character> answerList = new ArrayList<>(str.length());
        for (char c : str.toCharArray()) answerList.add(c); // O(n)

        // จัดเรียง List ด้วย GregComparator และส่ง orderMap ให้ GregComparator
        answerList.sort(new GregComparator(orderMap)); // O(n*log_2(n))
        
        // แปลงจาก List<Character> เป็น String
        StringBuilder builder = new StringBuilder();
        answerList.forEach(builder::append); // O(n)
        return builder.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter string order: ");
        String firstString = scanner.nextLine();

        System.out.print("Enter string: ");
        String secondString = scanner.nextLine();

        scanner.close();

        System.out.println("order: " + firstString);
        System.out.println("str: " + secondString);

        System.out.println("output: " + greg(firstString, secondString));
    }
}
