package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized String getName() {
        ++count;
        return Thread.currentThread().getName() + " " + count;
    }

}

public class ThreadSafeDemo {
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(count.getName());
                }
            });
        }
        executor.shutdown();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(count.getCount());
    }
}
