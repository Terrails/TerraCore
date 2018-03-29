package terrails.terracore.util;

public class MathUtils {

    public static double squared(double... args) {
        double squared = 0;
        for (double n : args) {
            squared += (n * n);
        }
        return squared;
    }
}
