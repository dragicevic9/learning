package streams;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Q2_2024 {
    public static void main(String[] args) {
        DoubleStream doubleStream = DoubleStream.of(0, 2, 4);

        double oddNumsSum = doubleStream
                .filter(d -> d % 2 != 0)
                .sum();

        System.out.println(oddNumsSum);

        Stream<Double> stream = Stream.of(1., 3.);

        double avgEven = stream
                .mapToDouble(d -> d)
                .filter(d -> d % 2 == 0)
                .average()
                .orElse(0.);

        System.out.println(avgEven);
    }
}
