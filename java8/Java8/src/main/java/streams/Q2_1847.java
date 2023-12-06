package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q2_1847 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Gone with the wind", 5.),
                new Book("Gone with the wind", 10.),
                new Book("Atlas shrugged", 15.)
        );

        books.stream()
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice, Double::sum))
                .forEach((title, price) -> System.out.println(title + ":" + price));
    }

}
