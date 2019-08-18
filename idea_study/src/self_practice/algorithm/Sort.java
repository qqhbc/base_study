package algorithm;

import java.util.Random;

public class Sort {
    public int[] random(int length, int bound) {
        Random random = new Random();
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = random.nextInt(bound);
        }
        return a;
    }
}
