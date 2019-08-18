package Thread.assitClass;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

class ThreadA extends Thread {

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("this is a ThreadA");
    }
}

class ThreadB extends Thread {

    @Override
    public void run() {
        System.out.println("this is a ThreadB");
        try {
            ThreadA threadA = new ThreadA();
            threadA.start();
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fished");
    }
}

public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        new ThreadB().start();
    }
}
