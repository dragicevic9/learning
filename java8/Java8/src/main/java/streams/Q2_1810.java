package streams;

import java.util.Arrays;
import java.util.List;

public class Q2_1810 {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Bob", "Bob", 31),
                new Person("Paul", "Paul", 32),
                new Person("John", "John", 33)
        );

        Double avgAge =
                people.stream()
                        .filter(person -> person.getAge() < 30)
                        .mapToInt(Person::getAge)
                        .average()
                        .orElse(0);

        System.out.println(avgAge);
    }
}
