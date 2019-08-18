package Thread.chapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadException {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    throw new RuntimeException("hello exception");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("have a Exception");
        } finally {
            executor.shutdown();
        }

    }
}
