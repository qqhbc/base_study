package Thread.chapter3;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicGenerator extends IntGenerator {
    private AtomicInteger currentValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicGenerator());
    }
}
