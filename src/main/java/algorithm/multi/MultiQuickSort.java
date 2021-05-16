package algorithm.multi;

import java.util.concurrent.ExecutorService;

public class MultiQuickSort {
    private final ExecutorService executorService;
    public MultiQuickSort(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void sort(final int[] array, int delta) {
        sort(array, 0, array.length - 1, delta);
    }

    private void sort(final int[] array, int l, int r, final int delta) {
        if (array.length == 0) {
            return;
        }
        if (l >= r) {
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
        if (l < j && (j - l < delta)) {
            sort(array, l, j, delta);
        } else {
            final int finalJ = j;
            executorService.submit(
                    () -> sort(array, l, finalJ, delta)
            );
        }
        if (r > i && (r - i < delta)) {
            sort(array, i, r, delta);
        } else {
            int finalI = i;
            executorService.submit(
                    () -> sort(array, finalI, r, delta)
            );
        }
    }

    private static int getSupportElement(int[] array, int l, int r) {
        int supportIndex = l + (r - l) / 2;
        return array[supportIndex];
    }
}
