package ku.cs.week01;

public class Sqrt2 {

    /**
     * <p>
     * https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Approximations_that_depend_on_the_floating_point_representation
     * </p>
     * 
     * <p>
     * Running time = O(n) , O(loops)
     * </p>
     * @param x value
     * @param loops the number of loops in the computation
     * @return approximation sqrt of x depend on the loops, more loops count more correct approximation.
     */
    public static double sqrt_approx(double x, int loops) {

        // Approximation sqrt value
        double result = Double.longBitsToDouble(((Double.doubleToLongBits(x)-(1l << 52) >> 1) + (1l << 61)));

        // Make it closer to the correct value
        for (int i = 0; i < loops; i++) result = (result + x / result) / 2;

        return result;
    }

    /**
     * Running time = O(8) = O(1)
     * @param x double value
     * @return sqrt of x
     */
    public static double sqrt_approx(double x) {
        return sqrt_approx(x, 8);
    }

    public static void main(String[] args) {
        System.out.println(sqrt_approx(144));
    }
}
