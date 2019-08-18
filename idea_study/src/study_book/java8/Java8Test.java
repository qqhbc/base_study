package java8;

import java.util.*;
import java.util.stream.Collectors;

public class Java8Test {
    public static void main(String[] args) {
//        List<String> name = new ArrayList<>();
////        name.add("hty ");
////        name.add("sfl ");
////        name.add("wpx ");
////        name.add("ssl ");
////        name.add("fsw ");
////        List<String> name1 = new ArrayList<>();
////        name1.add("hty ");
////        name1.add("sfl ");
////        name1.add("wpx ");
////        name1.add("ssl ");
////        name1.add("fsw ");
////        Java8Test java8Test = new Java8Test();
////        System.out.println("java 7 展示排序");
////        java8Test.sevenTest(name);
////        System.out.println(name);
////        System.out.println("================================");
////        System.out.println("java 8 展示排序");
////        java8Test.eightTest(name1);
////        System.out.println(name1);
        Set<Long> list = new HashSet<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        list.add(5L);
        Set<Long> list1 = new HashSet<>();
        list1.add(2L);
        list1.add(3L);
        list.stream().filter(b -> !list1.contains(b)).collect(Collectors.toList()).forEach(System.out::println);
    }

    private void sevenTest(List<String> list) {
        Collections.sort(list, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void eightTest(List<String> list) {
        Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
    }
}
