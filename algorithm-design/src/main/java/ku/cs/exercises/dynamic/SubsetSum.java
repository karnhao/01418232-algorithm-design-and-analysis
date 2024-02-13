package ku.cs.exercises.dynamic;

/**
 * SubsetSum
 */
public class SubsetSum {

    public static void main(String[] args) {
        final int B = 16;
        final int[] s = new int[]{2, 3, 5, 6};

        boolean[][] matrix = new boolean[s.length][B];
        for (int i = 0; i < s.length; i++) {
            matrix[i][0] = true;
            for (int j = 1; j <= B; j++) {
                matrix[i][j] = j == s[i] || (i > 0 && matrix[i - 1][j]);
                if(!matrix[i][j]) matrix[i][j] = i > 0 && s[i] <= j && matrix[i - 1][j - s[i]];
            }
        }









        for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
            System.out.println(i);
        }
    }
}