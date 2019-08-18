package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Counter {
    private int count;
    private Random random = new Random();

    public synchronized int increat(){

        int temp = count;
        if(random.nextBoolean()) Thread.yield();
        return (count = ++temp);
    }

    public synchronized int get() {
        return count;
    }
}

class ParkCount implements Runnable{
    private static Counter counter = new Counter();
    private static List<ParkCount> list = new ArrayList<>();
    private int number;
    private final int id;
    private static volatile boolean cancel = false;

    public static void setCancel(){
        cancel = true;
    }
    public ParkCount(int id){
        this.id = id;
        list.add(this);
    }
    @Override
    public void run() {
        while (!cancel) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total " + counter.increat());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopping "+ this);
    }

    public String toString(){
        return "ParkCount "+ id + " "+ getValue();
    }

    private synchronized int getValue(){
        return number;
    }
    public static int sum(){
        int sum = 0;
        for(ParkCount parkCount : list){
            sum += parkCount.getValue();
        }
        return sum;
    }
    public static int getTotal(){
        return counter.get();
    }

}
public class ParkCountDemo {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            executorService.execute(new ParkCount(i+1));
        }
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdown();
        ParkCount.setCancel();
        if(!executorService.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("This a error");
        }
        System.out.println(System.currentTimeMillis()-l);
        System.out.println("总数 "+ ParkCount.sum());
        System.out.println("计数器总数 "+ ParkCount.getTotal());
    }
}
