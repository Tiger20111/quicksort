package algorithm.single;

public class SingleQuickSort {
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int l, int r) {
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
        if (l < j) {
            sort(array, l, j);
        }
        if (r > i) {
            sort(array, i, r);
        }
    }

    private static int getSupportElement(int[] array, int l, int r) {
        int supportIndex = l + (r - l) / 2;
        return array[supportIndex];
    }
}
