package Thread.chapter4;

/**
 * 一个任务获得对象锁，可以重复进入该对象的同步代码块
 */
public class MultiLock {
    public synchronized void f1(int count) {
        if (count++ < 0) {
            System.out.println("f1() calling f2() with count" + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count++ < 0) {
            System.out.println("f2() calling f1() with count" + count);
            f1(count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiLock multiLock = new MultiLock();
        new Thread(() -> multiLock.f1(-11)).start();
        for (int i = 0; i < 10000; i++) {
            System.out.println("test hjhhhhhhhhhhhhhh" + i);
        }
    }
}
