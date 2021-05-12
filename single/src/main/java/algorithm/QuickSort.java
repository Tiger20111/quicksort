package algorithm;

public class QuickSort {
    public static int[] sort(int[] array) {

    }

    private static int[] sort(int[] array, int l, int r) {
        if (array.length == 0) {
            return array;
        }
        if (l >= r) {
            return array;
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

        }
    }

    public static int getSupportElement(int[] array, int l, int r) {

    }
}
