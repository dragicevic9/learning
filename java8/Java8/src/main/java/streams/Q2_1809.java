package streams;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Q2_1809 {
    public static void main(String[] args) {

        List<Book> books = Arrays.asList(
                new Book("Thinking in Java", 30.),
                new Book("Java in 24 hrs", 20.),
                new Book("Java Recipes", 10.)
        );

//        OptionalDouble avg = books.stream()
        double avg = books.stream()
                .mapToDouble(Book::getPrice)
//                .filter(price -> price > 10)
                .filter(price -> price > 90)
                .average()
                .orElse(0.0);

//        avg.ifPresent(System.out::println);
        System.out.println(avg);
    }
}
