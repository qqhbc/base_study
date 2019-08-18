package algorithm;

import java.util.Random;

class QuickSort {
    public void quickSort(int[] a, int start, int end) {
        if (start >= end) return;
        int index = firstSort(a, start, end);
        quickSort(a, start, index - 1);
        quickSort(a, index + 1, end);
    }

    private int firstSort(int[] a, int start, int end) {
        int key = a[start];
        while (start < end) {
            //从后往左遍历
            while (key <= a[end] && start < end) {
                end--;
            }
            a[start] = a[end];
            while (key > a[start] && start < end) {
                start++;
            }
            a[end] = a[start];
        }
        a[end] = key;
        return end;
    }

    public int[] randomArray(int length, int bound) {
        Random random = new Random();
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(bound);
        }
        return a;
    }
}

public class QuickSortDemo {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] a = quickSort.randomArray(8, 10);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "、");
        }
        System.out.println("============");
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "、");
        }


        System.out.println("======================");
        quickSort.quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "、");
        }
    }
}
