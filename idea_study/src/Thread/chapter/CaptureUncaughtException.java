package Thread.chapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 通过uncaughtExceptionHandler可以对线程异常进行捕获
 */
class ThreadException1 implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by" + t);
        System.out.println("eh=" + t.getUncaughtExceptionHandler());
        throw new RuntimeException("有趣啊兄嘚");
    }
}

class MyThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught" + e);
    }
}

class MyThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        //      System.out.println(this+"  creating new Thread");
        Thread t = new Thread(r);
        t.setName("aabb");
        //       System.out.println("create thread "+t);
        t.setUncaughtExceptionHandler(new MyThreadExceptionHandler());
        //       System.out.println("eh1 "+t.getUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        // Thread.setDefaultUncaughtExceptionHandler(new MyThreadExceptionHandler()); //可以设置默认线程异常处理器
        ExecutorService executor = Executors.newCachedThreadPool(new MyThreadFactory());
        executor.execute(new ThreadException1());
    }
}
