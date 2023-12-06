package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Q2_1846 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Atlas Shrugged", 10.),
                new Book("Freedom at Midnight", 5.),
                new Book("Gone with the wind", 5.)
        );

        Map<String, Double> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice));

        bookMap.forEach((title, price) -> {
            if (title.startsWith("A")) System.out.println(price);
        });
    }
}
