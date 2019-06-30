package test;

public class Main {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int f;
                long l = System.currentTimeMillis();
                for (int i = 0; i < 100; i++)
                    for (int j = 0; j < 1000; j++)
                        for (int k = 0; k < 10000; k++)
                            f = k;
                long l1 = System.currentTimeMillis();
                System.out.println(l1 - l + " " + Thread.currentThread().getName());
                long l2 = System.currentTimeMillis();
                for (int i = 0; i < 10000; i++)
                    for (int j = 0; j < 1000; j++)
                        for (int k = 0; k < 100; k++)
                            f = k;
                long l3 = System.currentTimeMillis();
                System.out.println(l3 - l2 + " " + Thread.currentThread().getName());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int f, i, j, k;
                long l = System.currentTimeMillis();
                for (i = 0; i < 100; i++)
                    for (j = 0; j < 1000; j++)
                        for (k = 0; k < 10000; k++)
                            f = k;
                long l1 = System.currentTimeMillis();
                System.out.println(l1 - l + " " + Thread.currentThread().getName());
                long l2 = System.currentTimeMillis();
                for (i = 0; i < 10000; i++)
                    for (j = 0; j < 1000; j++)
                        for (k = 0; k < 100; k++)
                            f = k;
                long l3 = System.currentTimeMillis();
                System.out.println(l3 - l2 + " " + Thread.currentThread().getName());
            }
        }).start();

    }
}
