package Thread.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * 检查中断
 */
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("Cleapup " + id);
    }
}

class Blocked implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {  //
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {


                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        System.out.println("Executing");
                        for (int i = 0; i < 100 * 1000; i++) {
                            d += (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finishing");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting test()");
        } catch (InterruptedException e) {
            System.out.println("Exiting interruptedException");
        }
    }
}

/**
 * 当args[0]<1000 在线程阻塞是进行中断 运行结果
 * NeedsCleanup 1
 * Sleeping
 * Cleapup 1
 * Exiting interruptedException
 */

/**
 * 当args[0]>1000 在线程非阻塞下进行中断，NeedsCleanup被调用两次 运行结果
 * NeedsCleanup 1
 * Sleeping
 * NeedsCleanup 2
 * Executing
 * Finishing
 * Cleapup 2
 * Cleapup 1
 * NeedsCleanup 1
 * Sleeping
 * Cleapup 1
 * Exiting interruptedException
 */
public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("This is a error");
            System.exit(1);
        }
        Thread t = new Thread(new Blocked());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt(); // 中断进程
    }
}
