package java8;

import java.util.*;

public class Java8Test {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("hty ");
        name.add("sfl ");
        name.add("wpx ");
        name.add("ssl ");
        name.add("fsw ");
        List<String> name1 = new ArrayList<>();
        name1.add("hty ");
        name1.add("sfl ");
        name1.add("wpx ");
        name1.add("ssl ");
        name1.add("fsw ");
        Java8Test java8Test = new Java8Test();
        System.out.println("java 7 展示排序");
        java8Test.sevenTest(name);
        System.out.println(name);
        System.out.println("================================");
        System.out.println("java 8 展示排序");
        java8Test.eightTest(name1);
        System.out.println(name1);
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
