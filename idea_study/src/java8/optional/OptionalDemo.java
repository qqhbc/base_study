package java8.optional;

import java.util.Optional;

/**
 * optional 允许null
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Integer value = null;
        Integer value1 = 5;
        Optional<Integer> a = Optional.ofNullable(value);
        Optional<Integer> b = Optional.of(value1);
        OptionalDemo optionalDemo = new OptionalDemo();
        Integer sum = optionalDemo.sum(a, b);
        System.out.println(sum);
    }

    private Integer sum(Optional<Integer> a, Optional<Integer> b) {
        System.out.println("第一个参数是否存在：" + a.isPresent());
        System.out.println("第二个参数是否存在：" + b.isPresent());
        Integer integer = a.orElse(0);
        return integer + b.get();

    }
}
