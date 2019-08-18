package Thread.chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        this.generator = g;
        this.id = ident;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) { // 没有被取消
            int val = generator.next();
            if (val % 2 != 0) { // 奇数
                System.out.println(val + " not even !");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator intGenerator, int count) {
        System.out.println("press control-c to exit");
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executor.execute(new EvenChecker(intGenerator, count));
        }
        executor.shutdown();
    }

    public static void test(IntGenerator intGenerator) {
        test(intGenerator, 10);
    }
}



