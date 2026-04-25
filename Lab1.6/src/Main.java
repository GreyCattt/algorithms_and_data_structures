import java.util.Random;
import java.util.Locale;

public class Main {
    private static final Random RANDOM = new Random();

    public static void cocktailShakerSort(int[] arr) {
        boolean swapped = true;
        int start = 0;
        int end = arr.length - 1;

        while (swapped) {
            swapped = false;

            for (int i = start; i < end; ++i) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }

            swapped = false;
            end = end - 1;

            for (int i = end - 1; i >= start; --i) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            
            start = start + 1;
        }
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(10000);
        }
        return arr;
    }

    public static long averageExecutionTimeNs(int size, int runs) {
        long totalTime = 0;

        for (int r = 0; r < runs; r++) {
            int[] arr = generateRandomArray(size);
            long startTime = System.nanoTime();
            cocktailShakerSort(arr);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }

        return totalTime / runs;
    }

    public static void main(String[] args) {
        int n = 100;
        int[] sizes = {n, n * n, n * n * n};
        int defaultRuns = 10;

        System.out.println("N,average_ns,average_ms");

        for (int size : sizes) {
            int runs = size == n * n * n ? 1 : defaultRuns;
            long averageTimeNs = averageExecutionTimeNs(size, runs);
            double averageTimeMs = averageTimeNs / 1_000_000.0;

            System.out.printf(Locale.US, "%d,%d,%.6f%n", size, averageTimeNs, averageTimeMs);
        }
    }
}