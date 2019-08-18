package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockFair {
    private ReentrantLock lock = new ReentrantLock();
    private ReentrantLock fairLock = new ReentrantLock(true);

    public void next(int i) {
        try {
            if (i == 1) lock.lock();
            else fairLock.lock();
            System.out.println("线程获取锁执行" + Thread.currentThread().getName());
        } finally {
            if (i == 1) lock.unlock();
            else fairLock.unlock();
        }
    }
}

public class ReentrantLockFairDemo {
    public static void main(String[] args) {
        ReentrantLockFair reentrantLockFair = new ReentrantLockFair();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    reentrantLockFair.next(1);
                }
            });
        }
        executor.shutdown();
    }
}
