package structs.array;


import java.util.Arrays;
import java.util.Random;

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
        assert(isSorted(a, lo, mid - 1));
        assert(isSorted(a, mid, hi));

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

        assert(isSorted(a, lo, hi));
    }

    //public static <T extends Comparable<? super T>> void quickSort(T[] a) {
    //}

    public static <T> void shuffle(T[] a) {
        int subArraySz = 2;

        while (subArraySz <= a.length) {
            for (int i = 0; i < a.length; i += subArraySz) {
                int hi = i + subArraySz - 1;
                if (a.length - (hi + 1) < subArraySz)
                    hi = a.length;
                mergeShuffle(a, i, Math.min(hi, a.length - 1));
            }
            subArraySz *= 2;
        }
    }

    private static <T> void mergeShuffle(T[] a, int lo, int hi) {
        if (a.length < 2 || lo == hi)
            return;

        T[] cpy = (T[]) new Object[hi - lo + 1];
        for (int i = lo; i <= hi; ++i)
            cpy[i - lo] = a[i];

        int mid = (hi - lo + 1) / 2 + lo;
        int i = lo;
        int j = mid;

        Random rand = new Random();

        while (i < mid && j <= hi) {
            if (rand.nextBoolean()) {
                a[i + j - mid] = cpy[i - lo];
                i++;
            }
            else {
                a[i + j - mid] = cpy[j - lo];
                j++;
            }
        }

        while (i < mid) {
            a[i + j - mid] = cpy[i - lo];
            i++;
        }

        while (j <= hi) {
            a[i + j - mid] = cpy[j - lo];
            j++;
        }
    }

    public static <T extends Comparable<? super T>> boolean isSorted(T[] a, int lo,
                                                                int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (a[i - 1].compareTo(a[i]) > 0)
                return false;
        return true;
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

        Integer[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(nums2));
        shuffle(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
