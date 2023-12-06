package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q2_1858 {
    public static void main(String[] args) {

        List<AnotherBook> books = Arrays.asList(
                new AnotherBook("Gone with the wind", "Fiction"),
                new AnotherBook("Bourne Ultimatum", "Thriller"),
                new AnotherBook("The Client", "Thriller")
        );

        List<String> genreList = new ArrayList<>();

        genreList = books.stream()
                .map(AnotherBook::getGenre)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(genreList);
    }
}
