package java8.lambda;


/**
 * lambda表达式
 *
 * @FunctionalInterface 方法引用 用"::"表示
 * 函数式接口 有且仅有一个抽象方法，父类Object方法除外
 */
@FunctionalInterface
interface MathOperation {
    int operation(int a, int b);

    boolean equals(Object obj);

    default int add(int a, int b) {
        return a + b;
    }
}

interface Converter {
    void convert(int i);
}

/**
 * 问候接口
 */
@FunctionalInterface
interface GreetingService {
    void sayMessage(String msg);
}

public class LambdaTest {
    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        MathOperation addition = (int a, int b) -> a + b;
        MathOperation sub = (a, b) -> a - b;
        MathOperation multiOperation = (int a, int b) -> {
            return a * b;
        };
        MathOperation division = (a, b) -> {
            return a / b;
        };
        System.out.println("5+40 = " + lambdaTest.operate(5, 40, addition));
        System.out.println("5-40 = " + lambdaTest.operate(5, 40, sub));
        System.out.println("5*40 = " + lambdaTest.operate(5, 40, multiOperation));
        System.out.println("5/40 = " + lambdaTest.operate(5, 40, division));
        System.out.println("5+10 = " + addition.operation(5, 10));
        System.out.println("5+10 = " + addition.add(10, 5));
        GreetingService service = message -> System.out.println("hello " + message);
        GreetingService service1 = message -> System.out.println("hello " + message);
        service.sayMessage("hty");
        service1.sayMessage("sfl");

        int num = 5;
        Converter converter = param -> System.out.println(String.valueOf(param + num));
        converter.convert(2);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
