package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Examples {
    public static void main(String[] args) {
//        filtersAndLimit();
//        collectAndReduce();

    }

    private static void collectAndReduce() {
        StringBuilder word = Stream.of("ad", "jud", "i", "cate")
                .collect(() -> new StringBuilder(),
                        (sb, str) -> sb.append(str),
                        (sb1, sb2) -> sb1.append(sb2));

        System.out.println(word);

        String name = Stream.of("ad", "jud", "i", "cate")
                .reduce("",
                        (sb, str) -> sb.concat(str),
                        (sb1, sb2) -> sb1.concat(sb2));

        System.out.println(name);
    }

    private static void filtersAndLimit() {
        List<String> names = Arrays.asList("April", "Ben", "Charlie", "David", "Benildus", "Christian");

        names.stream()
                .peek(System.out::println)
                .filter(s -> {
                    System.out.println("filter1: " + s);
                    return s.startsWith("B") || s.startsWith("C");
                })
                .filter(s -> {
                    System.out.println("filter 2: " + s);
                    return s.length() > 3;
                })
                .limit(1)
                .forEach(System.out::println);
    }

}
