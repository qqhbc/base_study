package Thread.assitClass;

import java.util.concurrent.*;

/**
 * 一个或多个线程相互等待直至达到某一条件，才会全部执行。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int N = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        ExecutorService executorService = Executors.newFixedThreadPool(N);
        for (int i = 0; i < N; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 开始执行写操作");
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.println(Thread.currentThread().getName() + "执行完毕啦");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("开始执行其他操作啦");
                }
            });
        }
        executorService.shutdown();
    }
}
