package algorithm;

public class BinarySearch {
    public int binarySearch(int[] a, int start, int end, int key) {
        if (start <= end) {
            int mod = (start + end) / 2;
            if (a[mod] == key) return mod;
            else if (a[mod] > key) return binarySearch(a, start, mod - 1, key);
            else return binarySearch(a, mod + 1, end, key);
        }
        return -1;
    }

    public int binarySearch(int[] a, int key) {
        int start = 0;
        int end = a.length - 1;
        int mod = (start + end) / 2;
        while (start <= end) {
            if (a[mod] == key) return mod;
            else if (a[mod] > key) return end = mod - 1;
            else return start = mod + 1;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] a = {3, 6, 7, 9, 99, 222, 6666};
        BinarySearch binarySearch = new BinarySearch();
        int i;
        i = binarySearch.binarySearch(a, 0, a.length - 1, 99);
        int j = binarySearch.binarySearch(a, 99);
        System.out.println(i);
        System.out.println(j);
    }
}
