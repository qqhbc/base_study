package Thread.assitClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量  控制某个资源可被同时访问的线程个数
 */
public class SemaphoreDemo {
    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();// 许可
                System.out.println("工人 " + num + " 占用了一个机器");
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("工人 " + num + " 释放了一个机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int N = 8; //工人数
        int count = 5; //机器数
        Semaphore semaphore = new Semaphore(count);
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        for (int i = 0; i < N; i++) {
            executorService.execute(new Worker(i, semaphore));
        }
        executorService.shutdown();
    }
}
