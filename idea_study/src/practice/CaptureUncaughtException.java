package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class MyExceptionThread implements Runnable {

    @Override
    public void run() {
        System.out.println("start executor task");
        throw new RuntimeException("我要抛出异常啦");
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("捕获异常啦！" + t.getName() + " " + e);
    }
}

class MyThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("myThread hhhh");
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new MyExceptionThread());
        System.out.println("有点意思啊！");
    }
}
