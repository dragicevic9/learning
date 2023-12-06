package streams;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class Q2_2023 {
    public static void main(String[] args) {

        IntStream intStream = IntStream.range(0, 5);
        OptionalDouble avg = intStream.average();
        avg.ifPresent(System.out::println);
    }
}
