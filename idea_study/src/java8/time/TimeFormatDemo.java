package java8.time;

import java.time.*;

/**
 * 本地 + 时区
 */
public class TimeFormatDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间：" + now);
        System.out.println("当前年月日：" + now.toLocalDate());
        System.out.println("当前时间：" + now.toLocalTime());
        System.out.println("月：" + now.getMonth() + " 日：" + now.getDayOfMonth() + " 周：" + now.getDayOfWeek());
        System.out.println("定义时间：" + now.withDayOfMonth(10).withYear(2017));
        System.out.println("定义年月日：" + LocalDate.of(2020, Month.JUNE, 30));
        System.out.println("定义时间：" + LocalTime.of(1, 33, 44));
        LocalTime localTime = LocalTime.parse("10:33:22");
        System.out.println("解析时间字符串：" + localTime);
        System.out.println("解析日期字符串：" + LocalDate.parse("2222-11-21"));
        System.out.println("================时区==================");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("当前时区：" + zonedDateTime);
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        ZoneId normalized = zoneId.normalized();
        System.out.println(normalized.getRules());
        System.out.println("设置时区：" + zoneId);
        System.out.println("系统时区：" + ZoneId.systemDefault());
    }
}
