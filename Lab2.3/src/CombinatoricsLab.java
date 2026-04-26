import java.util.Scanner;
import java.math.BigInteger;

public class CombinatoricsLab {

    public static BigInteger combinations(long n, long k) {
        if (k > n) return BigInteger.ZERO;
        if (k == 0 || k == n) return BigInteger.ONE;

        long effectiveK = Math.min(k, n - k);
        BigInteger result = BigInteger.ONE;

        for (long i = 1; i <= effectiveK; i++) {
            result = result.multiply(BigInteger.valueOf(n - effectiveK + i));
            result = result.divide(BigInteger.valueOf(i));
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ОБЧИСЛЕННЯ КОМБIНАТОРНОI ЗАДАЧI ===");
        System.out.println("Тип вибiрки: Комбiнацii без повторень");
        System.out.print("Введiть загальну кiлькiсть студентiв на поверсi (n): ");
        long n = scanner.nextLong();

        System.out.print("Введiть кiлькiсть студентiв на одне чергування (k): ");
        long k = scanner.nextLong();

        if (n < 0 || k < 0) {
            System.out.println("Помилка: Кiлькiсть студентiв не може бути вiд'ємною.");
        } else if (k > n) {
            System.out.println("Помилка: Кiлькiсть чергових не може перевищувати загальну кількість студентів.");
        } else {
            BigInteger result = combinations(n, k);
            System.out.println("\n------------------------------------------------");
            System.out.printf("Кiлькiсть способiв скласти графiк: %s\n", result);
            System.out.println("------------------------------------------------");
        }

        scanner.close();
    }
}