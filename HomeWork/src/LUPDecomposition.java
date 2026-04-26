import java.util.Scanner;

public class LUPDecomposition {

    public static void printMatrix(String name, double[][] matrix) {
        System.out.println("Матриця " + name + ":");
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.printf("%8.3f ", val);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printVector(String name, double[] vector) {
        System.out.println("Вектор " + name + ":");
        for (int i = 0; i < vector.length; i++) {
            System.out.printf("x%d = %8.3f\n", (i + 1), vector[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 4;

        double[][] A = new double[n][n];
        double[] b = new double[n];

        System.out.println("=== РОЗВ'ЯЗАННЯ СЛАР МЕТОДОМ LUP-РОЗКЛАДУ ===");
        System.out.println("Введiть коефiцiєнти матрицi A (по " + n + " у кожному рядку):");
        for (int i = 0; i < n; i++) {
            System.out.print("Рядок " + (i + 1) + ": ");
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Введiть вектор вiльних членiв b:");
        for (int i = 0; i < n; i++) {
            System.out.print("b[" + (i + 1) + "]: ");
            b[i] = scanner.nextDouble();
        }

        System.out.println("\n--- ЗАДАНА СИСТЕМА (A * x = b) ---");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%6.1f*x%d ", A[i][j], (j + 1));
                if (j < n - 1) System.out.print("+ ");
            }
            System.out.printf("= %6.1f\n", b[i]);
        }
        System.out.println();

        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, C[i], 0, n);
        }

        int[] pi = new int[n];
        for (int i = 0; i < n; i++) pi[i] = i;

        for (int k = 0; k < n; k++) {
            double max = 0.0;
            int k_prime = k;
            
            for (int i = k; i < n; i++) {
                if (Math.abs(C[i][k]) > max) {
                    max = Math.abs(C[i][k]);
                    k_prime = i;
                }
            }

            if (max == 0.0) {
                System.out.println("Помилка: Сингулярна матриця (визначник дорiвнює нулю)!");
                return;
            }

            int temp = pi[k];
            pi[k] = pi[k_prime];
            pi[k_prime] = temp;

            double[] tempRow = C[k];
            C[k] = C[k_prime];
            C[k_prime] = tempRow;

            for (int i = k + 1; i < n; i++) {
                C[i][k] /= C[k][k];
                for (int j = k + 1; j < n; j++) {
                    C[i][j] -= C[i][k] * C[k][j];
                }
            }
        }

        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        double[][] P = new double[n][n];

        for (int i = 0; i < n; i++) {
            L[i][i] = 1.0;
            P[i][pi[i]] = 1.0;
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    L[i][j] = C[i][j];
                } else {
                    U[i][j] = C[i][j];
                }
            }
        }

        System.out.println("--- РЕЗУЛЬТАТИ LUP-РОЗКЛАДУ ---");
        printMatrix("L (Одинична нижня-трикутна)", L);
        printMatrix("U (Верхня-трикутна)", U);
        printMatrix("P (Перестановок)", P);

        double[] b_prime = new double[n];
        for (int i = 0; i < n; i++) {
            b_prime[i] = b[pi[i]];
        }

        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = b_prime[i];
            for (int j = 0; j < i; j++) {
                y[i] -= L[i][j] * y[j];
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = y[i];
            for (int j = i + 1; j < n; j++) {
                x[i] -= U[i][j] * x[j];
            }
            x[i] /= U[i][i];
        }

        System.out.println("--- ФIНАЛЬНИЙ РОЗВ'ЯЗОК ---");
        printVector("X", x);
    }
}