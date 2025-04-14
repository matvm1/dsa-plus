package structs.array;


import java.util.Arrays;

public class Array {
    public static <T extends Comparable<? super T>> void sort(T[] a, SortCallback<T> callback) {
        callback.call(a);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        mergeSortHelper(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSortHelper(T[] a, int lo, int hi) {
        if (lo >= hi)
            return;

        int mid = (hi - lo + 1) / 2 + lo;
        mergeSortHelper(a, lo, mid - 1);
        mergeSortHelper(a, mid, hi);

        merge(a, lo, mid, hi);
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, int lo, int mid, int hi) {
        Comparable[] cpy = new Comparable[hi - lo + 1];
        System.arraycopy(a, lo , cpy, 0, hi - lo + 1);

        int i = lo, j = mid;

        while (i < mid && j <= hi) {
            if (cpy[i  - lo].compareTo(cpy[j - lo]) <= 0) {
                a[i + (j - mid)] = (T) cpy[i - lo];
                i++;
            }
            else {
                a[i + (j - mid)] = (T) cpy[j - lo];
                j++;
            }
        }

        while (i < mid) {
            a[i + (j - mid)] = (T) cpy[i - lo];
            i++;
        }

        while (j <= hi) {
            a[i + (j - mid)] = (T) cpy[j - lo];
            j++;
        }
    }

    @FunctionalInterface
    private interface SortCallback<T extends Comparable<? super T>> {
        void call(T[] elements);
    }

    public static void main(String[] args) {
        Integer[] nums = {12, 4, 5, 6, 12, 1, 3, 9, 56, 0};
        System.out.println(Arrays.toString(nums));
        Array.sort(nums, Array::mergeSort);
        System.out.println(Arrays.toString(nums));

        String[] names = {"Charlie", "Abbie", "Ross", "Xander", "Zach", "Aaron", "Ross"};
        System.out.println(Arrays.toString(names));
        Array.sort(names, Array::mergeSort);
        System.out.println(Arrays.toString(names));
    }
}
