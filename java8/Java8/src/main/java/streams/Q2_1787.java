package streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Q2_1787 {
    public static void main(String[] args) {

        Stream<List<String>> stream = Stream.of(
                Arrays.asList("a", "b"),
                Arrays.asList("a", "c"),
                Arrays.asList("d", "c"));

        stream
                .filter(list -> list.contains("c"))
                .flatMap(Collection::stream)
                .forEach(System.out::print);
    }
}
