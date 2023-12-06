package streams;

import java.util.Arrays;
import java.util.List;

public class Q2_1840 {
    public static void main(String[] args) {

        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);

        boolean anyMatch11 = ls.stream()
                .distinct()
                .anyMatch(n -> n == 11);
        System.out.println(anyMatch11);

        boolean noneMatch = ls.stream()
                .noneMatch(n -> n % 11 > 0);
        System.out.println(noneMatch);
    }
}
