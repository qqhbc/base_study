package design;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 双重判断单例
 */
class Singleton {
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton newInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

/**
 * 通过枚举实现单例
 */
enum SingletonEnum {
    INSTANCE;

    private SingletonEnum() {
    }
}

public class SingletonDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //System.out.println(Singleton.newInstance());
                    System.out.println(SingletonEnum.INSTANCE); // 枚举实现单例既保证线程安全还保证反序列化不会重新创建实例
                }
            });
        }
        TimeUnit.MILLISECONDS.sleep(100);
        executor.shutdown();
    }
}
