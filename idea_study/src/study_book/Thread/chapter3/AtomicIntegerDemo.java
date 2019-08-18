package Thread.chapter3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic 实现原子性 cas算法 当原始值和预期值相等时，则替换新值
 */
public class AtomicIntegerDemo implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);

    public int getValue() {
        return i.get();
    }

    private void evenIncrement() {
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while (true) {
            this.evenIncrement();
        }
    }

    public static void main(String[] args) {
        /* 定时任务，5秒退出*/
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 10000);
        ExecutorService executor = Executors.newCachedThreadPool();
        AtomicIntegerDemo aid = new AtomicIntegerDemo();
        executor.execute(aid);
        while (true) {
            int val = aid.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
