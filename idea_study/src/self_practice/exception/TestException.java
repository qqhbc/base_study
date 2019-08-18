package exception;

public class TestException {
    public static void test() {
        try {
            Integer i = null;
            i.toString();
            System.out.println("step1");
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
