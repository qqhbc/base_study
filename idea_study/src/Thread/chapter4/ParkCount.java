package Thread.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count {
    private int count;
    private Random random = new Random();

    public synchronized int increase() {
        /* 增加线程不安全的风险*/
        int temp = count;
        if (random.nextBoolean()) Thread.yield();
        return (count = ++temp);
    }

    public synchronized int get() {
        return count;
    }
}

public class ParkCount implements Runnable {
    private static Count count = new Count();
    private static List<ParkCount> list = new ArrayList<>(); // 便于统计总数
    private int number;
    private final int id;
    private static volatile boolean cancel = false;

    public static void setCancel() {
        cancel = true;
    }

    public ParkCount(int id) {
        this.id = id;
        list.add(this);
    }

    @Override
    public void run() {
        while (!cancel) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total " + count.increase());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "ParkCount " + id + " " + getValue();
    }

    public static int sumParkCount() {
        int sum = 0;
        for (ParkCount parkCount : list) {
            sum += parkCount.getValue();
        }
        return sum;
    }

    public static int getTotalCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new ParkCount(i));
        }
        TimeUnit.SECONDS.sleep(3);
        ParkCount.setCancel();
        executor.shutdown();
        if (!executor.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("This is error");
        }
        System.out.println("总人数为：" + ParkCount.sumParkCount());
        System.out.println("计数器数目：" + ParkCount.getTotalCount());
    }
}
