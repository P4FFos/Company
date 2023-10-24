package Company.Util;

public class Util {
    public static double truncateSalary(double salary) {
        return Math.floor(salary * 100) / 100;
    }
}
