package algorithm.multi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiQuickSort {
    private final ExecutorService executorService;
    public MultiQuickSort(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void sort(int[] array, int delta, AtomicInteger tasks) {
        sort(array, 0, array.length - 1, delta, tasks, 0);
    }

    private void sort(int[] array, int l, int r, final int delta, AtomicInteger tasks, int smallT) {
        if (array.length == 0) {
            if (smallT == 0) {
                tasks.addAndGet(-1);
            }
            return;
        }
        if (l >= r) {
            if (smallT == 0) {
                tasks.addAndGet(-1);
            }
            return;
        }

        int supportElement = getSupportElement(array, l, r);
        int i = l;
        int j = r;
        while (i <= j) {
            while (array[i] < supportElement) {
                i++;
            }
            while (array[j] > supportElement) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                j--;
                i++;
            }
        }
        if (l < j) {
            if (j - l < delta) {
                sort(array, l, j, delta, tasks, smallT + 1);
            } else {
                tasks.addAndGet(1);
                final int finalJ = j;
                executorService.submit(
                        () -> sort(array, l, finalJ, delta, tasks, smallT)
                );
            }
        }
        if (r > i) {
            if (r - i < delta) {
                sort(array, i, r, delta, tasks, smallT + 1);
            } else {
                tasks.addAndGet(1);
                int finalI = i;
                executorService.submit(
                        () -> sort(array, finalI, r, delta, tasks, smallT)
                );
            }
        }
        if (smallT == 0) {
            tasks.addAndGet(-1);
        }
    }

    private static int getSupportElement(int[] array, int l, int r) {
        int supportIndex = l + (r - l) / 2;
        return array[supportIndex];
    }

    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
