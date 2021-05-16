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
        runSingle(arrayE);
        for (int i = 2; i < 5; i++) {
            executorService = Executors.newFixedThreadPool(i);
            MultiQuickSort quickSort = new MultiQuickSort(executorService);
            final int[] array = arrayE;
//            for (int value : array) {
//                System.out.print(value + " ");
//            }
//            System.out.println("---------------");
            long startTime = System.nanoTime();
            quickSort.sort(array, 10);
            executorService.shutdown();
            long endTime = System.nanoTime();
//            for (int value : array) {
//                System.out.print(value + " ");
//            }
            System.out.println("Threads: " + i + ", time with " + (endTime - startTime));
        }
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

    private static void runSingle(final int[] array) {
        long startTime = System.nanoTime();
        SingleQuickSort.sort(array);
        long endTime = System.nanoTime();
        System.out.println("Threads: " + 1 + ", time with " + (endTime - startTime));
    }
}
