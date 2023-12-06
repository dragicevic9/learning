package streams;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Q2_1841 {
    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger();
        Stream.of(11, 11, 22, 33)
                .parallel()
                .filter(n -> {
                    ai.incrementAndGet();
                    return n % 2 == 0;
                })
                .forEach(System.out::println);  // we have to use some terminal operation to start the whole process
        System.out.println(ai);

        AtomicInteger ai2 = new AtomicInteger();
        Stream<Integer> stream = Stream.of(11, 11, 22, 33).parallel();
        Stream<Integer> stream2 = stream.filter(e -> {
            ai2.incrementAndGet();
            return e % 2 == 0;
        });
        stream2.forEach(System.out::println);
        System.out.println(ai2);
    }
}
