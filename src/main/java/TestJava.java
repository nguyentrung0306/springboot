
import com.example.springboot.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TestJava {
    public static void main(String[] args) {
        test();
        // dateTimeInJava8();
    }

    public static void test() {
        String str  = new String("qwertyuiop");
        System.out.println(str.isBlank());
        List<Object> lst  = new ArrayList<>();
        lst.add(8.8);
        lst.add(9);
        lst.forEach(list -> { // lambda in java 8
            System.out.println("phan tu :" + list);
        });
        for (Object p : lst) { // stream API in java 8
            System.out.println("Stream API: " + p);
        }
        System.out.println("Show array :" + lst);
    }

    public static void dateTimeInJava8() { // Date and time APIs in java 8
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        System.out.println(localDate.getDayOfWeek().toString());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.isLeapYear());
        System.out.println(localDate.plusDays(12).toString());

        LocalTime localTime = LocalTime.of(12, 20);
        System.out.println(localTime.toString());
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.MIDNIGHT);
        System.out.println(localTime.NOON);
    }

}
