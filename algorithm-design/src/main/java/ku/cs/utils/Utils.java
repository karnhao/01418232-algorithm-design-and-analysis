package ku.cs.utils;

public class Utils {
    public static boolean isPrime(int number) {
        for (int i = (int)Math.ceil(Math.sqrt(number)); i > 1; i--) 
            if (number % i == 0) return false;
        return true;
    }
}
