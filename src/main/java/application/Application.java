package application;

import algorithm.multi.MultiQuickSort;
import algorithm.single.SingleQuickSort;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        ExecutorService executorService;
        final int[] arrayE = getExampleArray1();
        //printArray(arrayE);
        final long[] time = new long[4];
        time[0] = runSingle(arrayE);
        //printArray(arrayE);
        for (int i = 2; i < 5; i++) {
            executorService = Executors.newFixedThreadPool(i);
            MultiQuickSort quickSort = new MultiQuickSort(executorService);
            long startTime = System.nanoTime();
            quickSort.sort(arrayE, 10);
            //printArray(arrayE);
            executorService.shutdown();
            long endTime = System.nanoTime();
            time[i - 1] = endTime - startTime;
            System.out.println("Threads: " + i + ", time with " + (endTime - startTime));
        }
        showTable(time);
    }
    private static int[] getExampleArray1() {
        Random rn = new Random();
        int n = 1000;
        int[] array = new int[10000000];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = rn.nextInt(n);
        }
        return array;
    }

    private static long runSingle(final int[] array) {
        long startTime = System.nanoTime();
        SingleQuickSort.sort(array);
        long endTime = System.nanoTime();
        System.out.println("Threads: " + 1 + ", time with " + (endTime - startTime));
        return endTime - startTime;
    }

    private static void showTable(long[] time) {
        System.out.println();
        System.out.println("Num process | T(multi)/T(single)");
        for (int i = 1; i < 4; i++) {
            double div = ((double) time[i] / (double)time[0]);
            System.out.println((i + 1) + " | " + div);
        }
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
