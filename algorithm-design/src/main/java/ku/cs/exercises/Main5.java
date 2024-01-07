package ku.cs.exercises;

public class Main5 {
    // A[i][j] < A[i][j']  when j < j' หมายความว่าในแถวเดียวกันค่าทางด้านซ้ายจะน้อยกว่าค่าทางด้านขวา
    // A[i][j] < A[i'][j'] when i < i' หมายความว่าค่าด้านบนน้อยกว่าค่าด้านล่าง
    // ตัวอย่าง matrix ที่ตรงตาม 2 เงื่อนไขบนคือ
    // 01 02 03 05 08 10
    // 11 12 15 16 18 19
    // 20 22 24 25 26 28
    // 40 42 48 64 66 69
    // 80 81 82 84 85 86
    // 88 90 91 92 94 96
    // รับค่า x มา ต้องการรู้ว่ามี ค่า x อยู่ใน matrix หรือไม่

    public static int binarySearch(int arr[], int l, int r, int x) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == x) return mid;
            else if (arr[mid] > x) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }


    public static boolean contains(int[][] matrix, int value, int up, int down) {

        if (up == down) { // เหลือแถวเดียวใช้ binary search
            int searchResult = binarySearch(matrix[up], 0, matrix[up].length - 1, value);
            return searchResult == -1 ? false : true;
        }

        int midIndex = (up + down) / 2;
        int midValue = matrix[midIndex][matrix[midIndex].length - 1]; // ค่าตรงกลางแถวและ column สุดท้าย
        if (midValue == value) return true;
        if (midValue > value) return contains(matrix, value, up, midIndex);
        else return contains(matrix, value, midIndex + 1, down);
    }

    public static boolean contains(int[][] matrix, int value) {
        return contains(matrix, value, 0, matrix.length - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,  2,  3,  5,  8,  10},
                          {11, 12, 15, 16, 18, 19},
                          {20, 22, 24, 25, 26, 28},
                          {40, 42, 48, 64, 66, 69},
                          {80, 81, 82, 84, 85, 86},
                          {88, 90, 91, 92, 94, 96}};
        
        System.out.println("81 = " + contains(matrix, 81));
        System.out.println("82 = " + contains(matrix, 82));
        System.out.println("42 = " + contains(matrix, 42));
        System.out.println("94 = " + contains(matrix, 94));
        System.out.println("69 = " + contains(matrix, 69));
        System.out.println(" 2 = " + contains(matrix,  2));
        System.out.println();
        System.out.println("13 = " + contains(matrix, 13));
        System.out.println("21 = " + contains(matrix, 21));
        System.out.println("4  = " + contains(matrix,  4));
        System.out.println("83 = " + contains(matrix, 83));
        System.out.println("45 = " + contains(matrix, 45));
        System.out.println("29 = " + contains(matrix, 29));
    }

}
