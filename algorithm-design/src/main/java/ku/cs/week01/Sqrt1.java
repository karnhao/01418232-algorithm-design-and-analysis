package ku.cs.week01;

public class Sqrt1 {

    public static double sqrt(double value, double estimatedValue, int loops) {
        for (int i = 0; i < loops; i++) 
            estimatedValue = (estimatedValue + value / estimatedValue) / 2;
        return estimatedValue;
    }

    /**
     * Running time is log10(n)
     * @param value
     * @return
     */
    public static double sqrt(double value) {
        double temp = value;
        int loops = 0;

        while (temp >= 1) {
            temp /= 10;
            loops++;
        }

        return sqrt(value, value / 2, loops * loops);
    }

    public static void main(String[] args) {
        System.out.println(sqrt(1000000));
    }
}
