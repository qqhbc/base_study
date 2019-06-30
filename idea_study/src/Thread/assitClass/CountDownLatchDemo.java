package Thread.assitClass;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 一个或多个线程等其他线程完成后在执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        int num = 2;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 开始执行任务");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " 执行完成");
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        try {
            System.out.println("等待2个线程执行完成！");
            countDownLatch.await();
            System.out.println("2个线程执行完毕");
            System.out.println("开始执行主线程任务啦");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
