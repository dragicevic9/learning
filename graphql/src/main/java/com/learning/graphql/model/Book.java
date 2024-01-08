package com.learning.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String id;
    private String name;
    private int pageCount;
    private String authorId;

    public static List<Book> books = new ArrayList<>(List.of(
            new Book("book-1", "Effective Java", 416, "author-1"),
            new Book("book-2", "Hitchhiker's Guid to the Galaxy", 208, "author-2"),
            new Book("book-3", "Down Under", 436, "author-3")
    ));

    public static Book getById(String id) {
        return books.stream()
                .filter(book -> book.id.equals(id))
                .findFirst()
                .orElse(null);
    }
}