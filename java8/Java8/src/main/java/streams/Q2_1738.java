package streams;

import java.util.*;

public class Q2_1738 {
    public static void main(String[] args) {
        a();
        b();
        c();
    }

    private static void a() {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        Integer sum = integers.stream()
                .mapToInt(i -> i)
                .sum();
        System.out.println("Sum = " + sum);

        OptionalInt max = integers.stream()
                .mapToInt(i -> i)
                .max();
        max.ifPresent(System.out::println);

    }

    private static void b() {
        List<Person> people = Arrays.asList(
                new Person("Alan", "Burke", 22),
                new Person("Zoe", "Peters", 20),
                new Person("Peter", "Castle", 29)
        );

        Optional<Person> oldestPerson = people.stream()
                .max(Comparator.comparing(Person::getAge));

        oldestPerson.ifPresent(System.out::println);

    }

    private static void c() {
        List<Integer> integers = Arrays.asList(10, 47, 33, 23);
        Optional<Integer> max = integers.stream()
                .reduce(Integer::max);

        max.ifPresent(System.out::println);

        Integer maxI = integers.stream()
                .reduce(-1, Integer::max);

        System.out.println(maxI);
    }
}
