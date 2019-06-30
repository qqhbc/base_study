package exception;

public class TestException {
    public static void test() {
        try {
            throw new RuntimeException("hello exception");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("step2");
        } finally {
            System.out.println("step3");
        }
        System.out.println("step4");
    }

    public static void main(String[] args) {
        test();
        System.out.println("step5");
    }
}
