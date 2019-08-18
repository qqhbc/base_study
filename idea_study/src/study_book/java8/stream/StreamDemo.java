package java8.stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        Random random = new Random();
        random.ints(10).limit(10).forEach(System.out::println);
        List<Integer> list = Arrays.asList(1, 3, 5, 6, 6, 7, 8, 33, 2, 5);
        System.out.println("foreach 循环输出");
        list.forEach(System.out::println);
        System.out.println("map 匹配输出");
        list.stream().map(i -> i * i).distinct().sorted().forEach(System.out::println);
        System.out.println("filter 过滤输出");
        long count = list.stream().filter(i -> i > 10).count();
        System.out.println(count);
        System.out.println("parallel 并行输出");
        List<String> strings = Arrays.asList("hty", "wpx", "ssl", "fsw", "lwj", "lbb");
        long num = strings.parallelStream().filter(s -> !s.startsWith("l")).count();
        System.out.println(num);
        System.out.println("collections 加入合并输出");
        String string = strings.stream().filter(s -> !s.startsWith("l")).collect(Collectors.joining(","));
        System.out.println(string);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics statistics = numbers.stream().mapToInt(x -> x).summaryStatistics();// 简单统计
        System.out.println("statistics 统计输出");
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getSum());
    }
}
