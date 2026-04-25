import java.util.Scanner;

public class NumericalIntegration {

    public static double f(double x) {
        return Math.exp(Math.pow(x, 3));
    }

    public static double integrateRectangle(double a, double b, double h) {
        double sum = 0.0;
        int n = (int) Math.round((b - a) / h);

        for (int i = 0; i < n; i++) {
            double x_mid = a + i * h + h / 2.0;
            sum += f(x_mid);
        }
        return sum * h;
    }

    public static double integrateTrapezoid(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);

        double sum = (f(a) + f(b)) / 2.0;

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            sum += f(x);
        }
        return sum * h;
    }

    public static double integrateSimpson(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);

        int simpsonIntervals = n;
        double extraTrapezoidArea = 0.0;

        if (n % 2 != 0) {
            simpsonIntervals = n - 1;
            double x_last = a + simpsonIntervals * h;
            extraTrapezoidArea = (f(x_last) + f(b)) / 2.0 * h;
        }

        double sum = f(a) + f(a + simpsonIntervals * h);

        for (int i = 1; i < simpsonIntervals; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sum += 2 * f(x);
            } else {
                sum += 4 * f(x);
            }
        }

        return (sum * h / 3.0) + extraTrapezoidArea;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== INTEGRAL CALCULATION ===");
        System.out.println("Function: e^(x^3)");

        System.out.print("Enter interval start (a): ");
        double a = scanner.nextDouble();

        System.out.print("Enter interval end (b): ");
        double b = scanner.nextDouble();

        System.out.print("Enter integration step (h): ");
        double h = scanner.nextDouble();

        if (h <= 0 || b <= a) {
            System.out.println("Error: Invalid interval or step.");
            return;
        }

        double resRect = integrateRectangle(a, b, h);
        double resTrap = integrateTrapezoid(a, b, h);
        double resSimp = integrateSimpson(a, b, h);

        System.out.println("\n--- RESULTS ---");
        System.out.printf("Rectangle method: %.6f\n", resRect);
        System.out.printf("Trapezoid method: %.6f\n", resTrap);
        System.out.printf("Simpson method:   %.6f\n", resSimp);

        int n = (int) Math.round((b - a) / h);
        if (n % 2 != 0) {
            System.out.println("\n*Note: Since the number of intervals (" + n + ") is odd, " +
                               "the last interval for Simpson's method was computed using the trapezoid formula.");
        }

        scanner.close();
    }
}