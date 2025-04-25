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

    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        shuffle(a);

        quickSortHelper(a, 0, a.length - 1);
        assert(isSorted(a, 0, a.length));
    }

    private static <T extends Comparable<? super T>> void quickSortHelper(T[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        ThreeWayPartition p =  partition(a, lo, lo, hi);
        quickSortHelper(a, lo, p.lt - 1);
        quickSortHelper(a, p.gt + 1, hi);
    }

    public static <T extends Comparable<? super T>> T getKthSmallest(T[] a, int k) {
        T[] cpy = a.clone();
        return getKthSmallestHelper(cpy, k - 1, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> T getKthSmallestHelper(T[] a,
                                                                            int k, int lo, int hi) {
        ThreeWayPartition p = partition(a, (hi - lo + 1) / 2 + lo, lo, hi);

        if (k >= p.lt && k <= p.gt)
            return a[k];
        else if (k < p.lt)
            return getKthSmallestHelper(a, k, lo, p.lt - 1);
        else
            return getKthSmallestHelper(a, k, p.gt + 1, hi);
    }

    // 3 way partition <partitionItemIndex, ==partitionItemIndex, >partitionItemIndex
    private static <T extends Comparable<? super T>> ThreeWayPartition partition(T[] a, int partitionItemIndex,
                                                                 int lo, int hi) {
        int lt = lo;
        int gt = hi;
        int i = lo;
        T v = a[partitionItemIndex];

        while (i <= gt) {
            if (a[i].compareTo(v) < 0)
                swap(a, lt++, i++);
            else if (a[i].compareTo(v) > 0)
                swap(a, gt--, i);
            else
                i++;
        }

        assert(isThreeWayPartitioned(a, lt, gt));

        return new ThreeWayPartition(lt, gt);
    }

    public static <T> void swap(T[] a, int x, int y) {
        T tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

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

    public static <T extends Comparable<? super T>> boolean isThreeWayPartitioned(T[] a, int lt, int gt) {
        for (int i = 0; i < lt; ++i)
            if (a[i].compareTo(a[lt]) >= 0)
                return false;

        for (int i = gt + 1; i < a.length; ++i)
            if (a[i].compareTo(a[lt]) <= 0)
                return false;

        for (int i = lt; i <= gt; ++i)
            if (a[i].compareTo(a[lt]) != 0)
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

        Integer[] nums3 = {5, 3, 1, 5, 7, 1, 5, 9};
        ThreeWayPartition p = partition(nums3, 0, 0, nums3.length - 1);
        assert(isThreeWayPartitioned(nums3, p.lt, p.gt));
        System.out.println(Arrays.toString(nums3));

        Integer[] nums4 = {2, 4, 21, 1, 9, 0, -1, 9, 100, 101};

        for (int i = 1; i <= nums4.length; ++i)
            System.out.print(getKthSmallest(nums4, i) + " ");

        System.out.println("\n" + Arrays.toString(nums4));
        Array.sort(nums4, Array::quickSort);
        System.out.println(Arrays.toString(nums4));

        Random rand = new Random();
        int len = rand.nextInt(50000);
        Integer[] numsRand = new Integer[len];
        for (int i = 0; i < len; ++i)
            numsRand[i] = rand.nextInt();
        //System.out.println(Arrays.toString(numsRand));
        Array.sort(numsRand, Array::quickSort);
        //System.out.println(Arrays.toString(numsRand));
    }

    private static class ThreeWayPartition {
        private int lt;
        private int gt;

        private ThreeWayPartition(int lt, int gt) {this.lt = lt; this.gt = gt;}
    }
}
