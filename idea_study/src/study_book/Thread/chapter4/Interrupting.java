package Thread.chapter4;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程阻塞 sleep是可以中断的，可以捕获异常，正常退出任务
 * 用户输入和synchronized不可中断，直接退出任务
 */
class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("Interrup SleepBlocked");
        }
        System.out.println("Exit SleepBlocked run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream inputStream;

    public IOBlocked(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try {
            inputStream.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("IOException IOBlocked");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exit IOBlocked run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                f();
            }
        }).start();
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked run()");
    }
}

public class Interrupting {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> submit = executor.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getCanonicalName());
        submit.cancel(true);
        System.out.println("Interrupt send to " + r.getClass().getCanonicalName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System exit(0)");
        System.exit(0);
    }
}
