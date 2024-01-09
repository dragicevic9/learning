package com.learning.graphql.controller;

import com.learning.graphql.model.Author;
import com.learning.graphql.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }

    @QueryMapping
    public List<Book> books() {
        return Book.books;
    }

    @QueryMapping
    public List<Book> recentBooks(@Argument Integer count) {
        return Book.books.stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    @MutationMapping
    public Book createBook(@Argument String name, @Argument Integer pageCount, @Argument String authorId) {

        Book book = new Book();
        book.setName(name);
        book.setPageCount(pageCount);
        book.setAuthorId(authorId);
        book.setId(UUID.randomUUID().toString());

        Book.books.add(book);
        return book;
    }


}
