package ku.cs.exercises;

public class Main3 {
    
    public static float f(float[][] matrix, int x, int y, int start_y) {
        if (x < 2) throw new IllegalArgumentException();

        if (y == 1) {
            float min = Float.MAX_VALUE;
            float current;
            for (int i = 0; i < x - 1; i++) {
                current = matrix[start_y - 1][i + 1] - matrix[start_y - 1][i];
                if (current < 0) current = -current; // Absolute value
                if (current < min) min = current;
            }
            return min;
        } // เมื่อเหลือเพียงแถวเดียว จะวนซ้ำ O(n) รอบเพื่อคำนวนหาผลต่างที่น้อยที่สุดแล้วส่งค่ากลับ

        return Math.min(f(matrix, x, y / 2, start_y), f(matrix, x, y / 2, start_y + y / 2)); // แบบ matrix เป็น 2 ส่วนแล้วส่งกลับค่าที่น้อยสุดได้เป็น O(log_2(n))
    } // Time complexity เป็น log_2(n) * n = O(n*log_2(n))

    public static float f(float[][] matrix, int x, int y) {
        return f(matrix, x, y, 1);
    }
    
    public static void main(String[] args) {
        float[][] matrix = {{1, 6, 5, 3},
                            {4, 3, 7, 9},
                            {3, 8, 2, 4},
                            {6, 9, 5, 7}};
        System.out.println(f(matrix, 4, 4));
    }
}
